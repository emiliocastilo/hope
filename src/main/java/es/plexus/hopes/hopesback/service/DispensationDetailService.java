package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.DispensationDetailDTO;
import es.plexus.hopes.hopesback.repository.DispensationDetailRepository;
import es.plexus.hopes.hopesback.repository.model.Dispensation;
import es.plexus.hopes.hopesback.repository.model.DispensationDetail;
import es.plexus.hopes.hopesback.service.mapper.DispensationDetailMapper;
import es.plexus.hopes.hopesback.service.utils.CsvUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.csv.CSVRecord;
import org.hibernate.service.spi.ServiceException;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class DispensationDetailService {

	private static final String CALLING_DB = "Llamando a la DB...";
	private static final String[] MONTHS_OF_YEAR = (new DateFormatSymbols(Locale.getDefault()).getShortMonths());
    private static final LocalDateTime FIRST_DAY_OF_CURRENT_YEAR = LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Month.JANUARY, 1, 0, 0);
    private static final Integer NUM_MONTHS_OF_YEAR = 12;
	
	private final DispensationDetailRepository dispensationDetailRepository;
	private final HealthOutcomeService healthOutcomeService;

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
	
	public Map<String, Map<String, BigDecimal>> findMonthlyConsume(Integer lastYears, Boolean isAvg, String code) {
		log.debug(CALLING_DB);
		
		Map<String, Map<String, BigDecimal>> result = new HashMap<>();
		LocalDateTime dateStartPeriod = FIRST_DAY_OF_CURRENT_YEAR.minusYears(lastYears - 1);
		List<Long> listPatients = healthOutcomeService.getAllPatientsId();
		
		for (int index = 0; index < (MONTHS_OF_YEAR.length -1) * lastYears; index++) {
			LocalDateTime dateStopPeriod = dateStartPeriod.with(TemporalAdjusters.firstDayOfNextMonth());			
			fillMonthlyConsume(index, dateStartPeriod, dateStopPeriod.plusSeconds(-1), listPatients, isAvg, code, result);
			dateStartPeriod = dateStopPeriod;				
		}
		
		return result;
	}
	
	public Map<String, Map<String, BigDecimal>> findMonthlyConsumeAcumulated(Integer lastYears, Boolean isAvg, String code) {
		log.debug(CALLING_DB);
		
		Map<String, Map<String, BigDecimal>> result = new HashMap<>();
		LocalDateTime dateStartPeriod = FIRST_DAY_OF_CURRENT_YEAR.minusYears(lastYears - 1);
		LocalDateTime dateStopPeriod = dateStartPeriod.with(TemporalAdjusters.firstDayOfNextMonth());
		List<Long> listPatients = healthOutcomeService.getAllPatientsId();
		
		for (int index = 0; index < (MONTHS_OF_YEAR.length -1) * lastYears; index++) {
			fillMonthlyConsume(index, dateStartPeriod, dateStopPeriod.plusSeconds(-1), listPatients, isAvg, code, result);	
			dateStopPeriod = dateStopPeriod.with(TemporalAdjusters.firstDayOfNextMonth());
		}
		
		return result;
	}

	public List<DispensationDetailDTO> findDispensationDetailByPatientId(Long id) {
		List<DispensationDetail> dispensationDetailsList = dispensationDetailRepository.findDispensationDetailByPatientId(id);
		return dispensationDetailsList.stream()
				.map(Mappers.getMapper(DispensationDetailMapper.class)::entityToDto)
				.collect(Collectors.toList());
	}

	public List<DispensationDetailDTO> findDispensationDetailByNhc(String nhc) {
		List<DispensationDetail> dispensationDetailsList = dispensationDetailRepository.findDispensationDetailByNhc(nhc);
		return dispensationDetailsList.stream()
				.map(Mappers.getMapper(DispensationDetailMapper.class)::entityToDto)
				.collect(Collectors.toList());
	}

	private void fillMonthlyConsume(int index,
			LocalDateTime dateStartPeriod, LocalDateTime dateStopPeriod,
			List<Long> listPatients, Boolean isAvg, String code,
			Map<String, Map<String, BigDecimal>> result) {
		
		YearMonth ymStopPeriod = YearMonth.from(dateStopPeriod);
		YearMonth ymNow = YearMonth.from(LocalDateTime.now());
		BigDecimal resultAllPatients = BigDecimal.ZERO;
		BigDecimal resultAllPatientsContolled = BigDecimal.ZERO;
		
		if(!result.containsKey(MONTHS_OF_YEAR[index%NUM_MONTHS_OF_YEAR])) {
			result.put(MONTHS_OF_YEAR[index%NUM_MONTHS_OF_YEAR], new HashMap<>());
		}

		if(ymStopPeriod.isAfter(ymNow)) {			
			result.get(MONTHS_OF_YEAR[index%NUM_MONTHS_OF_YEAR]).put(
					dateStopPeriod.getYear() + " - Todos los pacientes", resultAllPatients.setScale(2, RoundingMode.HALF_UP));
			result.get(MONTHS_OF_YEAR[index%NUM_MONTHS_OF_YEAR]).put(
					dateStopPeriod.getYear() + " - Pacientes Controlados ", resultAllPatientsContolled.setScale(2, RoundingMode.HALF_UP));
		} else {

			// Consumo de todos los pacientes separado por meses
			Double consumeByMonth = dispensationDetailRepository.findResultsAllPatiensByMonth(
					dateStartPeriod, dateStopPeriod, code);

			//Consumo de todos los pacientes controlados (PASI=0) separado por meses
			Double consumeByPasi = 0.00;

			for (Long patient : listPatients) {
				consumeByPasi +=
						dispensationDetailRepository.findResultsAllPasiPatiensByMonth(LocalDateTime.now().plusMonths(-6),
								dateStartPeriod, dateStopPeriod, patient, code);
			}
			
			if(Boolean.TRUE.equals(isAvg)) {
				if(consumeByMonth > 0) {
					List<String> listPatientsForAvg = dispensationDetailRepository.findPatiensMonth(
							dateStopPeriod.plusMonths(-3), dateStopPeriod.plusSeconds(-1));
					if(CollectionUtils.isNotEmpty(listPatientsForAvg)) {
						consumeByMonth /= listPatientsForAvg.size();
						consumeByPasi /= listPatientsForAvg.size();
					}
				} else {
					consumeByMonth  = 0.00;
					consumeByPasi = 0.00;
				}
			}

			resultAllPatients = new BigDecimal(consumeByMonth);
			resultAllPatientsContolled = new BigDecimal(consumeByPasi);

			// Set result in current month
			result.get(MONTHS_OF_YEAR[index%NUM_MONTHS_OF_YEAR]).put(
					dateStopPeriod.getYear() + " - Todos los pacientes",resultAllPatients.setScale(2, RoundingMode.HALF_UP));
			
			result.get(MONTHS_OF_YEAR[index%NUM_MONTHS_OF_YEAR]).put(
					dateStopPeriod.getYear() + " - Pacientes Controlados", resultAllPatientsContolled.setScale(2, RoundingMode.HALF_UP));
		}
	}
}
