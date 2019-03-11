package com.pavser.image.processor.domain;

import com.pavser.image.processor.domain.exceptions.ImageProcessorException;
import com.pavser.image.processor.domain.structures.TransformArg;
import com.pavser.image.processor.helper.BufferedImageHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.awt.image.BufferedImage;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class ImageProcessorTest {

    public static final String FILE_NAE = "bla.bla";
    public static final String PATH_1 = "./bla/" + FILE_NAE;
    public static final int HEIGHT_1 = 100;
    public static final int WIDTH_1 = 100;
    @InjectMocks
    private ImageProcessor sut;

    @Mock
    private BufferedImageHelper bufferedImageHelper;

    @Mock
    private DefaultImageTransformer imageTransformer;

    @Test
    public void successProcessImage() throws ImageProcessorException {
        //given
        BufferedImage in = mock(BufferedImage.class);
        BufferedImage out = mock(BufferedImage.class);
        doReturn(out).when(imageTransformer).transform(any(), any());
        doReturn(in).when(bufferedImageHelper).open(any());

        //when
        sut.processImage(WIDTH_1, HEIGHT_1, PATH_1);

        //then
        verify(imageTransformer).transform(in, new TransformArg()
                .setWidth(WIDTH_1)
                .setHeight(HEIGHT_1)
                .setColorCorrection(TransformArg.ColorCorrection.GRAYSCALE));
        verify(bufferedImageHelper).open(PATH_1);
        verify(bufferedImageHelper).save(ImageProcessor.RESULT_PREFIX + FILE_NAE, out);
        verifyNoMoreInteractions(bufferedImageHelper, imageTransformer);
    }

    @Test
    public void failedOpenFileTest() throws ImageProcessorException {
        try {
            //given
            doThrow(new ImageProcessorException()).when(bufferedImageHelper).open(any());
            //when
            sut.processImage(WIDTH_1, HEIGHT_1, PATH_1);
            fail();
        } catch (ImageProcessorException e) {
            //then
            verify(bufferedImageHelper).open(PATH_1);
            verifyNoMoreInteractions(bufferedImageHelper, imageTransformer);
        } catch (Exception e) {
            fail();
        }
    }

}