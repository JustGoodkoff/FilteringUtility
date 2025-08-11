package ru.nsu.agudkov.statistics;

import java.math.BigInteger;

public class IntegerStatistics extends BaseStatistics<BigInteger> {
    private BigInteger max = null;
    private BigInteger min = null;
    private BigInteger sum = BigInteger.ZERO;
    private BigInteger avg = null;

    @Override
    public void processValue(BigInteger value) {
        if (min == null || value.compareTo(min) < 0) min = value;
        if (max == null || value.compareTo(max) > 0) max = value;
        sum = sum.add(value);
        avg = sum.divide(BigInteger.valueOf(getCount()));
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

