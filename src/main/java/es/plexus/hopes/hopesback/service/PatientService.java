package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.PatientDTO;
import es.plexus.hopes.hopesback.repository.PatientRepository;
import es.plexus.hopes.hopesback.repository.model.Patient;
import es.plexus.hopes.hopesback.service.mapper.PatientMapper;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatientService {
    private final PatientRepository patientRepository;

    public List<PatientDTO> findPatientsByPathology(Long id) {
        return PatientMapper.INSTANCE.entityListToDtoList(patientRepository.findByPathologies(id));
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

}
