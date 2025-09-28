package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.repository;

import edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDate;
import java.util.List;

public interface ReportRepository extends MongoRepository<Report, String> {
    List<Report> findByAuthorIgnoreCase(String author);
    List<Report> findByGeneratedDate(LocalDate date);
}
