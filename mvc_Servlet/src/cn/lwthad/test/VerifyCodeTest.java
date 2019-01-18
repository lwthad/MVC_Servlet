package cn.lwthad.test;

import cn.lwthad.utils.VerifyCode;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

public class VerifyCodeTest {
    @Test
    public void fun1() throws IOException {
        /**
         * 1. 创建图片缓冲区
         * 2. 设置其宽高
         * 3. 得到这个图片的绘制环境（得到画笔）
         * 4. 保存起来
         */
        BufferedImage bufferedImage = new BufferedImage(70,35,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0,0,70,35);
        graphics2D.setColor(Color.RED);
        graphics2D.drawString("Hello", 2, 35-2);

        ImageIO.write(bufferedImage,"JPEG",new FileOutputStream("C:/Users/tao/desktop/xx.jpg"));
    }

    @Test
    public void VerifyCodeTest() throws IOException {
        VerifyCode verifyCode = new VerifyCode();
        BufferedImage bufferedImage = verifyCode.createImage();
        ImageIO.write(bufferedImage, "JPEG", new FileOutputStream("C:/Users/tao/desktop/xx.jpg"));

        System.out.println(verifyCode.getText());
    }
}
