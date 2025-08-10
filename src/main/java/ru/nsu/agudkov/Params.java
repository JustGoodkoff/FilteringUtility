package ru.nsu.agudkov;

import java.util.ArrayList;

public class Params {

    private final String outputPath;
    private final String filePrefix;
    private final Boolean appendFiles;
    private final StatisticType type;
    private final ArrayList<String> files;

    public ArrayList<String> getFiles() {
        return files;
    }

    public Params(String outputPath, String filePrefix, Boolean appendFiles, StatisticType type, ArrayList<String> files) {
        this.outputPath = outputPath;
        this.filePrefix = filePrefix;
        this.appendFiles = appendFiles;
        this.type = type;
        this.files = files;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public String getFilePrefix() {
        return filePrefix;
    }

    public Boolean getAppendFiles() {
        return appendFiles;
    }

    public StatisticType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "outputPath='" + outputPath + '\'' +
                ", filePrefix='" + filePrefix + '\'' +
                ", rewriteFiles=" + appendFiles +
                ", type=" + type +
                ", files=" + files +
                '}';
    }
}