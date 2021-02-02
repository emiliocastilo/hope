package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.configuration.security.TokenProvider;
import es.plexus.hopes.hopesback.controller.model.HospitalDTO;
import es.plexus.hopes.hopesback.controller.model.MenuDTO;
import es.plexus.hopes.hopesback.controller.model.RoleDTO;
import es.plexus.hopes.hopesback.controller.model.SectionDTO;
import es.plexus.hopes.hopesback.repository.RoleRepository;
import es.plexus.hopes.hopesback.repository.model.Pathology;
import es.plexus.hopes.hopesback.repository.model.Role;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.service.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static es.plexus.hopes.hopesback.service.exception.ConstantsServiceCatalog.ROLE_NAME_VIOLATION_CONSTRAINT_MESSAGE;

@Log4j2
@Service
@RequiredArgsConstructor
public class RoleService {

	private final RoleRepository roleRepository;
	private final RoleMapper roleMapper;
	private final MenuService menuService;
	private final HospitalService hospitalService;
	private final PathologyService pathologyService;
	private final SectionService sectionService;

	public Page<RoleDTO> getAllRoles(Long idRoleSelected, final Pageable pageable) {
		RoleDTO role = getOneRoleById(idRoleSelected);
		log.debug("Calling BD. Obtener todos los roles...");
		if (role.getName().equals(Constants.ROLE_ADMIN)) {
			Page<Role> roles = roleRepository.findAll(pageable);
			return roles.map(roleMapper::roleToRoleDTOConverter);
		} else {
			return roleRepository.findRolesByRoleSelected(idRoleSelected, pageable).map(roleMapper::roleToRoleDTOConverter);
		}
	}

	public List<RoleDTO> getAllRoles(Long idRoleSelected) {
		RoleDTO role = getOneRoleById(idRoleSelected);
		log.debug("Calling BD. Obtener todos los roles...");
		if (role.getName().equals(Constants.ROLE_ADMIN)) {
			List<Role> roles = roleRepository.findAll();
			return roles.stream().map(roleMapper::roleToRoleDTOConverter).collect(Collectors.toList());
		} else {
			return roleRepository.findRolesByRoleSelected(idRoleSelected).stream().map(roleMapper::roleToRoleDTOConverter).collect(Collectors.toList());
		}
	}

	public RoleDTO getOneRoleById(final Long id) {
		final Optional<Role> role = getOneRoleByIdCommon(id);

		RoleDTO roleDTO = null;

		if (role.isPresent()) {
			roleDTO = roleMapper.roleToRoleDTOConverter(role.get());
		} else {
			log.error(String.format("RoleDto con id = %s no encontrado ...", id));
		}

		return roleDTO;
	}

	public RoleDTO addRole(final RoleDTO roleDTO) {

		roleDTO.setCode(codeRoleGenerator(roleDTO));

		log.debug("Llamando DB. Guardando record.");
		Role role = addRoleCommon(roleDTO);

		try {
			role = roleRepository.save(role);
		} catch (DataIntegrityViolationException ex) {
			throw ServiceExceptionCatalog.ROLE_CODE_VIOLATION_CONSTRAINT_EXCEPTION.exception(
					String.format("El rol con code %s ya existe para el hospital %s con patología %s",
							role.getCode(), roleDTO.getHospital().getName(), roleDTO.getPathology().getName()));
		}

		RoleDTO roleInsertedDTO = roleMapper.roleToRoleDTOConverter(role);
		// Por defecto en todos los roles se añade la relacion con la seccion padre
		if (role.getId() != null) {
			SectionDTO sectionDTO = sectionService.findByFatherSectionIsNull();
			sectionDTO.getRoles().add(roleInsertedDTO);
			sectionService.save(sectionDTO);
		}

		return roleInsertedDTO;
	}

	private void existRole(RoleDTO roleDTO) {

		Role role = null;

		if(null!=roleDTO.getId()){
			Optional<Role> optionalRole = roleRepository.findById(roleDTO.getId());
			role = optionalRole.orElseGet(null);
		}

		boolean isExistName = roleRepository.existsByName(roleDTO.getName());
		if(isExistName && (role == null || !roleDTO.getName().equals(role.getName()))){
			throw ServiceExceptionCatalog.ROLE_NAME_VIOLATION_CONSTRAINT_EXCEPTION
					.exception(ROLE_NAME_VIOLATION_CONSTRAINT_MESSAGE);
		}

	}

	public RoleDTO updateRole(final RoleDTO roleDTO) throws ServiceException {

		checkRoleExistence(roleDTO.getId());
		roleDTO.setCode(codeRoleGenerator(roleDTO));

		Role role = addRoleCommon(roleDTO);

		try {
			log.debug("Llamando DB. Registro actualizado con id=" + roleDTO.getId());
			role = roleRepository.save(role);
		} catch (DataIntegrityViolationException ex) {
			throw ServiceExceptionCatalog.ROLE_CODE_VIOLATION_CONSTRAINT_EXCEPTION.exception(
					String.format("El rol con code %s ya existe para el hospital %s con patología %s",
							role.getCode(), roleDTO.getHospital().getName(), roleDTO.getPathology().getName()));
		}

		return roleMapper.roleToRoleDTOConverter(role);
	}

