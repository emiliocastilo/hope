//package es.plexus.hopes.hopesback.controller;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.BDDMockito.given;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//import org.hibernate.service.spi.ServiceException;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//
//import es.plexus.hopes.hopesback.controller.model.DetailGraphDTO;
//import es.plexus.hopes.hopesback.controller.model.PatientDosesInfoDTO;
//import es.plexus.hopes.hopesback.controller.model.TreatmentInfoDTO;
//import es.plexus.hopes.hopesback.service.GraphsService;
//
//@RunWith(MockitoJUnitRunner.Silent.class)
//public class GraphsControllerTest {
//
//	@Mock
//	private GraphsService graphsService;
//
//	@InjectMocks
//	private GraphsController graphsController;
//
//	@Test
//	public void callPatientsUnderTreatmentShouldBeStatusOk() {
//
//		// given
//		given(graphsService.patientsUnderTreatment(anyString(), anyString()))
//				.willReturn(mockTreatmentList());
//
//		// when
//		List<TreatmentInfoDTO> response = graphsController.patientsUnderTreatment(anyString(), anyString());
//
//		// then		
//		Assert.assertNotNull(response);
//		Assert.assertTrue(!response.isEmpty());
//	}
//
//	@Test(expected = ServiceException.class)
//	public void callPatientsUnderTreatmentThrowException() throws Exception {
//		// given
//		given(graphsService.patientsUnderTreatment(anyString(), anyString()))
//				.willThrow(new ServiceException("Error: No contled error"));
//
//		// when
//		List<TreatmentInfoDTO> response = graphsController.patientsUnderTreatment(anyString(), anyString());
//
//		Assert.assertEquals(response, HttpStatus.BAD_REQUEST);
//		Assert.assertNull(response);
//	}
//	
//	@Test
//	public void callInfoPatientsDosesShouldBeStatusOk() {
//
//		// given
//		given(graphsService.infoPatientsDoses())
//				.willReturn(mockPatientDosesInfoList());
//
//		// when
//		List<PatientDosesInfoDTO> response = graphsController.infoPatientsDoses();
//
//		// then		
//		Assert.assertNotNull(response);
//		Assert.assertTrue(!response.isEmpty());
//	}
//		
//	@Test(expected = ServiceException.class)
//	public void callInfoPatientsDosesThrowException() throws Exception {
//		// given
//		given(graphsService.infoPatientsDoses())
//				.willThrow(new ServiceException("Error: No contled error"));
//
//		// when
//		List<PatientDosesInfoDTO> response = graphsController.infoPatientsDoses();
//
//		Assert.assertEquals(response, HttpStatus.BAD_REQUEST);
//		Assert.assertNull(response);
//	}
//	
//	@Test
//	public void callDetailsGrapthsShouldBeStatusOk() {
//
//		// given
//		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("patient"));
//		given(graphsService.detailsGrapths(anyString(), anyString(), any(Pageable.class)))
//				.willReturn(getPageableDetailGraph(pageRequest));
//
//		// when
//		Page<DetailGraphDTO> response = graphsController.detailsGrapths("BIOLOGICOS", "Psoriasis en placas", pageRequest);
//
//		// then		
//		Assert.assertNotNull(response);
//		Assert.assertTrue(!response.isEmpty());
//	}
//	
//	@Test(expected = ServiceException.class)
//	public void callDetailsGrapthsThrowException() throws Exception {
//		// given
//		final PageRequest pageRequest = PageRequest.of(1, 5, Sort.by("patient"));
//		given(graphsService.detailsGrapths(anyString(), anyString(), any(Pageable.class)))
//				.willThrow(new ServiceException("Error: No contled error"));
//
//		// when
//		Page<DetailGraphDTO> response = graphsController.detailsGrapths("BIOLOGICOS", "Psoriasis en placas", pageRequest);
//
//		Assert.assertEquals(response, HttpStatus.BAD_REQUEST);
//		Assert.assertNull(response);
//	}
//	
//	//Mocks
//	private List<TreatmentInfoDTO> mockTreatmentList() {
//		List<TreatmentInfoDTO> treatmentInfoDTOList = new ArrayList<TreatmentInfoDTO>();
//
//		TreatmentInfoDTO treatmentInfoDTO = mockTreatmentInfoDTO();
//		treatmentInfoDTOList.add(treatmentInfoDTO);
//
//		return  treatmentInfoDTOList;
//	}
//
//
//	private TreatmentInfoDTO mockTreatmentInfoDTO() {
//		TreatmentInfoDTO treatmentInfoDTO = new TreatmentInfoDTO("ACT code", "Act Ingredient", 1L);
//		return treatmentInfoDTO;
//	}
//	
//	private List<PatientDosesInfoDTO> mockPatientDosesInfoList() {
//		List<PatientDosesInfoDTO> patientDosesInfoDTOList = new ArrayList<PatientDosesInfoDTO>();
//
//		PatientDosesInfoDTO patientDosesInfoDTO = mockPatientDosesInfoDTO();
//		patientDosesInfoDTOList.add(patientDosesInfoDTO);
//
//		return  patientDosesInfoDTOList;
//	}
//
//
//	private PatientDosesInfoDTO mockPatientDosesInfoDTO() {
//		PatientDosesInfoDTO patientDosesInfoDTO = new PatientDosesInfoDTO("Desinfectada", 1L);
//		return patientDosesInfoDTO;
//	}
//	
//	private DetailGraphDTO mockDetailGraphDTO() {
//		DetailGraphDTO detailGraphDTO = new DetailGraphDTO();
//		detailGraphDTO.setNhc("1234");
//		detailGraphDTO.setSip("1234");
//		detailGraphDTO.setPatient("Antonio Diaz Alonso");
//		detailGraphDTO.setIndication("Indicacion1");
//		detailGraphDTO.setDiagnostig("Diagnostio1");
//		detailGraphDTO.setTreatment("Tratamiento1");
//		detailGraphDTO.setPasi("3");
//		detailGraphDTO.setDatePasi(LocalDateTime.now());
//		return detailGraphDTO;
//	}
//	
//	private PageImpl<DetailGraphDTO> getPageableDetailGraph(PageRequest pageRequest) {
//		return new PageImpl<>(Collections.singletonList(mockDetailGraphDTO()), pageRequest, 1);
//	}
//
//	
//}
//
