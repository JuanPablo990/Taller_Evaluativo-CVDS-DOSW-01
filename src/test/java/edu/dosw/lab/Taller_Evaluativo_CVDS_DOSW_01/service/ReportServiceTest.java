package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.service;

import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Report;
import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.repository.ReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReportServiceTest {

    private ReportRepository repo;
    private ReportService service;

    @BeforeEach
    void setUp() {
        repo = mock(ReportRepository.class);
        service = new ReportService(repo);
    }

    @Test
    void testCreateWithFeaturesAndExport() {
        Report base = new Report("Reporte 1", LocalDate.now(), "Sebas",
                List.of(), "contenido base", List.of("charts", "stats"));

        when(repo.save(any(Report.class))).thenAnswer(inv -> inv.getArgument(0));

        Report result = service.create(base, List.of("charts", "stats", "watermark"), "pdf");

        assertNotNull(result);
        assertTrue(result.getContent().contains("[Charts]"));
        assertTrue(result.getContent().contains("[Stats]"));
        assertTrue(result.getContent().contains("[Security]"));
        assertTrue(result.getContent().contains("[Export]"));
        verify(repo, times(1)).save(any(Report.class));
    }

    @Test
    void testListAllDelegatesToRepo() {
        Report r1 = new Report("R1", LocalDate.now(), "Ana", List.of(), "c1", List.of());
        when(repo.findAll()).thenReturn(List.of(r1));

        List<Report> result = service.listAll();

        assertEquals(1, result.size());
        assertEquals("Ana", result.get(0).getAuthor());
        verify(repo, times(1)).findAll();
    }

    @Test
    void testFindByFiltersCorrectly() {
        LocalDate today = LocalDate.now();
        Report r1 = new Report("R1", today, "Ana", List.of(), "c1", List.of("charts"));
        Report r2 = new Report("R2", today.minusDays(1), "Sebas", List.of(), "c2", List.of("stats"));
        when(repo.findAll()).thenReturn(List.of(r1, r2));

        List<Report> result = service.findBy(today, "Ana", "charts");


        assertEquals(1, result.size());
        assertEquals("Ana", result.get(0).getAuthor());
        assertTrue(result.get(0).getFeatures().contains("charts"));
    }
}
