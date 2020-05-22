package es.plexus.hopes.hopesback.controller.model.photo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class PhotoDTO extends PhotoCommon {

	@ApiModelProperty(position = 10, example = "1", value = "Identificador de la patologia relacionada en BD")
	private Long pathologyId;

	@ApiModelProperty(position = 20, example = "1", value = "Identificador del paciente relacionado en BD")
	private Long patientId;

	@ApiModelProperty(position = 30, example = "1", value = "Identificador del usuario relacionado en BD")
	private Long userId;
}