	public void deleteRole(final Long id) throws ServiceException {
		checkRoleExistence(id);

		log.debug("Llamando a la DB. Registro eliminado con id=" + id);
		roleRepository.deleteById(id);
	}

	public Page<RoleDTO> findRolesBySearch(final String search, final Pageable pageable) {
		log.debug("Llamando a la DB...");
		Page<Role> rolePage = roleRepository.findByNameContainingIgnoreCase(search, pageable);

		return rolePage.map(roleMapper::roleToRoleDTOConverter);
	}

	public Set<Role> getAllRolesByIdSet(final Set<Long> roleIdSet) {
		final Set<Role> roleSet = new HashSet<>();
		roleIdSet.forEach(aLong -> getOneRoleByIdCommon(aLong).ifPresent(roleSet::add));
		return roleSet;
	}

	public RoleDTO getRoleByCode(String code) {
		Role role = roleRepository.findByCode(code).orElse(null);

		return roleMapper.roleToRoleDTOConverter(role);
	}

	public MenuDTO getMenuByRole(final Long id) throws ServiceException {
		Optional<Role> role = getOneRoleByIdCommon(id);

		if (!role.isPresent()) {
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION
					.exception(String.format("RoleDto with id = %s not found ...", id));
		}

		MenuDTO menuDTO = menuService.findMenuByRole(Collections.singletonList(role.get().getName()));

		if (menuDTO.getId() == null) {
			log.debug("Arbol no encontrado para rol " + role.get().getName());
			return null;
		}

		return menuDTO;
	}

	private Optional<Role> getOneRoleByIdCommon(Long id) {
		log.debug(String.format("Llamando a la DB. Buscando rol por id = %d", id));
		return roleRepository.findById(id);
	}

	private Role addRoleCommon(RoleDTO roleDTO) {
		return roleMapper.roleDTOToRoleConverter(roleDTO);
	}

	private void checkRoleExistence(Long id) throws ServiceException {
		final Optional<Role> storedRole = getOneRoleByIdCommon(id);
		log.debug("Comprobando si record existe.");
		if (!storedRole.isPresent()) {
			throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION
					.exception(String.format("RoleDto con id = %s no encontrado ...", id));
		}
	}

	/**
	 * Método para generar un código basado en el nombre del rol, el hospital y la patología asociada.
	 * El código es ROLE_NOMBRE ROLE_ID HOSPITAL + 2 CARACTERES DE CADA PALABRA DEL NOMBRE_ID PATOLOGIA +2 CARACTERES DE CADA PALABRA DEL NOMBRE
	 *
	 * @param roleDTO modelo
	 * @return código autogenerado
	 */
	private String codeRoleGenerator(RoleDTO roleDTO) {

		HospitalDTO hospitalDTO = hospitalService.findById(roleDTO.getHospital().getId());
		Optional<Pathology> pathology = pathologyService.getOnePathologyById(roleDTO.getPathology().getId());
		String split = "_";
		StringBuilder code = new StringBuilder();

		if (hospitalDTO != null && pathology.isPresent()) {
			String roleName = roleDTO.getName().toUpperCase().replaceAll("\\s", "");
			String[] hospitalNameArray = hospitalDTO.getName().toUpperCase().split(" ");
			StringBuilder hospitalCode = new StringBuilder(String.valueOf(hospitalDTO.getId()));
			String[] pathologyNameArray = pathology.get().getName().toUpperCase().split(" ");
			StringBuilder pathologyCode = new StringBuilder(String.valueOf(pathology.get().getId()));

			nameTrimmer(hospitalNameArray, hospitalCode);
			nameTrimmer(pathologyNameArray, pathologyCode);

			code.append("ROLE").append(split).append(roleName).append(split).append(hospitalCode).append(split)
					.append(pathologyCode);
		}

		return code.toString();
	}

	/**
	 * Método para acortar el nombre de un hospital o una patología, y así usarlo en el código del Rol
	 *
	 * @param nameArray   palabras que componen el nombre del hospital o la patología
	 * @param codeBuilder código que se usará para componer el código del rol
	 */
	private void nameTrimmer(String[] nameArray, StringBuilder codeBuilder) {

		for (String word : nameArray) {
			codeBuilder.append(word.charAt(0)).append(word.length()>1?word.charAt(1):"");
		}
	}

	public Pathology getPathologyByRoleSelected(String token) {
		String roleSelected = TokenProvider.getRoleSelected(token);
		return getPathologyByRole(roleSelected);
	}

	public Pathology getPathologyByRole (String code) {
		Role role = roleRepository.findByCode(code).orElse(null);
		Pathology pathology = new Pathology();
		if (role != null && role.getPathology() != null) {
			pathology = role.getPathology();
		}
		return pathology;
	}
}
