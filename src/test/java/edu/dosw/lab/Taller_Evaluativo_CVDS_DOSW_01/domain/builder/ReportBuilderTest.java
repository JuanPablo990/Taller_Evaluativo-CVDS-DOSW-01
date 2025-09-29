package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.builder;

import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Report;
import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportBuilderTest {

    @Test
    void testBuilderMethodsReturnThisForChaining() {
        SimpleReportBuilder builder = new SimpleReportBuilder();
        LocalDate date = LocalDate.of(2025, 1, 1);
        List<Transaction> txs = List.of(new Transaction("t1", date, "desc", BigDecimal.ONE));

        ReportBuilder r1 = builder.title("Titulo");
        ReportBuilder r2 = builder.generatedDate(date);
        ReportBuilder r3 = builder.author("Autor");
        ReportBuilder r4 = builder.transactions(txs);
        ReportBuilder r5 = builder.content("Contenido");
        ReportBuilder r6 = builder.feature("Charts");

        assertAll("métodos encadenables del builder",
                () -> assertSame(builder, r1),
                () -> assertSame(builder, r2),
                () -> assertSame(builder, r3),
                () -> assertSame(builder, r4),
                () -> assertSame(builder, r5),
                () -> assertSame(builder, r6)
        );
    }

    @Test
    void testBuildProducesReportWithSetValues() {
        LocalDate date = LocalDate.of(2025, 4, 20);
        List<Transaction> txs = List.of(new Transaction("t1", date, "Compra", BigDecimal.valueOf(50)));

        Report report = new SimpleReportBuilder()
                .title("Reporte X")
                .generatedDate(date)
                .author("Autor X")
                .transactions(txs)
                .content("Contenido X")
                .feature("Stats")
                .build();

        assertAll("propiedades del Report generado por el builder",
                () -> assertEquals("Reporte X", report.getTitle()),
                () -> assertEquals(date, report.getGeneratedDate()),
                () -> assertEquals("Autor X", report.getAuthor()),
                () -> assertEquals(txs, report.getTransactions()),
                () -> assertEquals("Contenido X", report.getContent()),
                () -> assertTrue(report.getFeatures().contains("Stats"))
        );
    }

    @Test
    void testBuildWithDefaultsProducesEmptyLists() {
        ReportBuilder builder = new SimpleReportBuilder();

        Report report = builder.build();

        assertAll("build por defecto",
                () -> assertNotNull(report, "El report no debe ser null"),
                () -> assertNotNull(report.getTransactions(), "La lista de transacciones no debe ser null"),
                () -> assertTrue(report.getTransactions().isEmpty(), "La lista de transacciones debe estar vacía por defecto"),
                () -> assertNotNull(report.getFeatures(), "La lista de features no debe ser null"),
                () -> assertTrue(report.getFeatures().isEmpty(), "La lista de features debe estar vacía por defecto")
        );
    }
}
