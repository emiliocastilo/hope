package es.plexus.hopes.hopesback.service;

import lombok.Getter;

@Getter
public enum DoseExcelColumns {

    CODE_ACT("ATC", 0),
    DESCRIPTION("DESCRIPCIÓN", 1),
    DOSE_INDICATED("DOSIS", 2);

    private final String name;
    private int number;

    DoseExcelColumns(String name, int number) {
        this.name = name;
        this.number = number;
    }
}
