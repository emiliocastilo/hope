package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.PharmacyDTO;
import es.plexus.hopes.hopesback.repository.MedicineRepository;
import es.plexus.hopes.hopesback.repository.PharmacyRepositoryCustom;
import es.plexus.hopes.hopesback.repository.model.Medicine;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class PharmacyService {

    private static final String CALLING_DB = "Calling BD...";
    private final PharmacyRepositoryCustom pharmacyRepositoryCustom;

    public Page<PharmacyDTO> findAll(final Pageable pageable) {

        log.debug(CALLING_DB);
        Page<PharmacyDTO> pharmacyDTOPage = pharmacyRepositoryCustom.findAll(pageable);
        pharmacyDTOPage.getContent().forEach(pharmacyDTO -> {
            // TODO aquí calculamos el MG dispensado, aún no sabemos la información correcta que habrá en un archivo de medicamento.
            String quantity = pharmacyDTO.getQuantity();
            pharmacyDTO.setMgDispensed(BigDecimal.ZERO);
        });
        return pharmacyDTOPage;
    }
}
