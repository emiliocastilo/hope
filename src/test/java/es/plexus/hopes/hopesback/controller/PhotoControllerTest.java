package es.plexus.hopes.hopesback.controller;

import es.plexus.hopes.hopesback.controller.model.photo.PhotoDTO;
import es.plexus.hopes.hopesback.controller.model.photo.PhotoViewDTO;
import es.plexus.hopes.hopesback.service.PhotoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class PhotoControllerTest {

	@Mock
	private PhotoService photoService;

	@InjectMocks
	private PhotoController photoController;

	@Test
	public void getAllPhotosShouldBeStatusOk() {
		// given
		given(photoService.getAllPhotos(anyLong(), anyLong()))
				.willReturn(Collections.singletonList(mockPhotoViewDTO()));

		// when
		List<PhotoViewDTO> response = photoController.findAllPhoto(1L, 1L);

		// then
		assertNotNull(response);
		assertFalse(response.isEmpty());
	}

	@Test
	public void getOnePhotosShouldBeStatusOk() {
		// given
		given(photoService.getOnePhotoById(anyLong()))
				.willReturn(mockPhotoViewDTO());

		// when
		PhotoViewDTO response = photoController.findOnePhoto(1L);

		// then
		assertNotNull(response);
	}

	@Test
	public void addOnePhotoShouldBeStatusCreated() {
		// given
		given(photoService.addPhoto(any(PhotoDTO.class)))
				.willReturn(mockPhotoViewDTO());

		// when
		PhotoViewDTO response = photoController.addPhoto(mockPhotoDTO());

		// then
		assertNotNull(response);
	}

	@Test
	public void updatePhotoShouldBeStatusOk() {
		// given
		given(photoService.updatePhoto(any(PhotoDTO.class)))
				.willReturn(mockPhotoViewDTO());

		// when
		PhotoViewDTO response = photoController.updatePhoto(mockPhotoDTO());

		// then
		assertNotNull(response);
	}

	@Test
	public void deleteOnePhotosShouldBeStatusNoContent() {
		// when
		photoService.deletePhotoById(1L);

		// then
		verify(photoService, times(1)).deletePhotoById(anyLong());
	}

	@Test
	public void deleteAllPhotosShouldBeStatusNoContent() {
		// when
		photoService.deleteAllPhotos();

		// then
		verify(photoService, times(1)).deleteAllPhotos();
	}

	@Test
	public void generateQRPhotosShouldBeStatusNoContent() throws Exception {
		// given
		HttpServletResponse response = mock(HttpServletResponse.class);
		given(photoService.generateQRCodeImage(anyLong(), anyLong(), anyString(), any(HttpServletResponse.class)))
				.willReturn("test");

		// when
		String outputStream = photoController.generateQR(1L, 1L, "test", response);

		// then
		assertNotNull(outputStream);
	}

	private PhotoViewDTO mockPhotoViewDTO() {
		final PhotoViewDTO photoViewDTO = new PhotoViewDTO();
		photoViewDTO.setId(1L);
		photoViewDTO.setTitle("Title");
		photoViewDTO.setDescription("Description");
		return photoViewDTO;
	}

	private PhotoDTO mockPhotoDTO() {
		final PhotoDTO photoDTO = new PhotoDTO();
		photoDTO.setId(1L);
		photoDTO.setTitle("Title");
		photoDTO.setDescription("Description");
		return photoDTO;
	}
}
