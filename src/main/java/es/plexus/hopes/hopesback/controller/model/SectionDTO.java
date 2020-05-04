package es.plexus.hopes.hopesback.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@ApiModel
public class SectionDTO {

	@ApiModelProperty(position = 10, example = "1", value = "Identificador en la BD")
	private Long id;

	@ApiModelProperty(position = 20, example = "SECTIONS", value = "Título identificativo de la sección")
	@NotBlank
	private String title;

	@ApiModelProperty(position = 30, example = "Sección", value = "Valor con el que aparecerá la sección")
	@Column(name = "sec_description", nullable = true, length = 500)
	private String description;

	@ApiModelProperty(position = 40, example = "true", value = "Indicador de si está sección forma parte del menú central")
	@NotNull
	private boolean active;

	@ApiModelProperty(position = 50, example = "3", value = "Posición de la sección dentro de su nivel")
	@NotNull
	private int order;

	@ApiModelProperty(position = 60, example = "assets/img/modules/alertas.png", value = "Ruta del icono")
	private String icon;

	@ApiModelProperty(position = 70, example = "/secciones", value = "Url de la ruta")
	private String url;

	@ApiModelProperty(position = 80, example = "{id=1L}", value = "Padre de la sección")
	private SectionDTO fatherSection;

	@ApiModelProperty(position = 90, example = "[{id=1},{id=2}]", value = "Roles con permisos para poder visualizar esta sección")
	private Set<RoleDTO> roles = new HashSet<>();

}
