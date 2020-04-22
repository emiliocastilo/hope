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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class DoctorEntitiesMappingResolver implements DoctorMapper {

	private static final LocalDate NOW = LocalDate.now();

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
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	@Qualifier("delegate")
	private DoctorMapper delegate;

	public Doctor doctorDTOToDoctorConverter(final DoctorDTO doctorDTO) {
		final Doctor storedDoctor = searchStoredRecord(doctorDTO);
		final Service service = resolveDoctorServices(doctorDTO);

		final Doctor doctor = delegate.doctorDTOToDoctorConverter(doctorDTO);
		doctor.setService(service);
		doctor.setDateModify(NOW);

		if (isExistingRecord(storedDoctor)) {
			doctor.setDateCreate(storedDoctor.getDateCreate());
			doctor.setActive(storedDoctor.getActive());
		} else {
			doctor.setDateCreate(NOW);
			doctor.setActive(true);
		}

		createUser(doctor, doctorDTO, storedDoctor);

		return doctor;
	}

	private Doctor searchStoredRecord(DoctorDTO doctorDTO) {
		Doctor storedDoctor = null;
		if (doctorDTO.getId() != null) {
			storedDoctor = doctorRepository.getOne(doctorDTO.getId());
		}
		return storedDoctor;
	}

	private Service resolveDoctorServices(DoctorDTO doctorDTO) {
		Service service = null;
		//todo Implementar el crud de servicios para evitar que pete en el front y eliminar el id
		if (doctorDTO.getServiceId() != null) {
			service = serviceRepository.getOne(1L);
		}
		return serviceRepository.getOne(1L);
	}

	//todo Implementar el crud de hospitales y roles para evitar que pete en el front y eliminar el id
	private void createUser(Doctor doctor, DoctorDTO doctorDTO, Doctor storedDoctor) {
		//todo controlar que los valores no vengan a null cuando tengamos los crud
		final Hospital hospital = hospitalRepository.getOne(1L);
		final Set<Role> roles = findRoles(Arrays.asList(1L, 2L));

		final Long userId = getIdOfExistingUser(storedDoctor);

		User doctorUser;

		if (userId == null) {
			final User user = new User();
			user.setDateCreation(NOW);
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

	private Set<Role> findRoles(List<Long> longs) {
		return longs.stream().map(aLong -> roleRepository.getOne(aLong)).collect(Collectors.toSet());
	}

	private Long getIdOfExistingUser(Doctor storedDoctor) {
		return (isExistingRecord(storedDoctor) && storedDoctor.getUser() != null) ? storedDoctor.getUser().getId() : null;
	}

	private void setUserAttributes(DoctorDTO doctorDTO, Hospital hospital, Set<Role> roles, User user) {
		user.setDateModification(NOW);
		user.setActive(true);
		user.setHospital(hospital);
		user.setRoles(roles);

		user.setUsername(doctorDTO.getUsername());
		user.setPassword(bCryptPasswordEncoder.encode(doctorDTO.getPassword()));
		user.setEmail(doctorDTO.getEmail());
	}

	private boolean isExistingRecord(Doctor storedDoctor) {
		return storedDoctor != null;
	}
}
