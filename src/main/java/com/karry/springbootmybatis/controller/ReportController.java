package com.karry.springbootmybatis.controller;
import com.karry.springbootmybatis.service.impl.ReportService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/generate-report")
    public ResponseEntity<FileSystemResource> generateReport() throws IOException {
        List<String> dataLines = new ArrayList<>();
        dataLines.add("本月网站访问量为：1000 次。");
        dataLines.add("平均用户停留时间为：5 分钟。");
        reportService.generateWordReport(dataLines);

        File file = new File("report.docx");
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=report.docx");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new FileSystemResource(file));
    }
}