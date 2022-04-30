package com.jef.io.blog.file.fileChange;

import net.sourceforge.tess4j.TesseractException;
import org.junit.Test;

/**
 * 图片转换
 *
 * @author Jef
 * @date 2022/4/8
 */
public class ImageTransferTest {

    @Test
    public void testImageToWord() throws TesseractException {
        ImageTransferUtil.imageToWord("D://test.png");
    }

}