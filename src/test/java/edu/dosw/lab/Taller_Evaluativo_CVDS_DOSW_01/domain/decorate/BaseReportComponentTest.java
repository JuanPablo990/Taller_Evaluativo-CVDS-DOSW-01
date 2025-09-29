package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.decorate;

import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Report;
import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BaseReportComponentTest {

    private Report buildSampleReport() {
        return new Report(
                "Reporte Base",
                LocalDate.of(2025, 9, 28),
                "Sebastian",
                List.of(new Transaction("t1", LocalDate.now(), "Compra", BigDecimal.valueOf(100))),
                "Contenido base",
                List.of("Base")
        );
    }

    @Test
    void testRenderContainsReportData() {
        Report report = buildSampleReport();
        ReportComponent component = new BaseReportComponent(report);

        String output = component.render();

        assertAll("Base render",
                () -> assertTrue(output.contains("# " + report.getTitle())),
                () -> assertTrue(output.contains("Autor: " + report.getAuthor())),
                () -> assertTrue(output.contains("Fecha: " + report.getGeneratedDate())),
                () -> assertTrue(output.contains(report.getContent()))
        );
    }

    @Test
    void testModelReturnsSameReport() {
        Report report = buildSampleReport();
        ReportComponent component = new BaseReportComponent(report);

        assertSame(report, component.model());
    }
}

