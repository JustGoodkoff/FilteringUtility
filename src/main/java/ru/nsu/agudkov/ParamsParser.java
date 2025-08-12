package ru.nsu.agudkov;

import org.apache.commons.cli.*;


public class ParamsParser {

    public static final String HELP_TEXT = """
Usage: java -jar FilteringUtility.jar [options] <input-files...>

Options:
    -o      Output directory for result files. If not specified, the current working directory is used.
    -p      Prefix to add to the names of output files.
    -a      Append to existing output files instead of overwriting them.
    -s      Show short statistics for processed data.
    -f      Show full statistics for processed data.

Arguments:
  <input-files...>  One or more input files to process. At least one file must be specified.
""";

    public Params parse(String[] args) throws ParseException {

        Options options = new Options();
        options.addOption(Option.builder("o").hasArg().build());
        options.addOption(Option.builder("p").hasArg().build());
        options.addOption(Option.builder("a").build());
        options.addOption(Option.builder("s").build());
        options.addOption(Option.builder("f").build());

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        Params.Builder builder = new Params.Builder();

        if (cmd.hasOption("a")) {
            builder.setAppendFiles();
        }

        if (cmd.hasOption("f")) {
            builder.setStatisticType(StatisticType.FULL);
        }

        if (cmd.hasOption("s")) {
            builder.setStatisticType(StatisticType.SHORT);
        }

        builder.setOutputPath(cmd.getOptionValue("o", ""));

        builder.setFilePrefix(cmd.getOptionValue("p", ""));

        if (cmd.getArgs().length == 0) {
            throw new ParseException("Error: No input files specified. Please provide at least one input file");
        }

        builder.setFiles(cmd.getArgList());

        return builder.build();
    }
}
