package es.plexus.hopes.hopesback.service.utils;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.service.spi.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;

public final class CsvUtils {
    /**
     * The constant LOGGER.
     */
    private static final Logger LOGGER = LogManager.getLogger(CsvUtils.class);

    public static List<CSVRecord> obtainCsvRecords(MultipartFile file){
        try(Reader in = new InputStreamReader(Objects.requireNonNull(file.getInputStream()))){
            return IteratorUtils.toList( CSVFormat.DEFAULT.parse(in).iterator());
        } catch (FileNotFoundException e) {
            LOGGER.error("Fichero no encontrado", e.getCause());
            throw new ServiceException("Error: File not found");
        } catch (IOException e) {
            LOGGER.error("Problema al leer el Fichero", e.getCause());
            throw new ServiceException("Error: Unreadable file");
        }
    }

}
