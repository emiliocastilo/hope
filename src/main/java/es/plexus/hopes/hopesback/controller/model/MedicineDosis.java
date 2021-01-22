package es.plexus.hopes.hopesback.controller.model;

import es.plexus.hopes.hopesback.repository.model.Medicine;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class MedicineDosis {

    String actIngredient;
    List<Map<String, String>> regimes;

    public String getActIngredient() {
        return actIngredient;
    }

    public void setActIngredient(String actIngredient) {
        this.actIngredient = actIngredient;
    }

    public List<Map<String, String>> getRegimes() {
        return regimes;
    }

    public void setRegimes(List<Map<String, String>> regimes) {
        this.regimes = regimes;
    }
}
