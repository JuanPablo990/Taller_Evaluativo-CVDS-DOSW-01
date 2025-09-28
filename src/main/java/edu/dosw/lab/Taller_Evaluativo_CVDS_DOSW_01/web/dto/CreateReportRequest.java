package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.web.dto;

import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Transaction;

import java.time.LocalDate;
import java.util.List;

public record CreateReportRequest(
        String title,
        LocalDate generatedDate,
        String author,
        List<Transaction> transactions,
        String content,
        List<String> features,
        String exportFormat
) {}
