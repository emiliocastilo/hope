package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.ServiceDTO;
import es.plexus.hopes.hopesback.service.ServiceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class ServiceControllerTest {

	@Mock
	private ServiceService serviceService;

	@InjectMocks
	private ServiceController serviceController;

	@Test
	public void getAllServicesShouldBeStatusOk() {
		// given
		given(serviceService.getAllServices()).willReturn(Collections.singletonList(mockServiceDTO()));

		// when
		List<ServiceDTO> response = serviceController.getAllServices();

		// then
		assertNotNull(response);
	}

	private ServiceDTO mockServiceDTO() {
		final ServiceDTO serviceDTO = new ServiceDTO();
		serviceDTO.setId(1L);
		serviceDTO.setName("Test");

		return serviceDTO;
	}
}
