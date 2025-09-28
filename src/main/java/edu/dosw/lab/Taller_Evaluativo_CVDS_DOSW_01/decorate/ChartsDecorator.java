package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.decorate;

public class ChartsDecorator extends ReportDecorator {
    public ChartsDecorator(ReportComponent inner) { super(inner); }
    @Override public String render() {
        return inner.render() + "\n[Charts] Gráficas de transacciones agregadas.\n";
    }
}
