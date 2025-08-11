package ru.nsu.agudkov.statistics;

import java.math.BigDecimal;
import java.math.MathContext;

public class FloatStatistics extends BaseStatistics<BigDecimal> {

    private BigDecimal min = null;
    private BigDecimal max = null;
    private BigDecimal sum = BigDecimal.ZERO;
    private BigDecimal avg = null;

    @Override
    public void processValue(BigDecimal value) {
        if (min == null || value.compareTo(min) < 0) min = value;
        if (max == null || value.compareTo(max) > 0) max = value;
        sum = sum.add(value);
        avg = sum.divide(BigDecimal.valueOf(getCount()), MathContext.DECIMAL64);
    }

    @Override
    public String getFullStatistics() {
        return "Float full statistics:\n" + "\tmin: " + min + "\n\tmax: " + max + "\n\tavg: " + avg;
    }

    @Override
    public String getShortStatistics() {
        return "Float short statistics:\n" + "\tcount: " + getCount();
    }

}

