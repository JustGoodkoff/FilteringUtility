package ru.nsu.agudkov;

import org.apache.commons.cli.ParseException;

public class Main {
    public static void main(String[] args) {
        ParamsParser parser = new ParamsParser();
        Params params = null;
        try {
            params = parser.parse(args);
            System.out.println(params.toString());
        } catch (ParseException e) {
            System.err.println(e.getMessage());
        }
    }
}