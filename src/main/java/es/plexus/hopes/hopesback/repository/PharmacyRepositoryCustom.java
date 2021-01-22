package es.plexus.hopes.hopesback.repository;

import es.plexus.hopes.hopesback.controller.model.PharmacyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PharmacyRepositoryCustom {

    Page<PharmacyDTO> findAll(Pageable pageable);
}
