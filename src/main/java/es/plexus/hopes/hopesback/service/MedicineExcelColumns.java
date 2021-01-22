package es.plexus.hopes.hopesback.service;

import lombok.Getter;

@Getter
public enum MedicineExcelColumns {

    NATIONAL_CODE("COD_NACION", 0),
    ACT_INGREDIENTS("PRINCIPIO_ACTIVO", 1),
    ACRONYM("ABREVIATURA", 2),
    DESCRIPTION("DES_NOMCO_REG", 3),
    CONTENT("CONTENIDO", 4),
    PRESENTATION("DES_PRESE", 5),
    CODE_ACT("COD_ATC", 6),
    AUTHORIZATION_DATE("FECHA_AUTORIZACION", 7),
    AUTHORIZED("AUTORIZADO", 8),
    END_DATE_AUTHORIZATION("FEC_FIN_AUTORIZACION", 9),
    COMMERCIALIZATION("SW_COMERCIALIZADO", 10),
    COMMERCIALIZATION_DATE("FEC_COMER", 11),
    END_DATE_COMMERCIALIZATION("FEC_FIN_COMERCIALIZACION", 12),
    VIA_ADMINISTRATION("VIA_ADMINISTRACION", 13),
    BRAND("MARCA", 14),
    UNITS_CONT("UNIDADES_CONT", 15),
    UNITS_DOSE("DOSIS_UNIDAD", 16),
    PVL("PVL", 17),
    PVP("PVP", 18),
    PATHOLOGY("PATOLOGIA", 19),
    FAMILY("FAMILIA", 20),
    SUBFAMILY("SUBFAMILIA", 21),
    TREATMENT_TYPE("TIPO_TRATAMIENTO", 22);

    private final String name;
    private final int number;

    MedicineExcelColumns(String name, int number) {
        this.name = name;
        this.number = number;
    }
}
