package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.DispensationDTO;
import es.plexus.hopes.hopesback.controller.model.FormDispensationDTO;
import es.plexus.hopes.hopesback.repository.DispensationRepository;
import es.plexus.hopes.hopesback.repository.model.Dispensation;
import es.plexus.hopes.hopesback.service.mapper.DispensationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class DispensationService {
	private static final String CALLING_DB = "Calling DB...";
	private final DispensationRepository dispensationRepository;
	private final DispensationDetailService dispensationDetailService;

	public DispensationDTO save(FormDispensationDTO dispensationDTO) {
		if(Objects.nonNull(dispensationDTO)){
			log.debug(CALLING_DB);
			Dispensation dispensation = dispensationRepository.save(DispensationMapper.INSTANCE.formDispensationDtoToEntity(dispensationDTO));
			dispensation.setNumRecords(dispensationDetailService.loadFile(dispensationDTO.getFileDispensation(), dispensation));
			return DispensationMapper.INSTANCE.entityToDto(dispensationRepository.save(dispensation));
		} else{
			throw new ServiceException("Error: Object is null");
		}
	}

	public DispensationDTO findById(Long id) {
		DispensationDTO dispensationDTO = null;
		log.debug(CALLING_DB);
		Dispensation dispensationEntity = dispensationRepository.findById(id).orElse(null);
		if(Objects.nonNull(dispensationEntity)){
			dispensationDTO = Optional.of(DispensationMapper.INSTANCE.entityToDto(dispensationEntity)).get();
		}
		return dispensationDTO;
	}

	public void deleteById(Long id) {
		log.debug(CALLING_DB);
		dispensationDetailService.deleteAllByIdDispensation(id);
		dispensationRepository.deleteById(id);
	}

	public Page<DispensationDTO> findAll(final Pageable pageable) {
		log.debug(CALLING_DB);
		Page<Dispensation> page = dispensationRepository. findAll(pageable);
		return page.map(DispensationMapper.INSTANCE::entityToDto);
	}
}
