package com.pavser.image.processor.helper;

import com.pavser.image.processor.domain.exceptions.CLIException;
import com.pavser.image.processor.domain.structures.ParsedOptions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class CLITest {

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                        new String[]{"-w", "100", "-h", "100", "-i", "bla"},
                        new ParsedOptions().setWidth(100).setHeight(100).setInputFilePath("bla"),
                        false
                },
                {
                        new String[]{"-w", "99.54", "-h", "100", "-i", "bla.bla"},
                        null,
                        true
                },
                {
                        new String[]{"-w", "100", "-h", "99.54", "-i", "bla.bla"},
                        null,
                        true
                },
                {
                        new String[]{"-w", "-h", "100", "-i", "bla.bla"},
                        null,
                        true
                },
                {
                        new String[]{"-i", "bla.bla"},
                        null,
                        true
                },
                {
                        new String[]{"dasdassd"},
                        null,
                        true
                },
                {
                        new String[]{"-w", "100", "-h", "100"},
                        null,
                        true
                },
        });
    }

    private String[] args;
    private ParsedOptions expectedResult;
    private boolean expectException;

    public CLITest(String[] args, ParsedOptions expectedResult, boolean expectException) {
        this.args = args;
        this.expectedResult = expectedResult;
        this.expectException = expectException;
    }

    @Test
    public void test() throws CLIException {
        CLI sut = new CLI();
        if (!expectException) {
            assertEquals(expectedResult, sut.parse(args));
        } else {
            try {
                sut.parse(args);
                fail();
            } catch (Exception e) {
            }
        }
    }

}