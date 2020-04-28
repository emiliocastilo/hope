package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.DispensationDTO;
import es.plexus.hopes.hopesback.controller.model.FormDispensationDTO;
import es.plexus.hopes.hopesback.repository.DispensationRepository;
import es.plexus.hopes.hopesback.repository.model.Dispensation;
import es.plexus.hopes.hopesback.service.mapper.DispensationMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DispensationService {
	private final DispensationRepository dispensationRepository;
	private final DispensationDetailService dispensationDetailService;

	public DispensationDTO save(FormDispensationDTO dispensationDTO) {
		if(Objects.nonNull(dispensationDTO)){
			Dispensation dispensation = dispensationRepository.save(DispensationMapper.INSTANCE.formDispensationDtoToEntity(dispensationDTO));
			dispensation.setNumRecords(dispensationDetailService.loadFile(dispensationDTO.getFileDispensation(), dispensation));
			return DispensationMapper.INSTANCE.entityToDto(dispensationRepository.save(dispensation));
		} else{
			throw new ServiceException("Error: Object is null");
		}
	}

	public DispensationDTO findById(Long id) {
		DispensationDTO dispensationDTO = null;
		Dispensation dispensationEntity = dispensationRepository.findById(id).orElse(null);
		if(Objects.nonNull(dispensationEntity)){
			dispensationDTO = Optional.of(DispensationMapper.INSTANCE.entityToDto(dispensationEntity)).get();
		}
		return dispensationDTO;
	}

	public void deleteById(Long id) {
		dispensationDetailService.deleteAllByIdDispensation(id);
		dispensationRepository.deleteById(id);
	}

	public Page<DispensationDTO> findAll(final Pageable pageable) {
		Page<Dispensation> page = dispensationRepository. findAll(pageable);
		return page.map(DispensationMapper.INSTANCE::entityToDto);
	}
}
