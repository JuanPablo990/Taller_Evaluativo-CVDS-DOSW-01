package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.decorate;

import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Report;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChartsDecoratorTest {

    @Test
    void testChartsDecoratorAddsText() {
        Report report = new Report("t", LocalDate.now(), "a", List.of(), "c", List.of());
        ReportComponent component = new ChartsDecorator(new BaseReportComponent(report));

        String output = component.render();

        assertTrue(output.contains("[Charts] Gráficas de transacciones agregadas."));
    }
}
