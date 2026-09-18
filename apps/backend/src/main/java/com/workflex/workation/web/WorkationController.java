package com.workflex.workation.web;

import com.workflex.workation.service.WorkationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/workflex")
public class WorkationController {

    private final WorkationService workationService;

    public WorkationController(WorkationService workationService) {
        this.workationService = workationService;
    }

    @GetMapping("/workation")
    public List<WorkationResponse> listWorkations() {
        return workationService.findAll();
    }
}
