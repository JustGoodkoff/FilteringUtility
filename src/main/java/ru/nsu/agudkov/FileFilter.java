package ru.nsu.agudkov;


import ru.nsu.agudkov.statistics.FloatStatistics;
import ru.nsu.agudkov.statistics.IntegerStatistics;
import ru.nsu.agudkov.statistics.StringStatistics;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileFilter {
    private final Params params;
    private final Path outputPath;
    private final String integerFileName;
    private final String floatFileName;
    private final String stringFileName;
    private final String STRING_BASE_FILENAME = "strings.txt";
    private final String INTEGER_BASE_FILENAME = "integers.txt";
    private final String FLOAT_BASE_FILENAME = "floats.txt";
    private BufferedWriter integerWriter;
    private BufferedWriter floatWriter;
    private BufferedWriter stringWriter;
    private final IntegerStatistics integerStatistics = new IntegerStatistics();
    private final FloatStatistics floatStatistics = new FloatStatistics();
    private final StringStatistics stringStatistics = new StringStatistics();

    public FileFilter(Params params) {
        this.params = params;
        outputPath = Paths.get(params.getOutputPath()).toAbsolutePath();
        System.out.println("Output path: " + outputPath);
        integerFileName = params.getFilePrefix() + INTEGER_BASE_FILENAME;
        floatFileName = params.getFilePrefix() + FLOAT_BASE_FILENAME;
        stringFileName = params.getFilePrefix() + STRING_BASE_FILENAME;
    }

    private void deleteExistingFiles() throws IOException {
        for (String fileName : new String[]{integerFileName, floatFileName, stringFileName}) {
            Path filePath = outputPath.resolve(fileName);
            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                throw new IOException("Передан флаг перезаписи, но удалить файл не получилось");
            }
        }
    }

    public void filter() throws IOException {
        if (!params.getAppendFiles()) {
            deleteExistingFiles();
        }
        for (String fileName : params.getFiles()) {
            processFile(fileName);
        }
        closeWriters();
        if (params.getType() == StatisticType.SHORT) {
            System.out.println(integerStatistics.getShortStatistics());
            System.out.println(floatStatistics.getShortStatistics());
            System.out.println(stringStatistics.getShortStatistics());
        } else {
            System.out.println(integerStatistics.getFullStatistics());
            System.out.println(floatStatistics.getFullStatistics());
            System.out.println(stringStatistics.getFullStatistics());
        }
    }

    private void processFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private void processLine(String line) throws IOException {
        if (isInteger(line)) {
            writeLine(line, DataType.INTEGER);
            integerStatistics.add(new BigInteger(line));
        } else if (isDouble(line)) {
            writeLine(line, DataType.FLOAT);
            floatStatistics.add(new BigDecimal(line));
        } else {
            writeLine(line, DataType.STRING);
            stringStatistics.add(line);
        }
    }

    private boolean isInteger(String s) {
        try {
            new BigInteger(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDouble(String s) {
        try {
            new BigDecimal(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void writeLine(String line, DataType type) throws IOException {
        BufferedWriter writer = switch (type) {
            case INTEGER -> getIntegerWriter();
            case FLOAT -> getFloatWriter();
            case STRING -> getStringWriter();
        };
        if (writer != null) {
            writer.write(line);
            writer.newLine();
        }
    }

    private BufferedWriter getIntegerWriter() throws IOException {
        if (integerWriter == null) {
            integerWriter = createWriter(INTEGER_BASE_FILENAME);
        }
        return integerWriter;
    }

    private BufferedWriter getFloatWriter() throws IOException {
        if (floatWriter == null) {
            floatWriter = createWriter(FLOAT_BASE_FILENAME);
        }
        return floatWriter;
    }

    private BufferedWriter getStringWriter() throws IOException {
        if (stringWriter == null) {
            stringWriter = createWriter(STRING_BASE_FILENAME);
        }
        return stringWriter;
    }

    private BufferedWriter createWriter(String type) throws IOException {
        String fileName = params.getFilePrefix() + type;
        Path filePath = outputPath.resolve(fileName);
        System.out.println(fileName + " created");
        System.out.println(filePath + " resolved");
        boolean append = params.getAppendFiles();
        try {
            return new BufferedWriter(new FileWriter(filePath.toFile(), append));
        } catch (IOException e) {
            throw new IOException(
                    "Ошибка, невозможно создать файл для записи");
        }
    }

    private void closeWriters() throws IOException {
        closeWriter(integerWriter, "integer");
        closeWriter(floatWriter, "float");
        closeWriter(stringWriter, "string");
    }

    private void closeWriter(BufferedWriter writer, String type) throws IOException {
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                throw new IOException("Ошибка при закрытии файла");
            }
        }
    }
}