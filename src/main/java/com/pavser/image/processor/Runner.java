package com.pavser.image.processor;

import com.pavser.image.processor.domain.ImageProcessor;
import com.pavser.image.processor.domain.ParsedOptions;
import com.pavser.image.processor.helper.CLI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.io.File;

@ComponentScan(basePackages = "com.pavser.image.processor")
public class Runner {

    public static void main(String[] args) {
        try {
            // parse arguments
            CLI cli = new CLI();
            ParsedOptions parsedOptions = cli.parse(args);

            // get start bean
            ApplicationContext context = new AnnotationConfigApplicationContext(Runner.class);
            ImageProcessor bean = context.getBean(ImageProcessor.class);

            // do work
            String resultFileName = bean.processImage(
                    parsedOptions.getWidth(), parsedOptions.getHeight(), parsedOptions.getInputFilePath());

            //print result
            System.out.println("Your file is: " + System.getProperty("user.dir") + System.getProperty("file.separator") + resultFileName);
        } catch (Exception e) {
            System.out.println("Couldn't process image. Reason is " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

}