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
        return "String full statistics:\n" + "\tmin length: " + minLength + "\n\tmax length: " + maxLength;
    }

    @Override
    public String getShortStatistics() {
        return "String short statistics:\n" + "\tcount: " + getCount();
    }

}
