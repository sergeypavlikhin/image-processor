package com.pavser.image.processor;

import com.pavser.image.processor.domain.ImageProcessor;
import com.pavser.image.processor.domain.exceptions.CLIException;
import com.pavser.image.processor.domain.structures.ParsedOptions;
import com.pavser.image.processor.helper.CLI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

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
        } catch (CLIException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        catch (Exception e) {
            System.err.println("Couldn't process image. Reason is " + e.getMessage());
            System.exit(1);
        }
    }

}
