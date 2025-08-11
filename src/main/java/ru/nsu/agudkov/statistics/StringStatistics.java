package ru.nsu.agudkov.statistics;

public class StringStatistics extends BaseStatistics<String> {
    private long maxLength = 0;
    private long minLength = 0;


    @Override
    public void processValue(String value) {
        if (value.length() > maxLength) maxLength = value.length();
        if (value.length() < minLength) minLength = value.length();
    }

    @Override
    public String getFullStatistics() {
        return minLength + " " + maxLength;
    }

    @Override
    public String getShortStatistics() {
        return String.valueOf(getCount());
    }

}
