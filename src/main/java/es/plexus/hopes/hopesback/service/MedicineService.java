package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.MedicineDTO;
import es.plexus.hopes.hopesback.repository.MedicineRepository;
import es.plexus.hopes.hopesback.repository.model.Medicine;
import es.plexus.hopes.hopesback.repository.model.Recommendation;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.service.mapper.MedicineMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
public class MedicineService {

	private static final String CALLING_DB = "Llamando a la DB...";
	public static final String DEVELOPER_MESSAGE_RECOMMENDATION_MANDATORY =
			"Recommendation not found. If checked Recommended field, Recommendation is mandatory for the medicine";

	private final MedicineRepository medicineRepository;
	private final RecommendationService recommendationService;

	public MedicineDTO save(MedicineDTO medicineDto) throws ServiceException {
		Medicine medicine = addMedicineCommon(medicineDto);
		log.debug(CALLING_DB);
		return MedicineMapper.INSTANCE.entityToDto(medicineRepository.save(medicine));
	}

	public void saveAll(MultipartFile multipartFile) throws ServiceException {
		log.debug(CALLING_DB);

		Workbook workbook = validWorkbook(multipartFile);
		Sheet sheetMedicine = workbook.getSheetAt(0);
		Sheet sheetDose = workbook.getSheetAt(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Map<String, Integer> nameAndColumnMap = new HashMap<>();

		// Fixme crear un fichero de log, para saber en que línea ha fallado, añadirlo en el mismo resource
		for (Row row : sheetMedicine) {
			Medicine medicine = new Medicine();
			try {

				for (Cell cell : row) {
					//Suponemos que nunca se cambiarán las columnas de sitio
					if (row.getRowNum() != 0) {
						if (cell.getColumnIndex() == MedicineExcelColumns.NATIONAL_CODE.getNumber()) {
							medicine.setNationalCode(String.valueOf((int) cell.getNumericCellValue()));
						} else if (cell.getColumnIndex() == MedicineExcelColumns.ACT_INGREDIENTS.getNumber()) {
							medicine.setActIngredients(cell.getStringCellValue());
						} else if (cell.getColumnIndex() == MedicineExcelColumns.ACRONYM.getNumber()) {
							medicine.setAcronym(cell.getStringCellValue());
						} else if (cell.getColumnIndex() == MedicineExcelColumns.DESCRIPTION.getNumber()) {
							medicine.setDescription(cell.getStringCellValue());
						} else if (cell.getColumnIndex() == MedicineExcelColumns.CONTENT.getNumber()) {
							medicine.setContent(cell.getStringCellValue());
						} else if (cell.getColumnIndex() == MedicineExcelColumns.PRESENTATION.getNumber()) {
							medicine.setPresentation(cell.getStringCellValue());
						} else if (cell.getColumnIndex() == MedicineExcelColumns.CODE_ACT.getNumber()) {
							medicine.setCodeAct(cell.getStringCellValue());
						} else if (cell.getColumnIndex() == MedicineExcelColumns.AUTHORIZATION_DATE.getNumber()) {
							if (cell.getCellType() == CellType.STRING || cell.getCellType() == CellType.BLANK) {
								medicine.setAuthorizationDate(cell.getStringCellValue().isEmpty() ? null : LocalDate.parse(cell.getStringCellValue(), formatter));
							} else {
								medicine.setAuthorizationDate(cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
							}
						} else if (cell.getColumnIndex() == MedicineExcelColumns.AUTHORIZED.getNumber()) {
							medicine.setAuthorized(cell.getNumericCellValue() > 0);
						} else if (cell.getColumnIndex() == MedicineExcelColumns.END_DATE_AUTHORIZATION.getNumber()) {
							if (cell.getCellType() == CellType.STRING || cell.getCellType() == CellType.BLANK) {
								medicine.setEndDateAuthorization(cell.getStringCellValue().isEmpty() ? null : LocalDate.parse(cell.getStringCellValue(), formatter));
							} else {
								medicine.setEndDateAuthorization(cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
							}
						} else if (cell.getColumnIndex() == MedicineExcelColumns.COMMERCIALIZATION.getNumber()) {
							medicine.setCommercialization(cell.getNumericCellValue() > 0);
						} else if (cell.getColumnIndex() == MedicineExcelColumns.COMMERCIALIZATION_DATE.getNumber()) {
							if (cell.getCellType() == CellType.STRING || cell.getCellType() == CellType.BLANK) {
								medicine.setCommercializationDate(cell.getStringCellValue().isEmpty() ? null : LocalDate.parse(cell.getStringCellValue(), formatter));
							} else {
								medicine.setCommercializationDate(cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
							}
						} else if (cell.getColumnIndex() == MedicineExcelColumns.END_DATE_COMMERCIALIZATION.getNumber()) {
							if (cell.getCellType() == CellType.STRING || cell.getCellType() == CellType.BLANK) {
								medicine.setEndDateCommercialization(cell.getStringCellValue().isEmpty() ? null : LocalDate.parse(cell.getStringCellValue(), formatter));
							} else {
								medicine.setEndDateCommercialization(cell.getDateCellValue().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
							}
						} else if (cell.getColumnIndex() == MedicineExcelColumns.VIA_ADMINISTRATION.getNumber()) {
							medicine.setViaAdministration(cell.getStringCellValue());
						} else if (cell.getColumnIndex() == MedicineExcelColumns.BRAND.getNumber()) {
							medicine.setBrand(cell.getStringCellValue());
						} else if (cell.getColumnIndex() == MedicineExcelColumns.UNITS.getNumber()) {
							medicine.setUnits(BigDecimal.valueOf(cell.getNumericCellValue()));
						} else if (cell.getColumnIndex() == MedicineExcelColumns.PVL.getNumber()) {
							medicine.setPvl(BigDecimal.valueOf(cell.getNumericCellValue()));
						} else if (cell.getColumnIndex() == MedicineExcelColumns.PVP.getNumber()) {
							medicine.setPvp(BigDecimal.valueOf(cell.getNumericCellValue()));
						} else if (cell.getColumnIndex() == MedicineExcelColumns.PATHOLOGY.getNumber()) {
							medicine.setPathology(cell.getStringCellValue());
						} else if (cell.getColumnIndex() == MedicineExcelColumns.FAMILY.getNumber()) {
							medicine.setFamily(cell.getStringCellValue());
							medicine.setBiologic(medicine.getFamily().equals("TRATAMIENTO BIOLÓGICO"));
						} else if (cell.getColumnIndex() == MedicineExcelColumns.SUBFAMILY.getNumber()) {
							medicine.setSubfamily(cell.getStringCellValue());
						}
					}
				}
				if (row.getRowNum() != 0) {
					if (BigDecimal.ZERO.compareTo(medicine.getUnits()) != 0) {
						medicine.setPvlUnitary(medicine.getPvl().divide(medicine.getUnits(), 2));
					}

					medicineRepository.save(medicine);
				}
			} catch (Exception e) {
				log.error(e.getMessage());
				log.error(Arrays.toString(e.getStackTrace()));
			}
		}
	}

	/**
	 * Método para devolver un libro de trabajo válido
	 *
	 * @param multipartFile file
	 * @return workbook
	 * @throws IOException exception
	 */
	private Workbook validWorkbook(MultipartFile multipartFile) {
		try {
			if (multipartFile != null && Objects.equals(FilenameUtils.getExtension(multipartFile.getOriginalFilename()), "xlsx")) {

				return new XSSFWorkbook(multipartFile.getInputStream());

			} else if (multipartFile != null && Objects.equals(FilenameUtils.getExtension(multipartFile.getOriginalFilename()), "xls")) {
				return new HSSFWorkbook(multipartFile.getInputStream());
			}
			// Si no es ninguno de los anteriores es que el archivo que se quiere cargar es incorrecto
			throw ServiceExceptionCatalog.BAD_FILE_EXTENSION_EXCEPTION.exception();

		} catch (IOException e) {
			throw ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception(e.getMessage());
		}
	}

	public MedicineDTO findById(Long id) {
		MedicineDTO medicineDto = null;
		log.debug(CALLING_DB);
		Medicine dispensationDetail = medicineRepository.findById(id).orElse(null);
		if(Objects.nonNull(dispensationDetail)) {
			medicineDto = Optional.of(MedicineMapper.INSTANCE.entityToDto(dispensationDetail)).get();
		}
		return medicineDto;
	}

	public void deleteById(Long id) {
		log.debug(CALLING_DB);
		medicineRepository.deleteById(id);
	}

	public Page<MedicineDTO> findAllMedicines(final Pageable pageable) {
		log.debug(CALLING_DB);
		Page<Medicine> page = medicineRepository.findAll(pageable);
		return page.map(MedicineMapper.INSTANCE::entityToDto);
	}

	public Page<MedicineDTO> findMedicinesBySearch(String search, Pageable pageable) {
		log.debug(CALLING_DB);
		Page<Medicine> page = medicineRepository.findMedicinesBySearch(search, pageable);

		return page.map(MedicineMapper.INSTANCE::entityToDto);
	}

	public Page<MedicineDTO> filterMedicines(String json, Pageable pageable) {

		Medicine medicine = MedicineMapper.INSTANCE.jsonToEntity(json);

		ExampleMatcher matcher = exampleByFilter();

		Example<Medicine> example = Example.of(medicine, matcher);
		log.debug(CALLING_DB);
		Page<Medicine> page = medicineRepository.findAll(example, pageable);

		return page.map(MedicineMapper.INSTANCE::entityToDto);
	}

	private Medicine addMedicineCommon(MedicineDTO medicineDto) throws ServiceException {
		Medicine medicine = MedicineMapper.INSTANCE.dtoToEntity(medicineDto);
		if(medicineDto.isRecommended()){
			if (Objects.isNull(medicineDto.getRecommendation())){
				throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION
						.exception(DEVELOPER_MESSAGE_RECOMMENDATION_MANDATORY);
			}

			Optional<Recommendation> recommendation = recommendationService
					.findById(medicineDto.getRecommendation().getId());
			if (!recommendation.isPresent()){
				throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION
						.exception(DEVELOPER_MESSAGE_RECOMMENDATION_MANDATORY);
			}
			medicine.setRecommendation(recommendation.get());
		}

		return medicine;
	}

	private ExampleMatcher exampleByFilter() {
		final String[] IGNORE_PATHS = {"commercialization", "recommended"};
		return ExampleMatcher.matchingAll()
				.withIgnorePaths(IGNORE_PATHS)
				.withIgnoreCase(true)
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
	}
}
