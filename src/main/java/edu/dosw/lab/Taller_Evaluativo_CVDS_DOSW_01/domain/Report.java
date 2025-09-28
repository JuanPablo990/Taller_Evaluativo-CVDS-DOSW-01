package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document("reports")
public class Report {
    @Id private String id;
    private String title;
    private LocalDate generatedDate;
    private String author;
    private List<Transaction> transactions;
    private String content;
    private List<String> features;

    public Report() {}

    public Report(String title, LocalDate generatedDate, String author,
                  List<Transaction> transactions, String content, List<String> features) {
        this.title = title;
        this.generatedDate = generatedDate;
        this.author = author;
        this.transactions = transactions;
        this.content = content;
        this.features = features;
    }


    public String getId() { return id; }
    public String getTitle() { return title; }
    public LocalDate getGeneratedDate() { return generatedDate; }
    public String getAuthor() { return author; }
    public List<Transaction> getTransactions() { return transactions; }
    public String getContent() { return content; }
    public List<String> getFeatures() { return features; }
    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setGeneratedDate(LocalDate generatedDate) { this.generatedDate = generatedDate; }
    public void setAuthor(String author) { this.author = author; }
    public void setTransactions(List<Transaction> transactions) { this.transactions = transactions; }
    public void setContent(String content) { this.content = content; }
    public void setFeatures(List<String> features) { this.features = features; }
}
