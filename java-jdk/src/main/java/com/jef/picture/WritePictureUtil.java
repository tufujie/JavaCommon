package com.jef.picture;

import com.jef.util.StringUtils;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 画图工具
 * @author Jef
 * @date 2020/7/22
 */
public class WritePictureUtil {

    public static BufferedImage getBufferedImage(String upName, String centerName, String downName, String imagePath) throws Exception {
        BufferedImage image = null;
        if (StringUtils.isNotEmpty(upName)) {
            try {
                image = ImageIO.read(new FileInputStream(imagePath));
            } catch (FileNotFoundException e) {
                throw new Exception("获取资源文件异常");
            } catch (IOException e) {
                throw new Exception("获取资源文件异常");
            }
            int centerY = image.getHeight() / 2;
            int centerX = image.getWidth() / 2;
            int radius = centerX - 20;
            // 得到图形上下文
            Graphics2D g2 = image.createGraphics();
            FontRenderContext context = g2.getFontRenderContext();
            // 设置画笔颜色
            g2.setColor(new Color(180, 107, 105));
            int ilength = 0;
            if (StringUtils.isNotEmpty(downName)) {
                // 输入的字数
                ilength = downName.length();
            }
            int centerFontsize = 48;
            Font centerf = new Font("SimSun", Font.BOLD, centerFontsize);
            Rectangle2D centerfbounds = centerf.getStringBounds(downName, context);
            for (int t = 0; t < 50; t++) {
                if (centerfbounds.getWidth() <= image.getWidth() - 75) {
                    break;
                }
                centerFontsize--;
                centerf = new Font("SimSun", Font.BOLD, centerFontsize);
                centerfbounds = centerf.getStringBounds(downName, context);
            }

            // 设置字体
            g2.setFont(new Font("SimSun", Font.BOLD, centerFontsize));
            // 写入签名
            if (StringUtils.isNotEmpty(downName)) {
                g2.drawString(downName, (float) (centerX - centerfbounds.getWidth() / 2 + 2), centerY * 3 / 2 + 23 - ilength);
            }
            if (StringUtils.isNotEmpty(centerName)) {
                centerFontsize = 45;
                Font innerf = new Font("SimSun", Font.BOLD, centerFontsize);
                Rectangle2D innerfbounds = innerf.getStringBounds(centerName, context);
                for (int t = 0; t < 34; t++) {
                    if (innerfbounds.getWidth() <= image.getWidth() - 130) {
                        break;
                    }
                    centerFontsize--;
                    innerf = new Font("SimSun", Font.BOLD, centerFontsize);
                    innerfbounds = innerf.getStringBounds(centerName, context);
                }
                // 设置字体
                g2.setFont(new Font("SimSun", Font.PLAIN, centerFontsize));
                // 写入签名
                if (StringUtils.isNotEmpty(centerName)) {
                    g2.drawString(centerName, (float) (centerX - innerfbounds.getWidth() / 2 + 15), centerY);
                }
            }
            // 根据输入字符串得到字符数组
            String[] messages2 = (" " + upName.trim()).split("", 0);
            String[] messages;
            if ("".equals(messages2[0]) || " ".equals(messages2[0])) {
                if ("".equals(messages2[1]) || " ".equals(messages2[1])) {
                    messages = new String[messages2.length - 2];
                    System.arraycopy(messages2, 2, messages, 0, messages2.length - 2);
                } else {
                    messages = new String[messages2.length - 1];
                    System.arraycopy(messages2, 1, messages, 0, messages2.length - 1);
                }
            } else {
                messages = new String[messages2.length];
                System.arraycopy(messages2, 0, messages, 0, messages2.length);
            }
            // 输入的字数
            ilength = messages.length;
            // 设置字体属性
            int fontsize;
            if (ilength > 12) {
                fontsize = 48 - (ilength - 11) * 2;
            } else {
                fontsize = 46;
            }
            Font f = new Font("SimSun", Font.BOLD, fontsize);
            Rectangle2D bounds = f.getStringBounds(upName, context);
            // 字符宽度＝字符串长度/字符数
            double charInterval = (bounds.getWidth()) / ilength;
            double charInterval1 = (radius * Math.PI * 0.97) / ilength;
            // 上坡度
            double ascent = -bounds.getY();
            int first, second = 0;
            // 奇偶数
            boolean odd;
            if (ilength % 2 == 1) {
                first = (ilength - 1) / 2;
                odd = true;
            } else {
                first = (ilength) / 2 - 1;
                second = (ilength) / 2;
                odd = false;
            }
            double radius2 = radius - ascent;
            double x0 = centerY;
            double y0 = centerY - radius + ascent;
            // 旋转角度
            double a = 2 * Math.asin(charInterval1 / (2 * radius2));
            if (odd) {
                g2.setFont(f);
                g2.drawString(messages[first], (float) (x0 - charInterval / 2), (float) y0);
                // 中心点的右边
                for (int i = first + 1; i < ilength; i++) {
                    double aa = (i - first) * a;
                    drawChar(g2, messages[i], f, charInterval, radius2, x0, y0, aa, true);
                }
                // 中心点的左边
                for (int i = first - 1; i > -1; i--) {
                    double aa = (first - i) * a;
                    drawChar(g2, messages[i], f, charInterval, radius2, x0, y0, aa, false);
                }
            } else {
                // 中心点的右边
                for (int i = second; i < ilength; i++) {
                    double aa = (i - second + 0.5) * a;
                    drawChar(g2, messages[i], f, charInterval, radius2, x0, y0, aa, true);
                }
                // 中心点的左边
                for (int i = first; i > -1; i--) {
                    double aa = (first - i + 0.5) * a;
                    drawChar(g2, messages[i], f, charInterval, radius2, x0, y0, aa, false);
                }
            }
            g2.dispose();
        }
        return image;

    }

    /**
     * 画字
     * @author NJQ
     * @date Nov 24, 2016 7:33:33 PM
     * @param g2
     * @param message
     * @param f
     * @param charInterval
     * @param radius2
     * @param x0
     * @param y0
     * @param aa
     */
    private static void drawChar(Graphics2D g2, String message, Font f, double charInterval, double radius2, double x0, double y0, double aa, boolean t){
        double ax = radius2 * Math.sin(aa);
        double ay = radius2 - radius2 * Math.cos(aa);
        AffineTransform transform;
        if (t) {
            transform = AffineTransform.getRotateInstance(aa);
        } else {
            transform = AffineTransform.getRotateInstance(-aa);
        }

        Font f2 = f.deriveFont(transform);
        g2.setFont(f2);
        if (t) {
            g2.drawString(message,(float) (x0 + ax - charInterval / 2 * Math.cos(aa)),(float) (y0 + ay - charInterval / 2 * Math.sin(aa)));
        } else {
            g2.drawString(message,(float) (x0 - ax - charInterval / 2 * Math.cos(aa)),(float) (y0 + ay + charInterval / 2 * Math.sin(aa)));
        }

    }

}