package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.PatientDTO;
import es.plexus.hopes.hopesback.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/patient")
public class PatientController {
    private static final Logger LOGGER = LogManager.getLogger(PatientController.class);

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientDTO>> findAll(@RequestParam Long pth) {
        return ResponseEntity.ok(patientService.findPatientsByPathology(pth));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> findById(@PathVariable Long id) {
        Optional<PatientDTO> patient = patientService.findById(id);
        if (!patient.isPresent()) {
            LOGGER.error("Id " + id + " is not existed");
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(patient.get());
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody PatientDTO patient) {
        return ResponseEntity.ok(patientService.save(patient));
    }

    @PutMapping
    public ResponseEntity<PatientDTO> update(@Valid @RequestBody PatientDTO patient) {
        if (!patientService.findById(patient.getId()).isPresent()) {
            LOGGER.error("Id " + patient.getId() + " is not existed");
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(patientService.save(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        if (!patientService.findById(id).isPresent()) {
            LOGGER.error("Id " + id + " is not existed");
            ResponseEntity.badRequest().build();
        }

        patientService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
