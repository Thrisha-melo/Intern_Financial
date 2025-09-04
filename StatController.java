package com.sampleProject.FinancialTracker.Controller;

import com.sampleProject.FinancialTracker.Model.GraphModel;
import com.sampleProject.FinancialTracker.Service.Statistics.StatService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/stat")
@CrossOrigin("*")
public class StatController {

    @Autowired
    private final StatService statService;

    public StatController(StatService statService) {
        this.statService = statService;
    }


    @GetMapping("/chart")
    ResponseEntity<GraphModel> getChartDetails()
    {
        return ResponseEntity.ok(statService.getChartData());
    }

    @GetMapping
    public ResponseEntity<?>getStat()
    {
        return ResponseEntity.ok(statService.getStat());
    }

}
