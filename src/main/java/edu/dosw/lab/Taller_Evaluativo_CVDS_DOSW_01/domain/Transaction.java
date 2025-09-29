package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Transaction(String id, LocalDate date, String description, BigDecimal amount) {}
