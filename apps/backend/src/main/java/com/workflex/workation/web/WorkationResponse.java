package com.workflex.workation.web;

import com.workflex.workation.domain.Risk;
import com.workflex.workation.domain.Workation;

import java.time.LocalDate;

public record WorkationResponse(
        String workationId,
        String employee,
        String origin,
        String destination,
        LocalDate start,
        LocalDate end,
        int workingDays,
        Risk risk
) {
    public static WorkationResponse from(Workation workation) {
        return new WorkationResponse(
                workation.getId(),
                workation.getEmployee(),
                workation.getOrigin(),
                workation.getDestination(),
                workation.getStartDate(),
                workation.getEndDate(),
                workation.getWorkingDays(),
                workation.getRisk()
        );
    }
}
