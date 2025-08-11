package ru.nsu.agudkov.statistics;

public abstract class BaseStatistics<T> {

    private int count = 0;

    public int getCount() {
        return count;
    }

    public void add(T value) {
        count++;
        processValue(value);
    }

    public abstract void processValue(T value);

    public abstract String getFullStatistics();
    public abstract String getShortStatistics();
}
