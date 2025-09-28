package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.decorate;

import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Report;
import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportComponentTest {

    private Report buildSampleReport() {
        return new Report(
                "Reporte Stub",
                LocalDate.of(2025, 9, 28),
                "Tester",
                List.of(new Transaction("t1", LocalDate.now(), "Compra", BigDecimal.valueOf(100))),
                "Contenido stub",
                List.of("stub-feature")
        );
    }

    @Test
    void testAnonymousReportComponentImplementation() {
        Report report = buildSampleReport();
        ReportComponent stub = new ReportComponent() {
            @Override
            public String render() {
                return "STUB_RENDER: " + report.getTitle();
            }

            @Override
            public Report model() {
                return report;
            }
        };

        String rendered = stub.render();
        Report model = stub.model();

        assertAll("Stub ReportComponent",
                () -> assertEquals("STUB_RENDER: " + report.getTitle(), rendered),
                () -> assertSame(report, model),
                () -> assertNotNull(rendered),
                () -> assertNotNull(model)
        );
    }

    @Test
    void testDecoratorUsesInnerComponentAndPreservesModel() {
        Report report = buildSampleReport();
        ReportComponent innerStub = new ReportComponent() {
            @Override
            public String render() {
                return "INNER_RENDER";
            }

            @Override
            public Report model() {
                return report;
            }
        };

        ReportComponent decorated = new ExportDecorator(innerStub, "PDF");

        String output = decorated.render();
        Report modelOut = decorated.model();

        assertAll("Decorator should delegate to inner and keep model",
                () -> assertTrue(output.contains("INNER_RENDER"), "Debe contener el render interno"),
                () -> assertTrue(output.contains("Exportación a PDF"), "Debe contener texto de exportación"),
                () -> assertSame(report, modelOut, "model() debe devolver la instancia original")
        );
    }

    @Test
    void testBaseReportComponentPolymorphismAsReportComponent() {
        Report report = buildSampleReport();
        ReportComponent component = new BaseReportComponent(report);

        String out = component.render();
        Report model = component.model();

        assertAll("BaseReportComponent via ReportComponent",
                () -> assertTrue(out.contains("# " + report.getTitle())),
                () -> assertTrue(out.contains("Autor: " + report.getAuthor())),
                () -> assertSame(report, model)
        );
    }
}

