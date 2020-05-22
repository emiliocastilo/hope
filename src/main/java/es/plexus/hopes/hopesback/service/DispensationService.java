package es.plexus.hopes.hopesback.service;

import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.plexus.hopes.hopesback.controller.model.DispensationDTO;
import es.plexus.hopes.hopesback.controller.model.FormDispensationDTO;
import es.plexus.hopes.hopesback.repository.DispensationRepository;
import es.plexus.hopes.hopesback.repository.model.Dispensation;
import es.plexus.hopes.hopesback.service.mapper.DispensationMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class DispensationService {
	private static final String CALLING_DB = "Calling BD...";
	private static final String[] MONTHS_OF_YEAR = (new DateFormatSymbols(Locale.getDefault()).getShortMonths());
    private static final LocalDateTime FIRST_DAY_OF_CURRENT_YEAR = LocalDateTime.of(Calendar.getInstance().get(Calendar.YEAR), Month.JANUARY, 1, 0, 0);
    private static final Integer NUM_MONTHS_OF_YEAR = 12;
	
	private final DispensationRepository dispensationRepository;
	private final DispensationDetailService dispensationDetailService;
	private final HealthOutcomeService healthOutcomeService;
	
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
	
	public Map<String, Map<String, String>> findMonthlyConsume(Integer lastYears, Boolean isAvg) {
		log.debug(CALLING_DB);
		
		Map<String, Map<String, String>> result = new HashMap<>();		
		LocalDateTime dateStartPeriod = FIRST_DAY_OF_CURRENT_YEAR.minusYears(lastYears - 1);
		List<Long> listPatients = healthOutcomeService.getAllPatientsId();
		
		for (int index = 0; index < (MONTHS_OF_YEAR.length -1) * lastYears; index++) {
			LocalDateTime dateStopPeriod = dateStartPeriod.with(TemporalAdjusters.firstDayOfNextMonth());			
			fillMonthlyConsume(index, dateStartPeriod, dateStopPeriod.plusSeconds(-1), listPatients, isAvg, result);
			dateStartPeriod = dateStopPeriod;				
		}
		
		return result;
	}
	
	public Map<String, Map<String, String>> findMonthlyConsumeAcumulated(Integer lastYears, Boolean isAvg) {
		log.debug(CALLING_DB);
		
		Map<String, Map<String, String>> result = new HashMap<>();		
		LocalDateTime dateStartPeriod = FIRST_DAY_OF_CURRENT_YEAR.minusYears(lastYears - 1);
		LocalDateTime dateStopPeriod = dateStartPeriod.with(TemporalAdjusters.firstDayOfNextMonth());
		List<Long> listPatients = healthOutcomeService.getAllPatientsId();
		
		for (int index = 0; index < (MONTHS_OF_YEAR.length -1) * lastYears; index++) {
			fillMonthlyConsume(index, dateStartPeriod, dateStopPeriod.plusSeconds(-1), listPatients, isAvg, result);	
			dateStopPeriod = dateStopPeriod.with(TemporalAdjusters.firstDayOfNextMonth());
		}
		
		return result;
	}
	
	private void fillMonthlyConsume(int index,
			LocalDateTime dateStartPeriod, LocalDateTime dateStopPeriod,
			List<Long> listPatients, Boolean isAvg, Map<String, Map<String, String>> result) {
		

		String resultAllPatients = "-";
		String resultAllPatientsContolled = "-";
		
		if(!result.containsKey(MONTHS_OF_YEAR[index%NUM_MONTHS_OF_YEAR])) {
			result.put(MONTHS_OF_YEAR[index%NUM_MONTHS_OF_YEAR], new HashMap<String, String>());
		}
		
		if(dateStopPeriod.isAfter(LocalDateTime.now())) {			
			result.get(MONTHS_OF_YEAR[index%NUM_MONTHS_OF_YEAR]).put(
					dateStopPeriod.getYear() + " - Todos los pacientes", resultAllPatients);
			result.get(MONTHS_OF_YEAR[index%NUM_MONTHS_OF_YEAR]).put(
					dateStopPeriod.getYear() + " - Pacientes Controlados ", resultAllPatientsContolled);
		} else {
			
			// Consumo de todos los pacientes separado por meses
			Double consumeByMonth = dispensationRepository.findResultsAllPatiensByMonth(dateStartPeriod, dateStopPeriod);			
			
			
			//Consumo de todos los pacientes controlados (PASI=0) separado por meses
			Double consumeByPasi = 0.0;
						
			for (Long patient : listPatients) {
				consumeByPasi += 
						dispensationRepository.findResultsAllPasiPatiensByMonth(LocalDateTime.now().plusMonths(-6),
								dateStartPeriod, dateStopPeriod, patient);
			}
			
			if(isAvg) {
				if(consumeByMonth > 0) {
					List<String> listPatientsForAvg = dispensationDetailService.findPatiensMonth(
							dateStopPeriod.plusMonths(-3), dateStopPeriod.plusSeconds(-1));
					if(listPatientsForAvg.size() > 0) {
						consumeByMonth /= listPatientsForAvg.size();
						resultAllPatients = consumeByMonth.toString();
						consumeByPasi /= listPatientsForAvg.size();
						resultAllPatientsContolled = consumeByPasi.toString();
					}
				} else {
					resultAllPatients = "0.0";
					resultAllPatientsContolled = "0.0";
				}
			}
			
			// Set result in current month
			result.get(MONTHS_OF_YEAR[index%NUM_MONTHS_OF_YEAR]).put(
					dateStopPeriod.getYear() + " - Todos los pacientes", consumeByMonth.toString());
			
			result.get(MONTHS_OF_YEAR[index%NUM_MONTHS_OF_YEAR]).put(
					dateStopPeriod.getYear() + " - Pacientes Controlados", consumeByPasi.toString());			
		}
	}
}
