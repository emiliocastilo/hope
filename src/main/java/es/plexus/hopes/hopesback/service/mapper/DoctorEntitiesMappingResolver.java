package es.plexus.hopes.hopesback.service.mapper;

import es.plexus.hopes.hopesback.controller.model.DoctorDTO;
import es.plexus.hopes.hopesback.repository.HospitalRepository;
import es.plexus.hopes.hopesback.repository.RoleRepository;
import es.plexus.hopes.hopesback.repository.ServiceRepository;
import es.plexus.hopes.hopesback.repository.model.Doctor;
import es.plexus.hopes.hopesback.repository.model.Hospital;
import es.plexus.hopes.hopesback.repository.model.Role;
import es.plexus.hopes.hopesback.repository.model.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class DoctorEntitiesMappingResolver implements DoctorMapper {

	@Autowired
	private ServiceRepository serviceRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private HospitalRepository hospitalRepository;

	@Autowired
	@Qualifier("delegate")
	private DoctorMapper delegate;

	public Doctor doctorDTOToDoctorConverter(final DoctorDTO doctorDTO) {
		final Service service = serviceRepository.getOne(doctorDTO.getServiceId());
		final Hospital hospital = hospitalRepository.getOne(doctorDTO.getUser().getHospitalId());
		final Set<Role> roles = findRoles(doctorDTO.getUser().getRoleList());

		final Doctor doctor = delegate.doctorDTOToDoctorConverter(doctorDTO);
		doctor.setService(service);
		doctor.getUser().setHospital(hospital);
		doctor.getUser().setRoles(roles);

		return doctor;
	}

	private Set<Role> findRoles(List<Long> longs) {
		return longs.stream().map(aLong -> roleRepository.getOne(aLong)).collect(Collectors.toSet());
	}
}
