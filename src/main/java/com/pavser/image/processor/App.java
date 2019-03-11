package com.pavser.image.processor;

import com.pavser.image.processor.domain.ImageProcessor;
import com.pavser.image.processor.domain.exceptions.ImageProcessorException;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


@ComponentScan(basePackages = "com.pavser.image.processor")
public class App {

    public static final String PARAM_INPUT_FILE = "input";
    public static final String PARAM_WIDTH = "width";
    public static final String PARAM_HEIGHT = "height";

    public static void main(String[] args) {

        Options options = getOptions();

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

        try {
            ApplicationContext context = new AnnotationConfigApplicationContext(App.class);
            ImageProcessor bean = context.getBean(ImageProcessor.class);
            String resultFileName = bean.processImage(width, height, inputFilePath);
            System.out.println("Your file is: " + System.getProperty("user.dir") + File.pathSeparator + resultFileName);
        } catch (Exception e) {
            System.out.println("Couldn't process image. Reason is " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    private static Options getOptions() {
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
