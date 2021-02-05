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

        return pharmacyRepositoryCustom.findAll(pageable);
    }
    public Page<PharmacyDTO> findPharmacyBySearch(String search, final Pageable pageable) {

        log.debug(CALLING_DB);

        return pharmacyRepositoryCustom.findPharmacyBySearch(search, pageable);
    }
}
