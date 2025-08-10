package ru.nsu.agudkov;

import org.apache.commons.cli.*;


public class ParamsParser {
    public Params parse(String[] args) throws ParseException {

        Options options = new Options();
        options.addOption(Option.builder("o").hasArg().argName("path").build());
        options.addOption(Option.builder("p").hasArg().argName("prefix").build());
        options.addOption(Option.builder("a").build());
        options.addOption(Option.builder("s").build());
        options.addOption(Option.builder("f").build());

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        Params.Builder builder = new Params.Builder();

        if (cmd.hasOption("a")) {
            builder.setAppendFiles();
        }

        if (cmd.hasOption("s")) {
            builder.setStatisticType(StatisticType.SHORT);
        }

        if (cmd.hasOption("f")) {
            builder.setStatisticType(StatisticType.FULL);
        }

        builder.setOutputPath(cmd.getOptionValue("o", ""));

        builder.setFilePrefix(cmd.getOptionValue("p", ""));

        builder.setFiles(cmd.getArgList());

        return builder.build();
    }
}
