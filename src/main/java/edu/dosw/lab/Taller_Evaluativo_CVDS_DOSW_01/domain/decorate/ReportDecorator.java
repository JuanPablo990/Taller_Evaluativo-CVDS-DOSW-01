package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.decorate;

public abstract class ReportDecorator implements ReportComponent {
    protected final ReportComponent inner;
    protected ReportDecorator(ReportComponent inner) { this.inner = inner; }
    @Override public String render() { return inner.render(); }
    @Override public edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.Report model() { return inner.model(); }
}
