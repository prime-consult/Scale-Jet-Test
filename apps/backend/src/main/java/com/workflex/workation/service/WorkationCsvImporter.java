package com.workflex.workation.service;

import com.workflex.workation.domain.Risk;
import com.workflex.workation.domain.Workation;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class WorkationCsvImporter {

    private static final CSVFormat CSV_FORMAT = CSVFormat.DEFAULT.builder()
            .setHeader()
            .setSkipHeaderRecord(true)
            .setIgnoreHeaderCase(true)
            .setTrim(true)
            .setIgnoreEmptyLines(true)
            .build();

    public List<Workation> parse(Reader reader) throws IOException {
        try (CSVParser parser = CSV_FORMAT.parse(reader)) {
            List<Workation> workations = new ArrayList<>();
            for (CSVRecord record : parser) {
                workations.add(toWorkation(record));
            }
            return List.copyOf(workations);
        }
    }

    private Workation toWorkation(CSVRecord record) {
        return new Workation(
                required(record, "workationId"),
                required(record, "employee"),
                required(record, "origin"),
                required(record, "destination"),
                LocalDate.parse(required(record, "start")),
                LocalDate.parse(required(record, "end")),
                Integer.parseInt(required(record, "workingDays")),
                Risk.fromCsv(required(record, "risk"))
        );
    }

    private String required(CSVRecord record, String column) {
        String value = record.get(column);
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(
                    "Missing value for column '%s' on CSV record %d".formatted(column, record.getRecordNumber())
            );
        }
        return value;
    }
}
