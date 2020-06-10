package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.PatientDTO;
import es.plexus.hopes.hopesback.repository.PatientRepository;
import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.service.mapper.PatientDTOMapper;
import es.plexus.hopes.hopesback.service.mapper.PatientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.DNI_VIOLATION_CONSTRAINT_EXCEPTION;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.EMAIL_VIOLATION_CONSTRAINT_EXCEPTION;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.HEALTH_CARD_VIOLATION_CONSTRAINT_EXCEPTION;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.NHC_VIOLATION_CONSTRAINT_EXCEPTION;
import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.PHONE_VIOLATION_CONSTRAINT_EXCEPTION;

@Log4j2
@Service
@RequiredArgsConstructor
public class PatientService {


	private final PatientRepository patientRepository;

	public Page<PatientDTO> findPatientsByPathology(Long id, final Pageable pageable) {
		Page<Patient> patientList = patientRepository.findByPathologies(id, pageable);

		return patientList.map(PatientMapper.INSTANCE::entityToDto);
	}

	public Page<PatientDTO> findPatientBySearch(Long pth, String search, final Pageable pageable) {
		Page<Patient> patientList = patientRepository.findPatientBySearch(pth, search, pageable);

		return patientList.map(PatientMapper.INSTANCE::entityToDto);
	}

	public PatientDTO findById(Long id) throws ServiceException {
		Optional<Patient> patientEntity = getEntityPatientById(id);
		return PatientMapper.INSTANCE.entityToDto(patientEntity.orElse(null));
	}

	Optional<Patient> getEntityPatientById(Long id) throws ServiceException {
		Optional<Patient> patientEntity = patientRepository.findById(id);
		if (!patientEntity.isPresent()) {
			log.error("Id " + id + " no existe");
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION
					.exception("No se ha encontrado el paciente con el id: " + id);
		}
		return patientEntity;
	}

	public PatientDTO save(PatientDTO patient) throws ServiceException {
		if (null != patient.getPathologies() && patient.getPathologies().size() == 1){
			validatePatient(patient);
				return PatientMapper.INSTANCE.
						entityToDto(patientRepository.save(PatientMapper.INSTANCE.dtoToEntity(patient)));
		} else {
			throw ServiceExceptionCatalog.TOO_MANY_ELEMENTS_EXCEPTION
					.exception("Error: Too much pathologies");
		}


	}

	private void validatePatient(PatientDTO patientDTO) {

		Patient patient = null;

		if(null!=patientDTO.getId()){
			Optional<Patient> optionalPatient = patientRepository.findById(patientDTO.getId());
			patient = optionalPatient.orElseGet(null);
		}

		validatePatientNhc(patientDTO, patient);

		validatePatientHealthCard(patientDTO, patient);

		validatePatientMail(patientDTO, patient);

		validatePatientDni(patientDTO, patient);

		validatePatientPhone(patientDTO, patient);
	}

	private void validatePatientNhc(PatientDTO patientDTO, Patient patient) {
		boolean isExistNhc = patientRepository.existsByNhc(patientDTO.getNhc());
		if(isExistNhc && (patient == null || !patientDTO.getNhc().equals(patient.getNhc()))){
			throw ServiceExceptionCatalog.NHC_VIOLATION_CONSTRAINT_EXCEPTION
					.exception(NHC_VIOLATION_CONSTRAINT_EXCEPTION);
		}
	}

	private void validatePatientHealthCard(PatientDTO patientDTO, Patient patient) {
		boolean isExistHealthCard = patientRepository.existsByHealthCard(patientDTO.getHealthCard());
		if(isExistHealthCard && (patient == null || !patientDTO.getHealthCard().equals(patient.getHealthCard()))){
			throw ServiceExceptionCatalog.HEALTH_CARD_VIOLATION_CONSTRAINT_EXCEPTION
				.exception(HEALTH_CARD_VIOLATION_CONSTRAINT_EXCEPTION);
		}
	}

	private void validatePatientMail(PatientDTO patientDTO, Patient patient) {
		boolean isDistinctMail = isDistinctMail(patientDTO, patient);

		boolean isExistEmail = patientRepository.existsByEmail(patientDTO.getEmail());

		if(isExistEmail && (isDistinctMail || (patient==null && !StringUtils.isEmpty(patientDTO.getEmail())))){
			throw ServiceExceptionCatalog.EMAIL_VIOLATION_CONSTRAINT_EXCEPTION
					.exception( EMAIL_VIOLATION_CONSTRAINT_EXCEPTION);
		}
	}

	private void validatePatientDni(PatientDTO patientDTO, Patient patient) {
		boolean isExistDni = patientRepository.existsByDni(patientDTO.getDni());
		if (isExistDni && (patient == null || !patientDTO.getDni().equals(patient.getDni()))){
			throw ServiceExceptionCatalog.DNI_VIOLATION_CONSTRAINT_EXCEPTION
					.exception(DNI_VIOLATION_CONSTRAINT_EXCEPTION);
		}
	}

	private void validatePatientPhone(PatientDTO patientDTO, Patient patient) {
		boolean isDistinctPhone = isDistinctPhone(patientDTO, patient);
		boolean isExistPhone = patientRepository.existsByPhone(patientDTO.getPhone());

		if(isExistPhone && (isDistinctPhone || (patient==null && !StringUtils.isEmpty(patientDTO.getPhone())))) {
			throw ServiceExceptionCatalog.PHONE_VIOLATION_CONSTRAINT_EXCEPTION
					.exception(PHONE_VIOLATION_CONSTRAINT_EXCEPTION);
		}
	}

	private boolean isDistinctMail(PatientDTO patientDTO, Patient patient) {
		return patient != null
								&& (
										(!StringUtils.isEmpty(patientDTO.getEmail()) && StringUtils.isEmpty(patient.getEmail()))
										|| (StringUtils.isEmpty(patientDTO.getEmail()) && !StringUtils.isEmpty(patient.getEmail()))
									    || (!StringUtils.isEmpty(patientDTO.getEmail())
												&& !StringUtils.isEmpty(patient.getEmail())
												&& !patient.getEmail().equals(patientDTO.getEmail())));
	}


	private boolean isDistinctPhone(PatientDTO patientDTO, Patient patient) {
		return patient != null
				&& (
				(!StringUtils.isEmpty(patientDTO.getPhone()) && StringUtils.isEmpty(patient.getPhone()))
						|| (StringUtils.isEmpty(patientDTO.getPhone()) && !StringUtils.isEmpty(patient.getPhone()))
						|| (!StringUtils.isEmpty(patientDTO.getPhone())
						&& !StringUtils.isEmpty(patient.getPhone())
						&& !patient.getEmail().equals(patientDTO.getPhone())));
	}

	public void deleteById(Long id) {
		patientRepository.deleteById(id);
	}

	public Page<PatientDTO> filterPatients(String patient, final Pageable pageable) {
		PatientDTO patientDTO = PatientDTOMapper.INSTANCE.jsonToPatientDTOConventer(patient);

		ExampleMatcher matcher = ExampleMatcher.matchingAll().
				withIgnoreCase(true).withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

		Patient patientEx = PatientMapper.INSTANCE.dtoToEntity(patientDTO);

		Example<Patient> spec = Example.of(patientEx, matcher);

		Page<Patient> patientList = patientRepository.findAll(spec, pageable);

		return patientList.map(PatientMapper.INSTANCE::entityToDto);
	}

}
