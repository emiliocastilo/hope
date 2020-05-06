package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.MedicineDTO;
import es.plexus.hopes.hopesback.repository.MedicineRepository;
import es.plexus.hopes.hopesback.repository.model.Medicine;
import es.plexus.hopes.hopesback.repository.model.Recommendation;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.service.mapper.MedicineMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class MedicineService {

	private static final String CALLING_DB = "Calling DB...";
	public static final String DEVELOPER_MESSAGE_RECOMMENDATION_MANDATORY =
			"Recommendation not found. If checked Recommended field, Recommendation is mandatory for the medicine";

	private final MedicineRepository medicineRepository;
	private final RecommendationService recommendationService;

	public MedicineDTO save(MedicineDTO medicineDto) throws ServiceException {
		Medicine medicine = addMedicineCommon(medicineDto);
		log.debug(CALLING_DB);
		return MedicineMapper.INSTANCE.entityToDto(medicineRepository.save(medicine));
	}

	public MedicineDTO findById(Long id) {
		MedicineDTO medicineDto = null;
		log.debug(CALLING_DB);
		Medicine dispensationDetail = medicineRepository.findById(id).orElse(null);
		if(Objects.nonNull(dispensationDetail)) {
			medicineDto = Optional.of(MedicineMapper.INSTANCE.entityToDto(dispensationDetail)).get();
		}
		return medicineDto;
	}

	public void deleteById(Long id) {
		log.debug(CALLING_DB);
		medicineRepository.deleteById(id);
	}

	public Page<MedicineDTO> findAllMedicines(final Pageable pageable) {
		log.debug(CALLING_DB);
		Page<Medicine> page = medicineRepository.findAll(pageable);
		return page.map(MedicineMapper.INSTANCE::entityToDto);
	}

	public Page<MedicineDTO> findMedicinesBySearch(String search, Pageable pageable) {
		log.debug(CALLING_DB);
		Page<Medicine> page = medicineRepository.findMedicinesBySearch(search, pageable);

		return page.map(MedicineMapper.INSTANCE::entityToDto);
	}

	public Page<MedicineDTO> filterMedicines(String json, Pageable pageable) {

		Medicine medicine = MedicineMapper.INSTANCE.jsonToEntity(json);

		ExampleMatcher matcher = exampleByFilter();

		Example<Medicine> example = Example.of(medicine, matcher);
		log.debug(CALLING_DB);
		Page<Medicine> page = medicineRepository.findAll(example, pageable);

		return page.map(MedicineMapper.INSTANCE::entityToDto);
	}

	private Medicine addMedicineCommon(MedicineDTO medicineDto) throws ServiceException {
		Medicine medicine = MedicineMapper.INSTANCE.dtoToEntity(medicineDto);
		if(medicineDto.isRecommended()){
			if (Objects.isNull(medicineDto.getRecommendation())){
				throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION
						.exception(DEVELOPER_MESSAGE_RECOMMENDATION_MANDATORY);
			}

			Optional<Recommendation> recommendation = recommendationService
					.findById(medicineDto.getRecommendation().getId());
			if (!recommendation.isPresent()){
				throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION
						.exception(DEVELOPER_MESSAGE_RECOMMENDATION_MANDATORY);
			}
			medicine.setRecommendation(recommendation.get());
		}

		return medicine;
	}

	private ExampleMatcher exampleByFilter() {
		final String[] IGNORE_PATHS = {"commercialization", "recommended"};
		return ExampleMatcher.matchingAll()
				.withIgnorePaths(IGNORE_PATHS)
				.withIgnoreCase(true)
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
	}
}
