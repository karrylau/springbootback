package com.karry.springbootmybatis.service.impl;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class ReportService {

    public void generateWordReport(List<String> dataLines) throws IOException {
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        run.setText("网站数据分析报告");
        run.setFontSize(18);
        run.setBold(true);

        for (String data : dataLines) {
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.setText(data);
        }

        try (FileOutputStream out = new FileOutputStream("report.docx")) {
            document.write(out);
        }
    }
}