package ru.nsu.agudkov.statistics;

public class StringStatistics extends BaseStatistics<String> {
    private Integer maxLength = null;
    private Integer minLength = null;

    @Override
    public void processValue(String value) {
        if (maxLength == null || value.length() > maxLength) maxLength = value.length();
        if (minLength == null || value.length() < minLength) minLength = value.length();
    }

    @Override
    public String getFullStatistics() {
        if (getCount() == 0) {
            return "String full statistics: no data";
        }
        return "String full statistics:\n" + "\tcount: " + getCount() +
                "\n\tmin length: " + minLength + "\n\tmax length: " + maxLength;
    }

    @Override
    public String getShortStatistics() {
        if (getCount() == 0) {
            return "String short statistics: no data";
        }
        return "String short statistics:\n" + "\tcount: " + getCount();
    }

}
