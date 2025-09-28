package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.builder;

import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Report;
import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimpleReportBuilderTest {

    @Test
    void testBuildReportWithAllFields() {
        LocalDate date = LocalDate.of(2025, 9, 28);
        List<Transaction> transactions = List.of(
                new Transaction("t1", date, "Compra", BigDecimal.valueOf(100))
        );


        Report report = new SimpleReportBuilder()
                .title("Reporte Prueba")
                .generatedDate(date)
                .author("Sebastian")
                .transactions(transactions)
                .content("Contenido del reporte")
                .feature("Charts")
                .feature("Stats")
                .build();


        assertAll("Report construido con builder",
                () -> assertEquals("Reporte Prueba", report.getTitle()),
                () -> assertEquals(date, report.getGeneratedDate()),
                () -> assertEquals("Sebastian", report.getAuthor()),
                () -> assertEquals(transactions, report.getTransactions()),
                () -> assertEquals("Contenido del reporte", report.getContent()),
                () -> assertTrue(report.getFeatures().contains("Charts")),
                () -> assertTrue(report.getFeatures().contains("Stats"))
        );
    }

    @Test
    void testBuildReportWithNullTransactionsAndNullFeature() {

        Report report = new SimpleReportBuilder()
                .title("Sin transacciones")
                .transactions(null)
                .feature(null)
                .build();


        assertAll("Builder con nulls",
                () -> assertEquals("Sin transacciones", report.getTitle()),
                () -> assertNotNull(report.getTransactions(), "La lista de transacciones no debe ser null"),
                () -> assertTrue(report.getTransactions().isEmpty(), "La lista de transacciones debe ser vacía"),
                () -> assertNotNull(report.getFeatures(), "La lista de features no debe ser null"),
                () -> assertTrue(report.getFeatures().isEmpty(), "La lista de features debe estar vacía")
        );
    }

    @Test
    void testChainedBuilderMethods() {
        SimpleReportBuilder builder = new SimpleReportBuilder();


        builder.title("Encadenado")
                .author("Autor X")
                .content("Contenido X")
                .feature("Watermark");

        Report report = builder.build();


        assertAll("Encadenamiento de métodos",
                () -> assertEquals("Encadenado", report.getTitle()),
                () -> assertEquals("Autor X", report.getAuthor()),
                () -> assertEquals("Contenido X", report.getContent()),
                () -> assertTrue(report.getFeatures().contains("Watermark"))
        );
    }
}
