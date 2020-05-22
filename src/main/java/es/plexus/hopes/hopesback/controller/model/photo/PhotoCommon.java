package es.plexus.hopes.hopesback.controller.model.photo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class PhotoCommon {

	@ApiModelProperty(position = 10, example = "1", value = "Identificador de la foto en BD")
	private Long id;

	@ApiModelProperty(position = 20, example = "Dermatosis cenicienta", value = "Título de la foto")
	private String title;

	@ApiModelProperty(position = 30, example = "La dermatosis cenicienta es una hipermelanosis...", value = "Descripción de la foto")
	private String description;

	@ApiModelProperty(position = 40, example = "dermatosis", value = "Nombre de la imagen")
	private String name;

	@ApiModelProperty(position = 50, example = ".png", value = "Extensión de la imagen")
	private String typePhoto;

	@ApiModelProperty(position = 60, value = "Foto")
	private String photoBytes;
}
