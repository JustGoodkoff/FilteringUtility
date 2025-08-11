package ru.nsu.agudkov.statistics;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class IntegerStatistics extends BaseStatistics<BigInteger> {
    private BigInteger max = null;
    private BigInteger min = null;
    private BigInteger sum = BigInteger.ZERO;
    private BigDecimal avg = null;

    @Override
    public void processValue(BigInteger value) {
        if (min == null || value.compareTo(min) < 0) min = value;
        if (max == null || value.compareTo(max) > 0) max = value;
        sum = sum.add(value);
        avg = new BigDecimal(sum).divide(BigDecimal.valueOf(getCount()), 3, RoundingMode.CEILING);
    }

    @Override
    public String getFullStatistics() {
        if (getCount() == 0) {
            return "Integer full statistics: no data";
        }
        return "Integer full statistics:"
                + "\n\tcount: " + getCount()
                + "\n\tmin: " + min
                + "\n\tmax: " + max
                + "\n\tsum: " + sum
                + "\n\tavg: " + avg;
    }

    @Override
    public String getShortStatistics() {
        if (getCount() == 0) {
            return "Integer short statistics: no data";
        }
        return "Integer short statistics:\n" + "\tcount: " + getCount();
    }

}

