package com.pavser.image.processor.helper;

import com.pavser.image.processor.domain.ParsedOptions;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Command line interface.
 * Do logic with CL commands
 */
public class CLI {

    public static final String PARAM_INPUT_FILE = "input";
    public static final String PARAM_WIDTH = "width";
    public static final String PARAM_HEIGHT = "height";

    /**
     * Parse and validate input array of CL arguments
     * @param args CL arguments
     * @return POJO with argument values
     */
    public ParsedOptions parse(String[] args) {
        Options options = getOptions();
        return parse(options, args);
    }

    private ParsedOptions parse(Options options, String[] args) {
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("Use help below", options);
            System.exit(1);
        }

        String inputFilePath = cmd.getOptionValue(PARAM_INPUT_FILE);
        String widthStr = cmd.getOptionValue(PARAM_WIDTH);
        String heightStr = cmd.getOptionValue(PARAM_HEIGHT);

        Integer width = -1;
        Integer height = -1;

        try {
            width = Integer.parseInt(widthStr);
            height = Integer.parseInt(heightStr);
        } catch (Exception e) {
            System.out.println("Incorrect format of width or height. Only integers is allowed");
            System.exit(1);
        }

        return new ParsedOptions(width, height, inputFilePath);
    }

    private Options getOptions() {
        Options options = new Options();

        Option input = new Option("i", PARAM_INPUT_FILE, true, "Input image to process. URI");
        input.setRequired(true);
        options.addOption(input);

        Option widthOpt = new Option("w", PARAM_WIDTH, true, "Desired width of result. Integer");
        widthOpt.setRequired(true);
        options.addOption(widthOpt);

        Option heightOpt = new Option("h", PARAM_HEIGHT, true, "Desired height of result. Integer");
        heightOpt.setRequired(true);
        options.addOption(heightOpt);
        return options;
    }


}
