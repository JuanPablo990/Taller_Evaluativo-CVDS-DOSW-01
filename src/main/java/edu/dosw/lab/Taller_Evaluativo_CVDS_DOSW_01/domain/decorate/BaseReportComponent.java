package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.decorate;

import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Report;

public class BaseReportComponent implements ReportComponent {
    private final Report report;
    public BaseReportComponent(Report report) { this.report = report; }

    @Override public String render() {
        return """
               # %s
               Autor: %s
               Fecha: %s

               %s
               """.formatted(report.getTitle(), report.getAuthor(), report.getGeneratedDate(), report.getContent());
    }
    @Override public Report model() { return report; }
}
