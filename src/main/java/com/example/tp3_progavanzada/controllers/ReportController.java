package com.example.tp3_progavanzada.controllers;

import com.example.tp3_progavanzada.interfaces.StatisticalReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private StatisticalReport statisticalReport;


    @GetMapping("/downloadpdf/{id}")
    public ResponseEntity<byte[]> descargarReportePDF(@PathVariable Long id) {
        try {
            byte[] pdfBytes = statisticalReport.generateReportWithStatistics(id);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte_bancario.pdf")
                    .body(pdfBytes);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
