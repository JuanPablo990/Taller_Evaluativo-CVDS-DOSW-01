package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.decorate;

public class ExportDecorator extends ReportDecorator {
    private final String format; // PDF/XLSX (simulado)
    public ExportDecorator(ReportComponent inner, String format) { super(inner); this.format = format; }
    @Override public String render() {
        return inner.render() + ("\n[Export] Exportación a %s generada.\n".formatted(format));
    }
}
