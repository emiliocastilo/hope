package es.plexus.hopes.hopesback.service;

import com.google.common.base.Throwables;
import es.plexus.hopes.hopesback.controller.model.DoseDTO;
import es.plexus.hopes.hopesback.controller.model.MedicineDTO;
import es.plexus.hopes.hopesback.repository.DoseRepository;
import es.plexus.hopes.hopesback.repository.MedicineRepository;
import es.plexus.hopes.hopesback.repository.PathologyRepository;
import es.plexus.hopes.hopesback.repository.model.Dose;
import es.plexus.hopes.hopesback.repository.model.Medicine;
import es.plexus.hopes.hopesback.repository.model.Pathology;
import es.plexus.hopes.hopesback.service.exception.ServiceException;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.service.mapper.DoseMapper;
import es.plexus.hopes.hopesback.service.mapper.MedicineMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class MedicineService {

	private static final String CALLING_DB = "Llamando a la DB...";
	public static final String DEVELOPER_MESSAGE_RECOMMENDATION_MANDATORY =
			"Recommendation not found. If checked Recommended field, Recommendation is mandatory for the medicine";

	private final MedicineRepository medicineRepository;
	private final DoseRepository doseRepository;
	private final PathologyRepository pathologyRepository;

	public MedicineDTO save(MedicineDTO medicineDto) throws ServiceException {
		Medicine medicine = MedicineMapper.INSTANCE.dtoToEntity(medicineDto);
		log.debug(CALLING_DB);
		return MedicineMapper.INSTANCE.entityToDto(medicineRepository.save(medicine));
	}

	public void saveAll(MultipartFile multipartFile) {
		log.debug(CALLING_DB);

		Workbook workbook = validWorkbook(multipartFile);
		Sheet sheetMedicine = workbook.getSheetAt(0);
		Sheet sheetDose = workbook.getSheetAt(1);

		// Creamos un fichero de errores
		try {
			Path newFolder = Paths.get("src", "main", "resources", "logs", "excel");
			Files.createDirectories(newFolder);
			File fileLog = new File(newFolder + "/medicineAndDoses-Log.txt");

			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileLog));

			for (Row row : sheetMedicine) {
				readAndInsertMedicine(row, bufferedWriter);
			}
			for (Row row : sheetDose) {
				readAndInsertDose(row, bufferedWriter);
			}

			bufferedWriter.close();
		} catch (IOException e) {
			throw ServiceExceptionCatalog.UNKNOWN_EXCEPTION.exception(e.getMessage());
		}

	}

	private void readAndInsertDose(Row row, BufferedWriter bufferedWriter) throws IOException {
		try {
			Dose dose = new Dose();
			for (Cell cell : row) {
				if (row.getRowNum() != 0) {
					if (cell.getColumnIndex() == DoseExcelColumns.CODE_ACT.getNumber()) {
						String[] codeAtcPlusType = cell.getStringCellValue().toUpperCase().split("-");
						codeAtcPlusType[0] = codeAtcPlusType[0].trim();
						if (codeAtcPlusType.length > 1) {
							codeAtcPlusType[1] = codeAtcPlusType[1].replaceFirst("^\\\\s+", "");
							dose.setCodeAtcType(codeAtcPlusType[1]);
						}
						dose.setCodeAtc(codeAtcPlusType[0]);
					} else if (cell.getColumnIndex() == DoseExcelColumns.DESCRIPTION.getNumber()) {
						dose.setDescription(cell.getStringCellValue());
					} else if (cell.getColumnIndex() == DoseExcelColumns.DOSE_INDICATED.getNumber()) {
						dose.setDoseIndicated(cell.getStringCellValue());
					} else if (cell.getColumnIndex() == DoseExcelColumns.RECOMMENDATION.getNumber()) {
						dose.setRecommendation(cell.getStringCellValue());
					}
				}
			}
			if (row.getRowNum() != 0) {
				doseRepository.findByCodeAtcAndDescriptionAndDoseIndicated(dose.getCodeAtc()
						, dose.getDescription()
						, dose.getDoseIndicated()).ifPresent(doseToUpdate -> dose.setId(doseToUpdate.getId()));
				doseRepository.save(dose);
			}
		} catch (IllegalStateException e) {
			bufferedWriter.write(LocalDateTime.now() + " ERROR " + getClass().getName() + " - En la fila del excel de Dosis: " + row.getRowNum() + " " + Throwables.getStackTraceAsString(e));
		}
	}

	private void readAndInsertMedicine(Row row, BufferedWriter bufferedWriter) throws IOException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Medicine medicine = new Medicine();

		try {
			for (Cell cell : row) {
				//Suponemos que nunca se cambiarán las columnas de sitio
				if (row.getRowNum() != 0) {
					if (cell.getColumnIndex() == MedicineExcelColumns.NATIONAL_CODE.getNumber()) {
						medicine.setNationalCode(cell.getCellType() == CellType.STRING ? cell.getStringCellValue() : String.valueOf((int) cell.getNumericCellValue()));
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
						String[] codeAtcPlusType = cell.getStringCellValue().toUpperCase().split("-");
						codeAtcPlusType[0] = codeAtcPlusType[0].trim();
						if (codeAtcPlusType.length > 1) {
							codeAtcPlusType[1] = codeAtcPlusType[1].replaceFirst("^\\\\s+", "");
							medicine.setCodeAtcType(codeAtcPlusType[1]);
						}
						medicine.setCodeAtc(codeAtcPlusType[0].replaceAll("^\\\\s+", ""));
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
					} else if (cell.getColumnIndex() == MedicineExcelColumns.UNITS_CONT.getNumber()) {
						medicine.setUnits(BigDecimal.valueOf(cell.getNumericCellValue()));
					} else if (cell.getColumnIndex() == MedicineExcelColumns.UNITS_DOSE.getNumber()) {
						medicine.setUnitDose(BigDecimal.valueOf(cell.getNumericCellValue()));
					} else if (cell.getColumnIndex() == MedicineExcelColumns.PVL.getNumber()) {
						medicine.setPvl(BigDecimal.valueOf(cell.getNumericCellValue()));
					} else if (cell.getColumnIndex() == MedicineExcelColumns.PVP.getNumber()) {
						medicine.setPvp(BigDecimal.valueOf(cell.getNumericCellValue()));
					} else if (cell.getColumnIndex() == MedicineExcelColumns.PATHOLOGY.getNumber()) {
						String[] pathologyNames;
						if (cell.getStringCellValue().contains(",")) {
							pathologyNames = cell.getStringCellValue().toUpperCase().trim().split(",");
						} else {
							pathologyNames = cell.getStringCellValue().toUpperCase().trim().split("/");
						}
						for (int i = 0; i < pathologyNames.length; ++i) {
							if (pathologyNames[i].replaceFirst("\\s", "").equalsIgnoreCase("DERMA")) {
								pathologyNames[i] = "DERMATOLOGÍA";
							} else if (pathologyNames[i].replaceFirst("\\s", "").equalsIgnoreCase("REUMA")) {
								pathologyNames[i] = "REUMATOLOGÍA";
							}
							else if (pathologyNames[i].replaceFirst("\\s", "").equalsIgnoreCase("EII")) {
								//TODO pone el nombre completo asociado a EII (otras patologias vistas: CIRUGIA, VIH)
								pathologyNames[i] = "EII";
							}
						}
						Set<Pathology> pathologies = pathologyRepository.findByNameInIgnoreCase(pathologyNames);
						medicine.setPathologies(pathologies);
					} else if (cell.getColumnIndex() == MedicineExcelColumns.FAMILY.getNumber()) {
						medicine.setFamily(cell.getStringCellValue().toUpperCase());
						medicine.setBiologic(medicine.getFamily().contains("BIOLÓGICO") || medicine.getFamily().contains("BIOLÓGICOS"));
					} else if (cell.getColumnIndex() == MedicineExcelColumns.SUBFAMILY.getNumber()) {
						medicine.setSubfamily(cell.getStringCellValue());
					} else if (cell.getColumnIndex() == MedicineExcelColumns.TREATMENT_TYPE.getNumber()) {
						medicine.setTreatmentType(cell.getStringCellValue().toUpperCase());
					}
				}
			}
			if (row.getRowNum() != 0) {
				if (medicine.getPvl() != null && BigDecimal.ZERO.compareTo(medicine.getUnits()) != 0) {
					medicine.setPvlUnitary(medicine.getPvl().divide(medicine.getUnits(), 2, RoundingMode.UP));
				}

				medicineRepository.findByNationalCode(medicine.getNationalCode()).ifPresent(medicineToUpdate -> medicine.setId(medicineToUpdate.getId()));
				if ( medicine.getAcronym() != null && medicine.getNationalCode() != null && medicine.getDescription() != null)
					medicineRepository.save(medicine);
			}
		} catch (IllegalStateException | DateTimeParseException e) {
			bufferedWriter.write(LocalDateTime.now() + " ERROR " + getClass().getName() + " - En la fila del excel de Medicamentos: " + row.getRowNum() + " " + Throwables.getStackTraceAsString(e));
		}
	}

	/**
	 * Método para devolver un libro de trabajo válido
	 *
	 * @param multipartFile file
	 * @return workbook
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
		if (Objects.nonNull(dispensationDetail)) {
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

	public Map<String,String> findAllMedicines(final Pageable pageable, String groupBy) {
		log.debug(CALLING_DB);
		List<Medicine> medicines = medicineRepository.findAll();
		Map<String, String> map = new HashMap<>();
		medicines.stream().forEach(medicine -> {
			if ( !map.containsKey(medicine.getActIngredients()) ){
				map.put(medicine.getActIngredients(), medicine.getCodeAct());
			}
		});
		return map;

	}

	public Page<MedicineDTO> findMedicinesBySearchOrSearchAndTreatmentType(String search, String treatmentType, Pageable pageable) {
		log.debug(CALLING_DB);
		Page<Medicine> page;

		if (treatmentType != null) {
			page = medicineRepository.findMedicinesBySearchAndTreatmentType(search, treatmentType, pageable);
		} else {
			page = medicineRepository.findMedicinesBySearch(search, pageable);
		}

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

	private ExampleMatcher exampleByFilter() {
		final String[] IGNORE_PATHS = {"commercialization", "recommended"};
		return ExampleMatcher.matchingAll()
				.withIgnorePaths(IGNORE_PATHS)
				.withIgnoreCase(true)
				.withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
	}

	public List<DoseDTO> findDosesByMedicineId(Long medicineId) {
		List<DoseDTO> doseDTOPage = null;
		Medicine medicine = medicineRepository.findById(medicineId).orElse(null);

		if (Objects.nonNull(medicine)) {
			List<Dose> doses = doseRepository.findByCodeAtc(medicine.getCodeAtc());
			doseDTOPage = doses.stream().map(DoseMapper.INSTANCE::entityToDto).collect(Collectors.toList());
		}

		return doseDTOPage;
	}

	public MedicineDTO findByNationalCode(String nationalCode) {
		Optional<Medicine> medicine = medicineRepository.findByNationalCode(nationalCode);
		MedicineDTO medicineDTO = null;

		if (medicine.isPresent()) {
			medicineDTO = MedicineMapper.INSTANCE.entityToDto(medicine.get());
		}

		return medicineDTO;
	}
}
