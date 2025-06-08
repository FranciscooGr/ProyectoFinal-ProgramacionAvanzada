package com.example.tp3_progavanzada.services;

import com.example.tp3_progavanzada.dto.TransactionResponseDTO;
import com.example.tp3_progavanzada.interfaces.StatisticalReport;
import com.example.tp3_progavanzada.interfaces.TransactionAnalyticService;
import com.example.tp3_progavanzada.models.BankAccountModel;
import com.example.tp3_progavanzada.models.UserModel;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class StatiscalReportPDF implements StatisticalReport {

    @Autowired
    private TotalAmountServiceReport totalAmountServiceReport;

    @Autowired
    private BankAccountService bankAccountService;

    @Autowired
    private TransactionAnalyticService transactionAnalyticsService;

    @Override
    public byte[] generateReportWithStatistics(Long userId) throws IOException {
        BankAccountModel cuenta = bankAccountService.getAccountByUser(userId);
        System.out.println("Nro de cuenta recuperado: " + cuenta.getAccountNumber());

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                contentStream.beginText();
                contentStream.newLineAtOffset(50, 700);

                // Titular
                String nombreTitular = cuenta.getUser().getUsername();
                contentStream.showText("Titular: " + nombreTitular);
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Número de cuenta: " + cuenta.getAccountNumber());
                contentStream.newLineAtOffset(0, -30);

                // Título
                contentStream.showText("REPORTE BANCARIO COMPLETO");
                contentStream.newLineAtOffset(0, -30);

                // Totales
                contentStream.showText("Total depositado: $" + totalAmountServiceReport.calculateTotalDepositedAmount(userId));
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Total retirado: $" + totalAmountServiceReport.calculateTotalWithdrawnAmount(userId));
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Dinero disponible: $" + bankAccountService.getBalance(userId));
                contentStream.newLineAtOffset(0, -30);

                // Top transacciones
                List<TransactionResponseDTO> topTransactions = transactionAnalyticsService.getHighestTransactionsByUser(userId, 5);
                contentStream.showText("Top 5 transacciones más altas:");

                for (TransactionResponseDTO tx : topTransactions) {
                    contentStream.newLineAtOffset(0, -20);
                    contentStream.showText("- $" + tx.getAmount() + " | " + tx.getType() + " | " );
                }

                contentStream.endText();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            return baos.toByteArray();
        }
    }
}
