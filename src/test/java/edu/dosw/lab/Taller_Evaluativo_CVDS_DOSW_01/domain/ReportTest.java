package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain;


import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {

    @Test
    void testReportConstructorAndGetters() {

        String title = "Reporte Financiero";
        LocalDate generatedDate = LocalDate.of(2025, 2, 15);
        String author = "Sebastian Silva";
        List<Transaction> transactions = List.of(
                new Transaction("t1", LocalDate.now(), "Compra", java.math.BigDecimal.ONE)
        );
        String content = "Contenido del reporte";
        List<String> features = List.of("Charts", "Summary");


        Report report = new Report(title, generatedDate, author, transactions, content, features);


        assertAll("Report properties",
                () -> assertEquals(title, report.getTitle()),
                () -> assertEquals(generatedDate, report.getGeneratedDate()),
                () -> assertEquals(author, report.getAuthor()),
                () -> assertEquals(transactions, report.getTransactions()),
                () -> assertEquals(content, report.getContent()),
                () -> assertEquals(features, report.getFeatures())
        );
    }

    @Test
    void testSettersAndId() {

        Report report = new Report();
        String id = "r1";
        String title = "Reporte de Ventas";
        LocalDate date = LocalDate.of(2025, 3, 10);
        String author = "Ana";
        String content = "Contenido ejemplo";
        List<String> features = List.of("Export", "Stats");


        report.setId(id);
        report.setTitle(title);
        report.setGeneratedDate(date);
        report.setAuthor(author);
        report.setTransactions(List.of());
        report.setContent(content);
        report.setFeatures(features);


        assertAll("Report setters",
                () -> assertEquals(id, report.getId()),
                () -> assertEquals(title, report.getTitle()),
                () -> assertEquals(date, report.getGeneratedDate()),
                () -> assertEquals(author, report.getAuthor()),
                () -> assertNotNull(report.getTransactions()),
                () -> assertEquals(content, report.getContent()),
                () -> assertEquals(features, report.getFeatures())
        );
    }
}
