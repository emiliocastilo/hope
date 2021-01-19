package es.plexus.hopes.hopesback.controller.model;

import es.plexus.hopes.hopesback.repository.model.Medicine;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MedicineDosis {

    Medicine medicine;
    List<Map<String, String>> regimes;

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public List<Map<String, String>> getRegimes() {
        return regimes;
    }

    public void setRegimes(List<Map<String, String>> regimes) {
        this.regimes = regimes;
    }
}
