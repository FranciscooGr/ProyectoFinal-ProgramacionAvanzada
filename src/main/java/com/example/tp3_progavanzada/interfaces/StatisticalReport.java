package com.example.tp3_progavanzada.interfaces;

import java.io.IOException;

public interface StatisticalReport {
    byte[] generateReportWithStatistics(Long id) throws IOException;
}
