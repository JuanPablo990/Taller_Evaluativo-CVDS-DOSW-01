package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.web.dto;

import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Report;
import java.time.LocalDate;
import java.util.List;

public record ReportResponse(
        String id, String title, LocalDate generatedDate, String author,
        List<String> features, String content) {
    public static ReportResponse of(Report r){
        return new ReportResponse(r.getId(), r.getTitle(), r.getGeneratedDate(),
                r.getAuthor(), r.getFeatures(), r.getContent());
    }
}
