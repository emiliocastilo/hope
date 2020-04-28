package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.DispensationDetailDTO;
import es.plexus.hopes.hopesback.repository.DispensationDetailRepository;
import es.plexus.hopes.hopesback.repository.model.Dispensation;
import es.plexus.hopes.hopesback.repository.model.DispensationDetail;
import es.plexus.hopes.hopesback.service.mapper.DispensationDetailMapper;
import es.plexus.hopes.hopesback.service.utils.CsvUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DispensationDetailService {

	private static final Logger LOGGER = LogManager.getLogger(DispensationDetailService.class);
	private static final String CALLING_DB = "Calling DB...";
	private final DispensationDetailRepository dispensationDetailRepository;

	public DispensationDetailDTO save(DispensationDetailDTO dispensationDTO) {
		if(Objects.nonNull(dispensationDTO)){
			return DispensationDetailMapper.INSTANCE.
					entityToDto(dispensationDetailRepository.save(DispensationDetailMapper.
																	INSTANCE.dtoToEntity(dispensationDTO)));
		} else{
			throw new ServiceException("Error: Object is null");
		}
	}

	public DispensationDetailDTO findById(Long id) {
		DispensationDetailDTO dispensationDetailDTO = null;
		DispensationDetail dispensationDetail = dispensationDetailRepository.findById(id).orElse(null);
		if(Objects.nonNull(dispensationDetail)) {
			dispensationDetailDTO = Optional.of(DispensationDetailMapper.INSTANCE.entityToDto(dispensationDetail)).get();
		}
		return dispensationDetailDTO;
	}

	public void deleteById(Long id) {
		dispensationDetailRepository.deleteById(id);
	}

	public void deleteAllByIdDispensation(Long id) {
		List<DispensationDetail> dispensationDetails = dispensationDetailRepository.findByDispensation(id);
		dispensationDetailRepository.deleteAll(dispensationDetails);
	}

	public Page<DispensationDetailDTO> findAllByDispensation(Long dsp, final Pageable pageable) {
		Page<DispensationDetail> page = dispensationDetailRepository.findByDispensation(dsp, pageable);
		return page.map(DispensationDetailMapper.INSTANCE::entityToDto);
	}

	public int loadFile(MultipartFile dispensationFile, Dispensation dispensation) {
		int numberCsvRecord = 0;
		List<CSVRecord> dispensationsRecords = CsvUtils.obtainCsvRecords(dispensationFile);
		if (dispensationsRecords!=null){

			List<DispensationDetail> dispensationDetailList = dispensationsRecords.stream().map(csvRecord ->{
				DispensationDetail dispensationDetail = DispensationDetailMapper.INSTANCE.csvRecordToEntity(csvRecord);
				dispensationDetail.setDispensation(dispensation);
				return dispensationDetail;
			}).collect(Collectors.toList());

			dispensationDetailRepository.saveAll(dispensationDetailList);
			numberCsvRecord = dispensationDetailList.size();
		}
		return numberCsvRecord;
	}

	public Page<DispensationDetailDTO> findDispensationDetailsBySearch(String search, Pageable pageable) {
		LOGGER.debug(CALLING_DB);
		Page<DispensationDetail> page = dispensationDetailRepository.findDispensationDetailBySearch(search, pageable);

		return page.map(DispensationDetailMapper.INSTANCE::entityToDto);
	}

	public Page<DispensationDetailDTO> filterDispensationDetails(String json, Pageable pageable) {
		DispensationDetailDTO dispensationDetailDTO = DispensationDetailMapper.INSTANCE.jsonToDTO(json);
		LOGGER.debug("Check DTO...");

		DispensationDetail dispensationDetail = DispensationDetailMapper.INSTANCE.dtoToEntity(dispensationDetailDTO);

		ExampleMatcher matcher = exampleByFilter(dispensationDetail);

		Example<DispensationDetail> example = Example.of(dispensationDetail, matcher);

		Page<DispensationDetail> page = dispensationDetailRepository.findAll(example, pageable);

		return page.map(DispensationDetailMapper.INSTANCE::entityToDto);
	}

	private ExampleMatcher exampleByFilter(DispensationDetail dispensationDetail) {

		return ExampleMatcher.matchingAll().
				withIgnoreCase(true)
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
	}
}
