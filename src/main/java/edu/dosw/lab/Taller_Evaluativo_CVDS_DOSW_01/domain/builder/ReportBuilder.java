package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.builder;

import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Report;
import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface ReportBuilder {
    ReportBuilder title(String title);
    ReportBuilder generatedDate(LocalDate date);
    ReportBuilder author(String author);
    ReportBuilder transactions(List<Transaction> txs);
    ReportBuilder content(String content);
    ReportBuilder feature(String feature);
    Report build();
}
