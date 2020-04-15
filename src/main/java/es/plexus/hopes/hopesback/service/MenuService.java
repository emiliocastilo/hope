package es.plexus.hopes.hopesback.service;

import es.plexus.hopes.hopesback.repository.SectionRepository;
import es.plexus.hopes.hopesback.repository.model.ERole;
import org.springframework.stereotype.Service;

@Service
public class MenuService {

    private final SectionRepository sectionRepository;

    public MenuService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    public void findMenuByRole(ERole role) {

    }
}
