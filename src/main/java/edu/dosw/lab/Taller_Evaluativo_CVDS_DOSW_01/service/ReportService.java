package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.service;

import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Report;
import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.builder.ReportBuilder;
import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.builder.SimpleReportBuilder;
import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.decorate.*;
import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

@Service
public class ReportService {
    private final ReportRepository repo;
    public ReportService(ReportRepository repo) { this.repo = repo; }

    public Report create(Report base, List<String> features, String exportFormat) {
        ReportBuilder builder = new SimpleReportBuilder()
                .title(base.getTitle())
                .generatedDate(base.getGeneratedDate())
                .author(base.getAuthor())
                .transactions(base.getTransactions())
                .content(base.getContent());
        if (features != null) features.forEach(builder::feature);
        Report built = builder.build();

        ReportComponent comp = new BaseReportComponent(built);
        if (features != null) {
            for (String f : features) {
                switch (f.toLowerCase()) {
                    case "charts" -> comp = new ChartsDecorator(comp);
                    case "watermark" -> comp = new WatermarkDecorator(comp);
                    case "stats" -> comp = new StatsDecorator(comp);
                    default -> {}
                }
            }
        }
        if (exportFormat != null) comp = new ExportDecorator(comp, exportFormat.toUpperCase());

        String rendered = comp.render();
        built.setContent(rendered);
        return repo.save(built);
    }

    public List<Report> listAll() { return repo.findAll(); }

    public List<Report> findBy(LocalDate date, String author, String containsFeature) {
        Predicate<Report> byDate = (date == null) ? r -> true : r -> date.equals(r.getGeneratedDate());
        Predicate<Report> byAuthor = (author == null || author.isBlank()) ? r -> true :
                r -> r.getAuthor() != null && r.getAuthor().equalsIgnoreCase(author);
        Predicate<Report> byFeature = (containsFeature == null || containsFeature.isBlank()) ? r -> true :
                r -> r.getFeatures() != null && r.getFeatures().stream().anyMatch(f -> f.equalsIgnoreCase(containsFeature));

        return repo.findAll().stream()
                .filter(byDate.and(byAuthor).and(byFeature))
                .sorted((a,b) -> a.getGeneratedDate().compareTo(b.getGeneratedDate()))
                .toList();
    }
}
