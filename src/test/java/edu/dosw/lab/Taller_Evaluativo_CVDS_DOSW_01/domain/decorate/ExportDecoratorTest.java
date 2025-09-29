package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.decorate;

import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Report;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ExportDecoratorTest {

    @Test
    void testExportDecoratorAddsExportText() {
        Report report = new Report("t", LocalDate.now(), "a", List.of(), "c", List.of());
        ReportComponent component = new ExportDecorator(new BaseReportComponent(report), "PDF");

        String output = component.render();

        assertTrue(output.contains("[Export] Exportación a PDF generada."));
    }
}
