package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.RecommendationDTO;
import es.plexus.hopes.hopesback.service.RecommendationService;
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
public class RecommendationControllerTest {

	@Mock
	private RecommendationService recommendationService;

	@InjectMocks
	private RecommendationController recommendationController;

	@Test
	public void callAllServicesShouldBeStatusOk() {
		// given
		given(recommendationService.findAllRecommendation()).willReturn(Collections.singletonList(mockRecommendationDTO()));

		// when
		List<RecommendationDTO> response = recommendationController.getAllServices();

		// then
		assertNotNull(response);
	}

	private RecommendationDTO mockRecommendationDTO() {
		final RecommendationDTO recommendationDTO = new RecommendationDTO();
		recommendationDTO.setId(1L);
		recommendationDTO.setValue("Test Value");

		return recommendationDTO;
	}
}
