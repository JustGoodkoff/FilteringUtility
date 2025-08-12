package ru.nsu.agudkov;

import org.apache.commons.cli.ParseException;

import java.io.IOException;

public class Main {

    private static final int EXIT_PARSE_ERROR = 2;

    public static void main(String[] args) {
        ParamsParser parser = new ParamsParser();
        Params params = null;
        try {
            params = parser.parse(args);
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            System.err.println(ParamsParser.HELP_TEXT);
            System.exit(EXIT_PARSE_ERROR);
        }
        try {
            FileFilter filter = new FileFilter(params);
            filter.filter();
        } catch (RuntimeException | IOException e) {
            System.out.println(e.getMessage());
        }
    }
}