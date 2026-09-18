package com.workflex.workation.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WorkationTest {

    @Test
    void rejectsEndDateBeforeStartDate() {
        assertThatThrownBy(() -> new Workation(
                "w1",
                "Steffen Jacobs",
                "Germany",
                "Spain",
                LocalDate.of(2024, 3, 2),
                LocalDate.of(2024, 3, 1),
                1,
                Risk.HIGH
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("endDate");
    }

    @Test
    void rejectsNegativeWorkingDays() {
        assertThatThrownBy(() -> new Workation(
                "w1",
                "Steffen Jacobs",
                "Germany",
                "Spain",
                LocalDate.of(2024, 3, 1),
                LocalDate.of(2024, 3, 2),
                -1,
                Risk.LOW
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("workingDays");
    }
}
