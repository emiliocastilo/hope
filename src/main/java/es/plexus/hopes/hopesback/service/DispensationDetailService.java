package es.plexus.hopes.hopesback.service;

import java.text.DateFormatSymbols;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVRecord;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.plexus.hopes.hopesback.controller.model.DispensationDetailDTO;
import es.plexus.hopes.hopesback.repository.DispensationDetailRepository;
import es.plexus.hopes.hopesback.repository.model.Dispensation;
import es.plexus.hopes.hopesback.repository.model.DispensationDetail;
import es.plexus.hopes.hopesback.service.mapper.DispensationDetailMapper;
import es.plexus.hopes.hopesback.service.utils.CsvUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class DispensationDetailService {

	private static final String CALLING_DB = "Llamando a la DB...";
    private static final DateFormatSymbols MONTHS_OF_YEAR = new DateFormatSymbols(Locale.getDefault());
	
	private final DispensationDetailRepository dispensationDetailRepository;

	public DispensationDetailDTO save(DispensationDetailDTO dispensationDTO) {
		if(Objects.nonNull(dispensationDTO)){
			log.debug(CALLING_DB);
			return DispensationDetailMapper.INSTANCE.
					entityToDto(dispensationDetailRepository.save(DispensationDetailMapper.
																	INSTANCE.dtoToEntity(dispensationDTO)));
		} else{
			throw new ServiceException("Error: Object is null");
		}
	}

	public DispensationDetailDTO findById(Long id) {
		DispensationDetailDTO dispensationDetailDTO = null;
		log.debug(CALLING_DB);
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
		log.debug(CALLING_DB);
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
			log.debug(CALLING_DB);
			dispensationDetailRepository.saveAll(dispensationDetailList);
			numberCsvRecord = dispensationDetailList.size();
		}
		return numberCsvRecord;
	}

	public Page<DispensationDetailDTO> findDispensationDetailsBySearch(String search, Pageable pageable) {
		log.debug(CALLING_DB);
		Page<DispensationDetail> page = dispensationDetailRepository.findDispensationDetailBySearch(search, pageable);

		return page.map(DispensationDetailMapper.INSTANCE::entityToDto);
	}

	public Page<DispensationDetailDTO> filterDispensationDetails(String json, Pageable pageable) {
		DispensationDetailDTO dispensationDetailDTO = DispensationDetailMapper.INSTANCE.jsonToDTO(json);
		log.debug("Check DTO...");

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
