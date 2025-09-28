package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.web.dto;

import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Report;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportResponseTest {

    @Test
    void testOfCreatesResponseCorrectly() {
        LocalDate date = LocalDate.of(2025, 1, 1);
        Report report = new Report(
                "Titulo", date, "Autor",
                List.of(), "Contenido", List.of("charts")
        );
        report.setId("1");

        ReportResponse resp = ReportResponse.of(report);

        assertEquals("1", resp.id());
        assertEquals("Titulo", resp.title());
        assertEquals(date, resp.generatedDate());
        assertEquals("Autor", resp.author());
        assertEquals(List.of("charts"), resp.features());
        assertEquals("Contenido", resp.content());
    }
}
