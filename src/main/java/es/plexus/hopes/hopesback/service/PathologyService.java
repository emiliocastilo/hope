package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.controller.model.PathologyDTO;
import es.plexus.hopes.hopesback.repository.PathologyRepository;
import es.plexus.hopes.hopesback.repository.model.Pathology;
import es.plexus.hopes.hopesback.service.exception.ServiceExceptionCatalog;
import es.plexus.hopes.hopesback.service.mapper.PathologyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PathologyService {

    private final PathologyRepository pathologyRepository;

    Optional<Pathology> getOnePathologyById(final Long id) {
        return pathologyRepository.findById(id);
    }

    public PathologyDTO findByName(String name) {
        Optional<Pathology> pathology = pathologyRepository.findByName(name);

        if (pathology.isPresent()) {
            return PathologyMapper.INSTANCE.entityToDto(pathology.get());
        } else {
            throw ServiceExceptionCatalog.NOT_FOUND_ELEMENT_EXCEPTION.exception(
                    MessageFormat.format("Patología con name {0} no encontrado. La patología es requerido.", name));
        }
    }
}
