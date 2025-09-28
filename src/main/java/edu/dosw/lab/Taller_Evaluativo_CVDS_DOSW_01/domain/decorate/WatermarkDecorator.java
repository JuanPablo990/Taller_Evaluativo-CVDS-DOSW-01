package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.decorate;

public class WatermarkDecorator extends ReportDecorator {
    public WatermarkDecorator(ReportComponent inner) { super(inner); }
    @Override public String render() {
        return inner.render() + "\n[Security] Marca de agua aplicada.\n";
    }
}

