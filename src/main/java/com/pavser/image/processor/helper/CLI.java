package com.pavser.image.processor.helper;

import com.pavser.image.processor.domain.exceptions.CLIException;
import com.pavser.image.processor.domain.structures.ParsedOptions;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.net.MalformedURLException;
import java.net.URL;

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
    public ParsedOptions parse(String[] args) throws CLIException {
        Options options = getOptions();
        return parse(options, args);
    }

    private ParsedOptions parse(Options options, String[] args) throws CLIException {
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            formatter.printHelp("Use help below", options);
            throw new CLIException(e);
        }

        String inputFilePath = cmd.getOptionValue(PARAM_INPUT_FILE);
        String widthStr = cmd.getOptionValue(PARAM_WIDTH);
        String heightStr = cmd.getOptionValue(PARAM_HEIGHT);

        try {
            new URL(inputFilePath);
        } catch (MalformedURLException e) {
            throw new CLIException("Malformed url in input.");
        }

        Integer width = -1;
        Integer height = -1;

        try {
            width = Integer.parseInt(widthStr);
            height = Integer.parseInt(heightStr);
        } catch (Exception e) {
            throw new CLIException("Incorrect format of width or height. Only integers is allowed");
        }

        return new ParsedOptions(width, height, inputFilePath);
    }

    private Options getOptions() {
        Options options = new Options();

        Option input = new Option("i", PARAM_INPUT_FILE, true,
                "Input image to process. URL. Might work with http(s) and file schema. Supports JPG and PNG formats.");
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
