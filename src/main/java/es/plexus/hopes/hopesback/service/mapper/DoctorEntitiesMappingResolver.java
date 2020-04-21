package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.DoctorDTO;
import es.plexus.hopes.hopesback.repository.DoctorRepository;
import es.plexus.hopes.hopesback.repository.HospitalRepository;
import es.plexus.hopes.hopesback.repository.RoleRepository;
import es.plexus.hopes.hopesback.repository.ServiceRepository;
import es.plexus.hopes.hopesback.repository.UserRepository;
import es.plexus.hopes.hopesback.repository.model.Doctor;
import es.plexus.hopes.hopesback.repository.model.Hospital;
import es.plexus.hopes.hopesback.repository.model.Role;
import es.plexus.hopes.hopesback.repository.model.Service;
import es.plexus.hopes.hopesback.repository.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class DoctorEntitiesMappingResolver implements DoctorMapper {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private HospitalRepository hospitalRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	@Qualifier("delegate")
	private DoctorMapper delegate;

	public Doctor doctorDTOToDoctorConverter(final DoctorDTO doctorDTO) {
		final Service service = serviceRepository.getOne(doctorDTO.getServiceId());

		final Doctor doctor = delegate.doctorDTOToDoctorConverter(doctorDTO);
		doctor.setService(service);

		createUser(doctor, doctorDTO);

		return doctor;
	}

	private void createUser(Doctor doctor, DoctorDTO doctorDTO) {
		final Hospital hospital = hospitalRepository.getOne(doctorDTO.getHospitalId());
		final Set<Role> roles = findRoles(doctorDTO.getRoleList());

		Long userId = checkUserExistence(doctorDTO);

		User doctorUser;

		if (userId == null) {
			final User user = new User();
			user.setDateCreation(LocalDate.now());
			setUserAttributes(doctorDTO, hospital, roles, user);
			doctorUser = user;
		} else {
			final User storedUser = userRepository.getOne(userId);
			storedUser.setDateCreation(storedUser.getDateCreation());
			setUserAttributes(doctorDTO, hospital, roles, storedUser);
			doctorUser = storedUser;
		}

		doctor.setUser(doctorUser);
	}

	private Long checkUserExistence(DoctorDTO doctorDTO) {
		Long userId = null;
		if (doctorDTO.getId() != null) {
			Doctor storedDoctor = doctorRepository.getOne(doctorDTO.getId());
			if (storedDoctor.getUser() != null) {
				userId = storedDoctor.getUser().getId();
			}
		}
		return userId;
	}

	private void setUserAttributes(DoctorDTO doctorDTO, Hospital hospital, Set<Role> roles, User user) {
		user.setDateModification(LocalDate.now());
		user.setActive(true);
		user.setHospital(hospital);
		user.setRoles(roles);

		user.setUsername(doctorDTO.getUsername());
		user.setPassword(doctorDTO.getPassword());
		user.setEmail(doctorDTO.getEmail());
	}

	private Set<Role> findRoles(List<Long> longs) {
		return longs.stream().map(aLong -> roleRepository.getOne(aLong)).collect(Collectors.toSet());
	}
}
