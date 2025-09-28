package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.decorate;

import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Report;
import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportDecoratorTest {

    private Report buildSampleReport() {
        return new Report(
                "Decorated Report",
                LocalDate.now(),
                "Tester",
                List.of(new Transaction("t1", LocalDate.now(), "Compra", BigDecimal.valueOf(50))),
                "Contenido",
                List.of()
        );
    }

    @Test
    void testModelPreserved() {
        Report report = buildSampleReport();
        ReportComponent component = new ChartsDecorator(new BaseReportComponent(report));

        assertSame(report, component.model());
    }

    @Test
    void testMultipleDecoratorsCombined() {
        ReportComponent component = new WatermarkDecorator(
                new StatsDecorator(
                        new ChartsDecorator(
                                new ExportDecorator(
                                        new BaseReportComponent(buildSampleReport()), "XLSX"
                                )
                        )
                )
        );

        String output = component.render();

        assertAll("All decorators",
                () -> assertTrue(output.contains("[Charts]")),
                () -> assertTrue(output.contains("[Export] Exportación a XLSX generada.")),
                () -> assertTrue(output.contains("[Stats]")),
                () -> assertTrue(output.contains("[Security] Marca de agua aplicada."))
        );
    }
}
