package com.workflex.workation.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "workations")
public class Workation {

    @Id
    @Column(name = "workation_id", nullable = false, updatable = false)
    private String id;

    @Column(nullable = false)
    private String employee;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String destination;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "working_days", nullable = false)
    private int workingDays;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private Risk risk;

    protected Workation() {
        // JPA
    }

    public Workation(
            String id,
            String employee,
            String origin,
            String destination,
            LocalDate startDate,
            LocalDate endDate,
            int workingDays,
            Risk risk
    ) {
        this.id = requireText(id, "id");
        this.employee = requireText(employee, "employee");
        this.origin = requireText(origin, "origin");
        this.destination = requireText(destination, "destination");
        this.startDate = Objects.requireNonNull(startDate, "startDate");
        this.endDate = Objects.requireNonNull(endDate, "endDate");
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("endDate must not be before startDate");
        }
        if (workingDays < 0) {
            throw new IllegalArgumentException("workingDays must not be negative");
        }
        this.workingDays = workingDays;
        this.risk = Objects.requireNonNull(risk, "risk");
    }

    private static String requireText(String value, String field) {
        Objects.requireNonNull(value, field);
        if (value.isBlank()) {
            throw new IllegalArgumentException(field + " must not be blank");
        }
        return value;
    }

    public String getId() {
        return id;
    }

    public String getEmployee() {
        return employee;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getWorkingDays() {
        return workingDays;
    }

    public Risk getRisk() {
        return risk;
    }
}
