package ru.nsu.agudkov.statistics;

public class StringStatistics extends BaseStatistics<String> {
    private int maxLength = -1;
    private int minLength = Integer.MAX_VALUE;

    @Override
    public void processValue(String value) {
        if (value.length() > maxLength) maxLength = value.length();
        if (value.length() < minLength) minLength = value.length();
    }

    @Override
    public String getFullStatistics() {
        if (getCount() == 0) {
            return "String full statistics: no data";
        }
        return "String full statistics:\n" + "\tmin length: " + minLength + "\n\tmax length: " + maxLength;
    }

    @Override
    public String getShortStatistics() {
        if (getCount() == 0) {
            return "String short statistics: no data";
        }
        return "String short statistics:\n" + "\tcount: " + getCount();
    }

}
