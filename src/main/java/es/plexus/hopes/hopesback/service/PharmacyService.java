package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.PharmacyDTO;
import es.plexus.hopes.hopesback.repository.PharmacyRepositoryCustom;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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
            BigDecimal quantity = new BigDecimal(pharmacyDTO.getQuantity());
            pharmacyDTO.setMgDispensed(pharmacyDTO.getQuantity() == null || pharmacyDTO.getUnitDose() == null ? BigDecimal.ZERO : quantity.multiply(pharmacyDTO.getUnitDose()));
        });
        return pharmacyDTOPage;
    }
}
