package com.shinyleo.util;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by zhugh on 17/7/20.
 */
public class QrcodeUtil {

    private static final int BLACK = -16777216;
    private static final int WHITE = -1;

    public QrcodeUtil() {
    }

    public static void encode(String contents, File file, BarcodeFormat format, int width, int height, Map<EncodeHintType, ?> hints) {
        try {
            BitMatrix e = (new MultiFormatWriter()).encode(contents, format, width, height);
            BufferedImage image = new BufferedImage(width, height, 2);

            for(int x = 0; x < width; ++x) {
                for(int y = 0; y < height; ++y) {
                    image.setRGB(x, y, e.get(x, y)?-16777216:-1);
                }
            }

            ImageIO.write(image, "png", file);
        } catch (Exception var10) {
            var10.printStackTrace();
        }

    }

    public void decode(File file) {
        try {
            try {
                BufferedImage ex = ImageIO.read(file);
                if(ex == null) {
                    System.out.println("Could not decode image");
                }

                BufferedImageLuminanceSource re = new BufferedImageLuminanceSource(ex);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(re));
                Hashtable hints = new Hashtable();
                hints.put(DecodeHintType.CHARACTER_SET, "utf-8");
                Result result = (new MultiFormatReader()).decode(bitmap, hints);
                String resultStr = result.getText();
                System.out.println("解析后内容：" + resultStr);
            } catch (IOException var8) {
                System.out.println(var8.toString());
            } catch (ReaderException var9) {
                System.out.println(var9.toString());
            }
        } catch (Exception var10) {
            System.out.println(var10.toString());
        }

    }
}
