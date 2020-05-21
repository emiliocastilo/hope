package es.plexus.hopes.hopesback.service;

import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.DoubleSummaryStatistics;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.plexus.hopes.hopesback.controller.model.DispensationDTO;
import es.plexus.hopes.hopesback.controller.model.FormDispensationDTO;
import es.plexus.hopes.hopesback.repository.DispensationRepository;
import es.plexus.hopes.hopesback.repository.model.Dispensation;
import es.plexus.hopes.hopesback.repository.model.HealthOutcome;
import es.plexus.hopes.hopesback.repository.model.Patient;
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
	
	public Map<String, Map<String, String>> findMonthlyConsume(String code, Integer lastYears) {
		log.debug(CALLING_DB);
		
		Map<String, Map<String, String>> result = new HashMap<>();		
		LocalDateTime firstDayOfMonth = FIRST_DAY_OF_CURRENT_YEAR.minusYears(lastYears - 1);
		
		for (int i = 0; i < (MONTHS_OF_YEAR.length -1) * lastYears; i++) {
			LocalDateTime firtstDayOfNextMonth = firstDayOfMonth.with(TemporalAdjusters.firstDayOfNextMonth());
			
			if(!result.containsKey(MONTHS_OF_YEAR[i%NUM_MONTHS_OF_YEAR])) {
				result.put(MONTHS_OF_YEAR[i%NUM_MONTHS_OF_YEAR], new HashMap<String, String>());
			}
			
			if(firtstDayOfNextMonth.isAfter(LocalDateTime.now())) {			
				result.get(MONTHS_OF_YEAR[i%NUM_MONTHS_OF_YEAR]).put(
						firstDayOfMonth.getYear() + " - Todos los pacientes", "-");
				result.get(MONTHS_OF_YEAR[i%NUM_MONTHS_OF_YEAR]).put(
						firstDayOfMonth.getYear() + " - Pacientes Controlados ", "-");
			} else {

				// Consumo de todos los pacientes separado por meses
				Double consumeByMonth = dispensationRepository.findResultsAllPatiensByMonth(firstDayOfMonth, firtstDayOfNextMonth, code);			
				result.get(MONTHS_OF_YEAR[i%NUM_MONTHS_OF_YEAR]).put(
						firstDayOfMonth.getYear() + " - Todos los pacientes", consumeByMonth.toString());
				
				//Consumo de todos los pacientes controlados (PASI=0) separado por meses
				Double consumeByPasi = 0.0;
				
				List<HealthOutcome> patiensWithPasiList = dispensationRepository.findPatiensWithPasi(LocalDateTime.now().plusYears(-3));
				
				Map<Patient, DoubleSummaryStatistics> patientMap = patiensWithPasiList.stream().collect(Collectors.groupingBy(
						ho -> ho.getPatient(),
						Collectors.summarizingDouble(ho -> Double.parseDouble(ho.getValue()))
				));
				

				for (Entry<Patient, DoubleSummaryStatistics> me : patientMap.entrySet()) {
					if(me.getValue().getSum() <= 3) {
						Long patient = (me.getKey()).getId();
						consumeByPasi += 
								dispensationRepository.findResultsAllPasiPatiensByMonth(
										firstDayOfMonth, firtstDayOfNextMonth, code, patient);
					}
		        }
				
				result.get(MONTHS_OF_YEAR[i%NUM_MONTHS_OF_YEAR]).put(
						firstDayOfMonth.getYear() + " - Pacientes Controlados", consumeByPasi.toString());			
			}
			
			firstDayOfMonth = firtstDayOfNextMonth;
						
		}
		
		return result;
	}
}
