package de.bencoepp.kommod.daemon.controller;

import de.bencoepp.entity.test.Report;
import de.bencoepp.kommod.daemon.repository.ScheduledTestRepository;
import de.bencoepp.kommod.daemon.utils.ReportHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReportController {

    @Autowired
    private ScheduledTestRepository scheduledTestRepository;


    @GetMapping("/report/all")
    public String getAll(Model model) throws IOException {
        model.addAttribute("pageTitle", "All Reports");
        List<Report> reports = ReportHelper.getAllReports();
        model.addAttribute("reports", reports);
        return "/report/all";
    }

    @GetMapping("/api/report/all")
    public ResponseEntity<List<Report>> getAllReports(Model model) throws IOException {
        return ResponseEntity.ok(ReportHelper.getAllReports());
    }
}
