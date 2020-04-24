package es.plexus.hopes.hopesback.service;

import java.util.Optional;

import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import es.plexus.hopes.hopesback.controller.model.PatientDTO;
import es.plexus.hopes.hopesback.repository.PatientRepository;
import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.service.mapper.PatientDTOMapper;
import es.plexus.hopes.hopesback.service.mapper.PatientMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService {
    	
	private final PatientRepository patientRepository;

    public Page<PatientDTO> findPatientsByPathology(Long id, final Pageable pageable) {
    	Page<Patient> patientList = patientRepository. findByPathologies(id, pageable);

		return patientList.map(PatientMapper.INSTANCE::entityToDto);
    }

    public Page<PatientDTO> findPatientBySearch(String search, final Pageable pageable) {
		Page<Patient> patientList = patientRepository.findPatientBySearch(search, pageable);

		return patientList.map(PatientMapper.INSTANCE::entityToDto);
	}
    
    public Optional<PatientDTO> findById(Long id) {

        Patient patientEntity = patientRepository.findById(id).orElse(null);

        return Optional.of( PatientMapper.INSTANCE.entityToDto(patientEntity));
    }

    public PatientDTO save(PatientDTO patient) {
        if(null!=patient.getPathologies() && patient.getPathologies().size()==1){
            return PatientMapper.INSTANCE.
                    entityToDto(patientRepository.save(PatientMapper.INSTANCE.dtoToEntity(patient)));
        } else{
            throw new ServiceException("Error: Too much pathologies");
        }


    }

    public void deleteById(Long id) {
        patientRepository.deleteById(id);
    }

    public Page<PatientDTO> filterPatiens(String patient, final Pageable pageable) {
    	PatientDTO patientDTO = PatientDTOMapper.INSTANCE.jsonToPatientDTOConventer(patient);	

    	ExampleMatcher matcher = ExampleMatcher.matchingAll().
    			withIgnoreCase(true).withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
    	
    	Patient patientEx = PatientMapper.INSTANCE.dtoToEntity(patientDTO);
    	
		Example<Patient> spec = Example.of(patientEx, matcher);
			 
		Page<Patient> patientList = patientRepository.findAll(spec, pageable);

		return patientList.map(PatientMapper.INSTANCE::entityToDto);
	}
	
}
