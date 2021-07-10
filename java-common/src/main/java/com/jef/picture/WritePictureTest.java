package com.jef.picture;

import com.jef.util.REIDIdentifier;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * 画图
 * @author Jef
 * @date 2020/7/22
 */
public class WritePictureTest {

    @Test
    public void testWritePicture() {
        BufferedImage image;
        String destpath = "E:\\Desktop\\Myget\\MyBatis\\src\\main\\resources";
        String electrionicSealContext = "金蝶环绕圈一金蝶环绕圈二金蝶环绕圈三金蝶环绕圈四金蝶环绕圈五", electronicSealInnerContext = "金蝶印章中一金蝶印章中二金蝶印章中三金蝶印章中四金蝶印章中五",
                electronicSealInnerFunContext = "金蝶软件园一金蝶软件园二金蝶软件园三金蝶软件园四金蝶软件园五", path = "E:\\Desktop\\Myget\\MyBatis\\src\\main\\resources\\test.png";
        try {
            image = WritePictureUtil.getBufferedImage("" + electrionicSealContext, electronicSealInnerContext, electronicSealInnerFunContext, path);
            // 上传图片
            String fileName = REIDIdentifier.randomEOID();
            String fileStr = fileName + ".png";
            File file = new File(destpath);
            // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
            if (!file.exists() && !file.isDirectory()) {
                file.mkdirs();
            }
            File saveFile = new File(file +"\\"+ fileStr);
            ImageIO.write(image, "png", saveFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}