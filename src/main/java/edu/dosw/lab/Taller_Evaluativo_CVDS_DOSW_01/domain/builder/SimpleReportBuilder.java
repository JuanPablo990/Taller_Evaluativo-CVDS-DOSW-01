package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.builder;

import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Report;
import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SimpleReportBuilder implements ReportBuilder {
    private String title;
    private LocalDate generatedDate;
    private String author;
    private List<Transaction> transactions = new ArrayList<>();
    private String content;
    private final List<String> features = new ArrayList<>();

    @Override public ReportBuilder title(String title) { this.title = title; return this; }
    @Override public ReportBuilder generatedDate(LocalDate date) { this.generatedDate = date; return this; }
    @Override public ReportBuilder author(String author) { this.author = author; return this; }
    @Override public ReportBuilder transactions(List<Transaction> txs) { if (txs!=null) this.transactions = txs; return this; }
    @Override public ReportBuilder content(String content) { this.content = content; return this; }
    @Override public ReportBuilder feature(String feature) { if (feature!=null) this.features.add(feature); return this; }

    @Override public Report build() {
        return new Report(title, generatedDate, author, transactions, content, features);
    }
}
