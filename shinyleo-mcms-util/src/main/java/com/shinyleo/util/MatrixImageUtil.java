package com.shinyleo.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

/**
 * Created by zhugh on 17/7/20.
 */
public class MatrixImageUtil {

    public MatrixImageUtil() {
    }

    public static void encode(String content, String imgPath, int width, int height) {
        try {
            if(StringUtil.isBlank(content) || StringUtil.isBlank(imgPath)) {
                return;
            }

            BitMatrix e = (new MultiFormatWriter()).encode(new String(content.getBytes("utf-8"), "iso-8859-1"), BarcodeFormat.QR_CODE, width, height);
            File file = new File(imgPath);
            MatrixToImageWriter.writeToFile(e, "png", file);
        } catch (Exception var6) {
            var6.printStackTrace();
        }

    }

    public static String decode(String imgPath) {
        try {
            File file = new File(imgPath);

            try {
                BufferedImage image = ImageIO.read(file);
                if(image == null) {
                    System.out.println("Could not decode image");
                }

                BufferedImageLuminanceSource re = new BufferedImageLuminanceSource(image);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(re));
                Hashtable hints = new Hashtable();
                hints.put(DecodeHintType.CHARACTER_SET, "GBK");
                Result result = (new MultiFormatReader()).decode(bitmap, hints);
                String resultStr = result.getText();
                return resultStr;
            } catch (IOException var8) {
                System.out.println(var8.toString());
            } catch (ReaderException var9) {
                System.out.println(var9.toString());
            }
        } catch (Exception var10) {
            ;
        }

        return "";
    }
}
