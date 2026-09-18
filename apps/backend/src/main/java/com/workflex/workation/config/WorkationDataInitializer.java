package com.workflex.workation.config;

import com.workflex.workation.domain.Workation;
import com.workflex.workation.repository.WorkationRepository;
import com.workflex.workation.service.WorkationCsvImporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class WorkationDataInitializer implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(WorkationDataInitializer.class);

    private final WorkationRepository workationRepository;
    private final WorkationCsvImporter csvImporter;
    private final ResourceLoader resourceLoader;
    private final String csvLocation;

    public WorkationDataInitializer(
            WorkationRepository workationRepository,
            WorkationCsvImporter csvImporter,
            ResourceLoader resourceLoader,
            @Value("${workflex.csv.location}") String csvLocation
    ) {
        this.workationRepository = workationRepository;
        this.csvImporter = csvImporter;
        this.resourceLoader = resourceLoader;
        this.csvLocation = csvLocation;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) throws IOException {
        if (workationRepository.count() > 0) {
            log.info("Skipping CSV import; {} workations already present", workationRepository.count());
            return;
        }

        Resource resource = resourceLoader.getResource(csvLocation);
        if (!resource.exists()) {
            throw new IllegalStateException("Workation CSV not found at " + csvLocation);
        }

        try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)) {
            List<Workation> workations = csvImporter.parse(reader);
            workationRepository.saveAll(workations);
            log.info("Imported {} workations from {}", workations.size(), csvLocation);
        }
    }
}
