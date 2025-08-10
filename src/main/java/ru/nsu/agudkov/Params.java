package ru.nsu.agudkov;

import org.apache.commons.cli.ParseException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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

    static class Builder {
        private String outputPath;
        private String filePrefix;
        private Boolean appendFiles = false;
        private StatisticType type;
        private final ArrayList<String> files = new ArrayList<>();

        void setOutputPath(String outputPath) {
            if (!Files.isDirectory(Paths.get(outputPath))) {
                System.out.println("Ошибка: указанный путь не является директорией.\n" +
                        "Вместо него будет использована текущая рабочая директория.");
            } else {
                this.outputPath = outputPath;
            }
        }

        void setFilePrefix(String filePrefix) {
            this.filePrefix = filePrefix;
        }


        void setStatisticType(StatisticType type) {
            if (this.type == null) {
                this.type = type;
            }
        }

        void setFiles(List<String> files) throws ParseException {
            for (String file : files) {
                if (Files.isRegularFile(Path.of(file))) {
                    this.files.add(file);
                } else {
                    System.out.println("Объект " + file + " не является файлом, данные из него не будут взяты");
                }
            }
            if (this.files.isEmpty()) {
                throw new ParseException("Нет валидных входных файлов");
            }
        }

        Params build() {
            return new Params(outputPath, filePrefix, appendFiles, type, files);
        }

        public void setAppendFiles() {
            this.appendFiles = true;
        }
    }
}