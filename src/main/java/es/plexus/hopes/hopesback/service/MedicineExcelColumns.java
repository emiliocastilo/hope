package es.plexus.hopes.hopesback.service;

import lombok.Getter;

@Getter
public enum MedicineExcelColumns {

    NATIONAL_CODE("cod_nacion", 0),
    ACT_INGREDIENTS("Principio activo", 1),
    ACRONYM("abreviatura", 2),
    DESCRIPTION("des_nomco_reg", 3),
    CONTENT("contenido", 4),
    PRESENTATION("des_prese", 5),
    CODE_ACT("cod_atc", 6),
    AUTHORIZATION_DATE("fecha_autorizacion", 7),
    AUTHORIZED("autorizado", 8),
    END_DATE_AUTHORIZATION("fec_fin_autorizacion", 9),
    COMMERCIALIZATION("sw_comercializado", 10),
    COMMERCIALIZATION_DATE("fec_comer", 11),
    END_DATE_COMMERCIALIZATION("fec_fin_comercializacion", 12),
    VIA_ADMINISTRATION("vía de administración", 13),
    BRAND("MARCA®", 14),
    UNITS("unidades_cont", 15),
    PVL("PVL", 16),
    PVP("PVP", 17),
    PATHOLOGY("Patología", 18),
    FAMILY("FAMILIA", 19),
    SUBFAMILY("SUBFAMILIA", 20);

    private final String name;
    private final int number;

    MedicineExcelColumns(String name, int number) {
        this.name = name;
        this.number = number;
    }
}
