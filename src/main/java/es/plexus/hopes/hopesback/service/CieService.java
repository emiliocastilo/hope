package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.CieDTO;
import es.plexus.hopes.hopesback.repository.Cie10Repository;
import es.plexus.hopes.hopesback.repository.Cie9Repository;
import es.plexus.hopes.hopesback.repository.model.Cie10;
import es.plexus.hopes.hopesback.repository.model.Cie9;
import es.plexus.hopes.hopesback.repository.model.Hospital;
import es.plexus.hopes.hopesback.service.mapper.Cie10Mapper;
import es.plexus.hopes.hopesback.service.mapper.Cie9Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Log4j2
@Service
@RequiredArgsConstructor
public class CieService {

    private static final String CALLING_DB = "Calling BD...";
    public static final String CIE_9 = "CIE9";

    private final Cie9Repository cie9Repository;
    private final Cie10Repository cie10Repository;
    private final HospitalService hospitalService;

    public Page<CieDTO> findAll(Long hospitalId, final Pageable pageable) {
        log.debug(CALLING_DB);
        Hospital hospital = hospitalService.getOneHospitalById(hospitalId).orElse(null);
        Page<CieDTO> cieDTOPage;

        if (CIE_9.equalsIgnoreCase(Objects.requireNonNull(hospital).getCie())) {
            Page<Cie9> cie9Page = cie9Repository.findAll(pageable);
            cieDTOPage = cie9Page.map(Cie9Mapper.INSTANCE::entityToDto);
        } else {
            Page<Cie10> cie10Page = cie10Repository.findAll(pageable);
            cieDTOPage = cie10Page.map(Cie10Mapper.INSTANCE::entityToDto);
        }

        return cieDTOPage;
    }


    public Page<CieDTO> findCieByCodeOrSearch(Long hospitalId, String search, Pageable pageable) {
        log.debug(CALLING_DB);
        Hospital hospital = hospitalService.getOneHospitalById(hospitalId).orElse(null);
        Page<CieDTO> cieDTOPage;

        if (CIE_9.equalsIgnoreCase(Objects.requireNonNull(hospital).getCie())) {
            Page<Cie9> cie9Page = cie9Repository.findCieByCodeOrSearch(search, pageable);
            cieDTOPage = cie9Page.map(Cie9Mapper.INSTANCE::entityToDto);
        } else {
            Page<Cie10> cie10Page = cie10Repository.findCieByCodeOrSearch(search, pageable);
            cieDTOPage = cie10Page.map(Cie10Mapper.INSTANCE::entityToDto);
        }

        return cieDTOPage;
    }
}
