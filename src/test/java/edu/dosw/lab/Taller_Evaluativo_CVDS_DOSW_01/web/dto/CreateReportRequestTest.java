package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.web.dto;

import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Transaction;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateReportRequestTest {

    @Test
    void testRecordStoresValues() {
        LocalDate date = LocalDate.of(2025, 1, 1);
        Transaction tx = new Transaction("Compra", date, "debito", BigDecimal.TEN);
        List<Transaction> transactions = List.of(tx);
        List<String> features = List.of("charts", "stats");

        CreateReportRequest req = new CreateReportRequest(
                "Titulo", date, "Autor", transactions, "Contenido", features, "pdf"
        );

        assertEquals("Titulo", req.title());
        assertEquals(date, req.generatedDate());
        assertEquals("Autor", req.author());
        assertEquals(transactions, req.transactions());
        assertEquals("Contenido", req.content());
        assertEquals(features, req.features());
        assertEquals("pdf", req.exportFormat());
    }
}
