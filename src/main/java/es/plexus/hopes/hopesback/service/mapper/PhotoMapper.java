package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.photo.PhotoDTO;
import es.plexus.hopes.hopesback.controller.model.photo.PhotoViewDTO;
import es.plexus.hopes.hopesback.repository.model.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PhotoMapper {

	PhotoViewDTO photoToPhotoViewDTOConverter(final Photo photo);

	@Mappings({
			@Mapping(target = "pathology", ignore = true),
			@Mapping(target = "patient", ignore = true),
	})
	Photo photoDTOToPhotoConverter(final PhotoDTO photoDTO);
}
