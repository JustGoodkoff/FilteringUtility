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
        return "min " + min + " max " + max + " sum " + sum + " avg " + avg;
    }

    @Override
    public String getShortStatistics() {
        return String.valueOf(getCount());
    }

}

