package edu.dosw.lab.Taller_Evaluativo_CVDS_DOSW_01.domain.decorate;

import java.math.BigDecimal;

public class StatsDecorator extends ReportDecorator {
    public StatsDecorator(ReportComponent inner) { super(inner); }
    @Override public String render() {
        var txs = inner.model().getTransactions();
        var sum = txs.stream().map(t -> t.amount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        var avg = txs.isEmpty() ? BigDecimal.ZERO : sum.divide(BigDecimal.valueOf(txs.size()), java.math.RoundingMode.HALF_UP);
        return inner.render() + ("\n[Stats] total=%s avg=%s count=%d\n".formatted(sum, avg, txs.size()));
    }
}
