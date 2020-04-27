package es.plexus.hopes.hopesback.controller.validation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class JsonValidator implements ConstraintValidator<IsValidJson, String> {

    @Override
    public void initialize(IsValidJson contactNumber) {
    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext cxt) {
        try {
            new JSONObject(field);
        } catch (JSONException ex) {
            try {
                new JSONArray(field);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }

}
