package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.decorate;

import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Report;
import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatsDecoratorTest {

    private Report buildReportWithTransactions() {
        return new Report(
                "Stats Report",
                LocalDate.now(),
                "Autor",
                List.of(
                        new Transaction("t1", LocalDate.now(), "Compra", BigDecimal.valueOf(100)),
                        new Transaction("t2", LocalDate.now(), "Venta", BigDecimal.valueOf(200))
                ),
                "Contenido",
                List.of()
        );
    }

    @Test
    void testStatsDecoratorWithTransactions() {
        Report report = buildReportWithTransactions();
        ReportComponent component = new StatsDecorator(new BaseReportComponent(report));

        String output = component.render();

        assertAll("Stats values",
                () -> assertTrue(output.contains("total=300")),
                () -> assertTrue(output.contains("avg=150")),
                () -> assertTrue(output.contains("count=2"))
        );
    }

    @Test
    void testStatsDecoratorWithEmptyTransactions() {
        Report emptyReport = new Report("Empty", LocalDate.now(), "Nobody", List.of(), "Nada", List.of());
        ReportComponent component = new StatsDecorator(new BaseReportComponent(emptyReport));

        String output = component.render();

        assertAll("Stats with empty list",
                () -> assertTrue(output.contains("total=0")),
                () -> assertTrue(output.contains("avg=0")),
                () -> assertTrue(output.contains("count=0"))
        );
    }
}

