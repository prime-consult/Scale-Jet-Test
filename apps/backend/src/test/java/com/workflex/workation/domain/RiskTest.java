package com.workflex.workation.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RiskTest {

    @ParameterizedTest
    @CsvSource({
            "HIGH,HIGH",
            "high,HIGH",
            "HIGH_RISK,HIGH",
            "LOW,LOW",
            "LOW_RISK,LOW",
            "NO,NO",
            "NO_RISK,NO"
    })
    void mapsCsvAliases(String csvValue, Risk expected) {
        assertThat(Risk.fromCsv(csvValue)).isEqualTo(expected);
    }

    @Test
    void rejectsUnknownValues() {
        assertThatThrownBy(() -> Risk.fromCsv("MEDIUM"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("MEDIUM");
    }

    @Test
    void rejectsBlankValues() {
        assertThatThrownBy(() -> Risk.fromCsv("  "))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
