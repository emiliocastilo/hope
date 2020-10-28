package es.plexus.hopes.hopesback.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import es.plexus.hopes.hopesback.controller.model.UserDTO;
import es.plexus.hopes.hopesback.controller.model.UserSimpleDTO;
import es.plexus.hopes.hopesback.controller.model.UserViewDTO;
import es.plexus.hopes.hopesback.repository.model.Role;
import es.plexus.hopes.hopesback.repository.model.User;
import org.hibernate.service.spi.ServiceException;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

	@Mappings({
			@Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToRoleIdListConverter")
	})
	UserDTO userToUserDTOConverter(final User user);

	@Mappings({
			@Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToRoleIdListConverter")
	})
	@Named("userToUserSimpleDTOConverter")
	UserSimpleDTO userToUserSimpleDTOConverter(final User user);

	@Mappings({
			@Mapping(target = "roles", ignore = true)
	})
	User userDTOToUserConverter(final UserDTO userDTO);

	@Mappings({
			@Mapping(source = "roles", target = "roles", qualifiedByName = "rolesToRoleIdListConverter")
	})
	UserViewDTO userToUserViewDTOConverter(final User user);

	@Mappings({
			@Mapping(target = "roles", ignore = true)
	})
	User userViewDTOToUserConverter(final UserViewDTO userViewDTO);

	@Named("rolesToRoleIdListConverter")
	default Set<Long> rolesToRoleIdListConverter(Set<Role> roles) {
		return roles.stream().map(Role::getId).collect(Collectors.toSet());
	}

	@Named("jsonToUserDTOConverter")
	default UserDTO jsonToUserDTOConverter(String user) {
		UserDTO userDTO;

		try {
			userDTO = new ObjectMapper().readValue(user, UserDTO.class);
		} catch (JsonMappingException e) {
			throw new ServiceException("Fields not belonging to the object are being sent " + e.getMessage());
		} catch (JsonProcessingException e) {
			throw new ServiceException("Filter processing error occurred " + e.getMessage());
		}

		return userDTO;
	}
}
