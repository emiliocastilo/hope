package es.plexus.hopes.hopesback.controller.model.photo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class PhotoViewDTO extends PhotoCommon {

	@ApiModelProperty(position = 10, value = "Fecha de creación")
	private String dateCreated;
}
