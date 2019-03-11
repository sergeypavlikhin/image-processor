package com.pavser.image.processor;

import com.pavser.image.processor.domain.ImageProcessor;
import org.apache.commons.cli.*;
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

/**
 * https://stackoverflow.com/questions/9131678/convert-a-rgb-image-to-grayscale-image-reducing-the-memory-in-java
 * https://www.baeldung.com/java-images
 * https://www.codejava.net/java-se/graphics/how-to-resize-images-in-java
 *
 */
@ComponentScan(basePackages = "com.pavser.image.processor")
public class App {

    public static void main(String[] args) {

        Options options = new Options();

        Option input = new Option("i", "input", true, "input file path");
        input.setRequired(true);
        options.addOption(input);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }

        String inputFilePath = cmd.getOptionValue("input");


        try {
            ApplicationContext context = new AnnotationConfigApplicationContext(App.class);

            ImageProcessor bean = context.getBean(ImageProcessor.class);
            bean.processImage(100, 100, inputFilePath);

//            URI uri = new URI(inputFilePath);
//            File inputFile = new File(uri);
//            BufferedImage inputImage = ImageIO.read(inputFile);
//            BufferedImage outputImage = new BufferedImage(100,100, BufferedImage.TYPE_BYTE_GRAY);
//
//            Graphics2D g2d = outputImage.createGraphics();
//            g2d.drawImage(inputImage, 0, 0, 100, 100, null);
//            g2d.dispose();
//            String formatName = inputFilePath.substring(inputFilePath
//                    .lastIndexOf(".") + 1);
//            File output1 = new File("res_1." + formatName);
//            if (!output1.exists()) {
//                output1.createNewFile();
//            }
//            ImageIO.write(outputImage, formatName, output1);
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println(inputFilePath);

    }
}
