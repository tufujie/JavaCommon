package com.jef.io.blog.file.fileChange;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

/**
 * 图片转化
 *
 * @author Administrator
 * @date 2022/4/28
 */
public class ImageTransferUtil {

    /**
     * 识别图片
     *
     * @param imageUrl
     * @throws TesseractException
     */
    public static void imageToWord(String imageUrl) throws TesseractException {
        ITesseract instance = new Tesseract();
        //如果未将tessdata放在根目录下需要指定绝对路径
        instance.setDatapath("E:\\Desktop\\Myget\\JavaCommon\\java-jdk\\tessdata");
        //如果需要识别英文之外的语种，需要指定识别语种，并且需要将对应的语言包放进项目中
        instance.setLanguage("chi_sim");
        // 指定识别图片
        File imgDir = new File(imageUrl);
        long startTime = System.currentTimeMillis();
        String ocrResult = instance.doOCR(imgDir);
        System.out.println(ocrResult);
        // 输出识别结果
//        System.out.println("OCR Result: \n" + ocrResult + "\n 耗时：" + (System.currentTimeMillis() - startTime) + "ms");
    }

}