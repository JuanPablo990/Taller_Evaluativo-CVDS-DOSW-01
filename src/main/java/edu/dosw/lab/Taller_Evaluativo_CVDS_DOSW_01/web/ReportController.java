package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.web;

import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Report;
import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.service.ReportService;
import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.web.dto.CreateReportRequest;
import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.web.dto.ReportResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService service;
    public ReportController(ReportService service) { this.service = service; }

    @PostMapping
    public ReportResponse create(@RequestBody CreateReportRequest req){
        Report base = new Report(req.title(), req.generatedDate(), req.author(),
                req.transactions(), req.content(), req.features());
        return ReportResponse.of(service.create(base, req.features(), req.exportFormat()));
    }

    @GetMapping
    public List<ReportResponse> listAll(){
        return service.listAll().stream().map(ReportResponse::of).toList();
    }

    @GetMapping("/search")
    public List<ReportResponse> search(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam(required = false) String author,
            @RequestParam(required = false, name = "feature") String feature){
        return service.findBy(date, author, feature).stream().map(ReportResponse::of).toList();
    }
}
