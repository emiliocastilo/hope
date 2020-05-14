package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.GroupingFieldTreatmentInfoDTO;
import es.plexus.hopes.hopesback.controller.model.PatientTreatmentDTO;
import es.plexus.hopes.hopesback.repository.PatientTreatmentRepository;
import es.plexus.hopes.hopesback.repository.model.PatientTreatment;
import es.plexus.hopes.hopesback.service.mapper.PatientTreatmentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class PatientTreatmentService {

	private static final String CALLING_DB = "Calling DB...";

	private final PatientTreatmentRepository patientTreatmentRepository;

	public List<GroupingFieldTreatmentInfoDTO> findPatientTreatmentByTreatment() {
		log.debug(CALLING_DB);
		List<PatientTreatment> patientTreatmentList = patientTreatmentRepository.findPatientTreatmentByTreatment();
		return patientTreatmentList.stream()
				.map(PatientTreatmentMapper.INSTANCE::entityToTypeTreatmentInfoDto).collect(Collectors.toList());
	}

	public List<GroupingFieldTreatmentInfoDTO> findPatientTreatmentByCombinedTreatment() {
		log.debug(CALLING_DB);
		List<PatientTreatment> patientTreatmentList = patientTreatmentRepository.findPatientTreatmentByCombinedTreatment();
		return patientTreatmentList.stream()
				.map(PatientTreatmentMapper.INSTANCE::entityToTypeTreatmentInfoDto).collect(Collectors.toList());
	}

	public List<GroupingFieldTreatmentInfoDTO> findPatientTreatmentByEndCauseBiologicTreatment(String endCause) {
		log.debug(CALLING_DB);
		List<PatientTreatment> patientTreatmentList =
				patientTreatmentRepository.findPatientTreatmentByEndCauseBiologicTreatment(endCause);
		return patientTreatmentList.stream()
				.map(PatientTreatmentMapper.INSTANCE::entityToReasonTreatmentInfoDto).collect(Collectors.toList());
	}

	public List<GroupingFieldTreatmentInfoDTO> findPatientTreatmentByEndCauseBiologicTreatmentInLast5Years(String endCause) {
		log.debug(CALLING_DB);
		List<PatientTreatment> patientTreatmentList =
				patientTreatmentRepository.findPatientTreatmentByEndCauseBiologicTreatmentInLast5Years(endCause);
		return patientTreatmentList.stream()
				.map(PatientTreatmentMapper.INSTANCE::entityToReasonTreatmentInfoDto).collect(Collectors.toList());
	}

	public List<PatientTreatmentDTO> findPatientTreatmentByNumberChangesOfBiologicTreatment() {
		log.debug(CALLING_DB);
		List<PatientTreatment> patientTreatmentList =
				patientTreatmentRepository.findPatientTreatmentByNumberChangesOfBiologicTreatment();
		return patientTreatmentList.stream()
				.map(PatientTreatmentMapper.INSTANCE::entityToPatientTreamentDto).collect(Collectors.toList());
	}

}
