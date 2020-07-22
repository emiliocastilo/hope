package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class MonitoringDrugRowDTO {

    private String analysisDate;
    private String monitoring;
    private String biologicalDrug;
    private String monitoringSystem;
    private String antibodyReference;
    private String analysisReason;
    private String sericLevels;
    private String observations;

}
