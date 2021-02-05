package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.controller.model.PharmacyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface PharmacyRepositoryCustom {

    Page<PharmacyDTO> findAll(Pageable pageable);
    Page<PharmacyDTO> findPharmacyBySearch(String search, Pageable pageable);

}
