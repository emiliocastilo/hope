package es.plexus.hopes.hopesback.controller.model;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MedicineDosis {

    String medicine;
    List<Map<String, String>> regimes;

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public List<Map<String, String>> getRegimes() {
        return regimes;
    }

    public void setRegimes(List<Map<String, String>> regimes) {
        this.regimes = regimes;
    }
}
