package com.workflex.workation.service;

import com.workflex.workation.domain.Risk;
import com.workflex.workation.domain.Workation;
import com.workflex.workation.repository.WorkationRepository;
import com.workflex.workation.web.WorkationResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WorkationServiceTest {

    @Mock
    private WorkationRepository workationRepository;

    @InjectMocks
    private WorkationService workationService;

    @Test
    void mapsEntitiesToResponses() {
        Workation workation = new Workation(
                "w1",
                "Steffen Jacobs",
                "Germany",
                "United States",
                LocalDate.of(2024, 1, 2),
                LocalDate.of(2024, 12, 31),
                65,
                Risk.HIGH
        );
        when(workationRepository.findAll(Sort.by("id"))).thenReturn(List.of(workation));

        List<WorkationResponse> responses = workationService.findAll();

        assertThat(responses).containsExactly(new WorkationResponse(
                "w1",
                "Steffen Jacobs",
                "Germany",
                "United States",
                LocalDate.of(2024, 1, 2),
                LocalDate.of(2024, 12, 31),
                65,
                Risk.HIGH
        ));
    }
}
