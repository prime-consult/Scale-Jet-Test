package com.workflex.workation.service;

import com.workflex.workation.repository.WorkationRepository;
import com.workflex.workation.web.WorkationResponse;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WorkationService {

    private final WorkationRepository workationRepository;

    public WorkationService(WorkationRepository workationRepository) {
        this.workationRepository = workationRepository;
    }

    @Transactional(readOnly = true)
    public List<WorkationResponse> findAll() {
        return workationRepository.findAll(Sort.by("id")).stream()
                .map(WorkationResponse::from)
                .toList();
    }
}
