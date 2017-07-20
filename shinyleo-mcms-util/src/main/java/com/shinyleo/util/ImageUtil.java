package com.shinyleo.util;

import com.mortennobel.imagescaling.ResampleOp;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.apache.log4j.Logger;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.MemoryCacheImageInputStream;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.*;
import java.util.Iterator;

/**
 * Created by zhugh on 17/7/20.
 */
public class ImageUtil {

    protected static Logger LOGGER = Logger.getLogger(ImageUtil.class);

    public ImageUtil() {
    }

    public static void batchImageWidthHeight(String directoryPath, int sSize, int tSize) {
        File dir = new File(directoryPath);
        File[] files = dir.listFiles();
        if(files != null) {
            for(int i = 0; i < files.length; ++i) {
                if(files[i].isDirectory()) {
                    batchImageWidthHeight(files[i].getAbsolutePath(), sSize, tSize);
                } else {
                    try {
                        files[i].getPath();
                        if(isPic(files[i].getPath())) {
                            BufferedImage srcImage = ImageIO.read(files[i]);
                            getImageWidthHeight(srcImage, sSize, tSize);
                        }
                    } catch (IOException var8) {
                        var8.printStackTrace();
                    }
                }
            }

        }
    }

    public static void ChangeImage(String root, double scale) throws IOException {
        File file = new File(root);
        File[] subFile = file.listFiles();

        for(int i = 0; i < subFile.length; ++i) {
            String name = subFile[i].getName();
            if(subFile[i].isDirectory()) {
                ChangeImage(subFile[i].getAbsolutePath() + "\\", scale);
            }

            String[] names = name.split("//.");
            if(StringUtil.isBlank(names[0])) {
                break;
            }

            scaleHyaline(root + subFile[i].getName(), root + subFile[i].getName(), scale, true);
        }

    }

    public static void convert(String source, String result) {
        try {
            File e = new File(source);
            e.canRead();
            e.canWrite();
            BufferedImage src = ImageIO.read(e);
            ImageIO.write(src, "JPG", new File(result));
        } catch (Exception var4) {
            LOGGER.error(var4);
        }

    }

    public static void createImage(String path, BufferedImage bi) {
        try {
            ImageIO.write(bi, path.substring(path.lastIndexOf("."), path.length()).replace(".", ""), new File(path));
        } catch (IOException var3) {
            LOGGER.error(var3);
        }

    }

    public static void createImage(String path, byte[] bt) {
    }

    public static void cut(String sourceImagePath, String descDir, int width, int height) {
        try {
            BufferedImage bi = ImageIO.read(new File(sourceImagePath));
            int srcWidth = bi.getHeight();
            int srcHeight = bi.getWidth();
            if(srcWidth > width && srcHeight > height) {
                Image image = bi.getScaledInstance(srcWidth, srcHeight, 1);
                short var17 = 200;
                short var18 = 150;
                boolean cols = false;
                boolean rows = false;
                int var19;
                if(srcWidth % var17 == 0) {
                    var19 = srcWidth / var17;
                } else {
                    var19 = (int)Math.floor((double)(srcWidth / var17)) + 1;
                }

                int var20;
                if(srcHeight % var18 == 0) {
                    var20 = srcHeight / var18;
                } else {
                    var20 = (int)Math.floor((double)(srcHeight / var18)) + 1;
                }

                for(int i = 0; i < var20; ++i) {
                    for(int j = 0; j < var19; ++j) {
                        CropImageFilter cropFilter = new CropImageFilter(j * 200, i * 150, var17, var18);
                        Image e = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), cropFilter));
                        BufferedImage tag = new BufferedImage(var17, var18, 1);
                        Graphics g = tag.getGraphics();
                        g.drawImage(e, 0, 0, (ImageObserver)null);
                        g.dispose();
                        ImageIO.write(tag, "JPEG", new File(descDir + "pre_map_" + i + "_" + j + ".jpg"));
                    }
                }
            }
        } catch (Exception var16) {
            var16.printStackTrace();
        }

    }

    public static void decodeImage(String sourceImagePath) {
        File file = new File(sourceImagePath);
        if(file.exists()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            boolean length = true;

            try {
                FileInputStream is = new FileInputStream(file);

                try {
                    int length1;
                    while((length1 = is.read(buffer)) != -1) {
                        baos.write(buffer, 0, length1);
                    }

                    baos.flush();
                } catch (IOException var11) {
                    var11.printStackTrace();
                }

                byte[] e1 = baos.toByteArray();
                e1[0] = -1;
                FileOutputStream os = new FileOutputStream(file);

                try {
                    os.write(e1);
                    os.flush();
                    os.close();
                } catch (IOException var10) {
                    var10.printStackTrace();
                }

                try {
                    is.close();
                    baos.close();
                } catch (IOException var9) {
                    var9.printStackTrace();
                }
            } catch (FileNotFoundException var12) {
                var12.printStackTrace();
            }
        }

    }

    public static void encodeImage(String sourceImagePath) {
        File file = new File(sourceImagePath);
        if(file.exists()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            boolean length = true;

            try {
                FileInputStream is = new FileInputStream(file);

                try {
                    int length1;
                    while((length1 = is.read(buffer)) != -1) {
                        baos.write(buffer, 0, length1);
                    }

                    baos.flush();
                } catch (IOException var11) {
                    var11.printStackTrace();
                }

                byte[] e1 = baos.toByteArray();
                e1[0] = 0;
                FileOutputStream os = new FileOutputStream(file);

                try {
                    os.write(e1);
                    os.flush();
                    os.close();
                } catch (IOException var10) {
                    var10.printStackTrace();
                }

                try {
                    is.close();
                    baos.close();
                } catch (IOException var9) {
                    var9.printStackTrace();
                }
            } catch (FileNotFoundException var12) {
                var12.printStackTrace();
            }
        }

    }

    public static String getImageType(byte[] imageBytes) {
        ByteArrayInputStream bais = null;
        MemoryCacheImageInputStream mcis = null;

        try {
            bais = new ByteArrayInputStream(imageBytes);
            mcis = new MemoryCacheImageInputStream(bais);
            Iterator e = ImageIO.getImageReaders(mcis);

            while(e.hasNext()) {
                ImageReader reader = (ImageReader)e.next();
                String imageName = reader.getClass().getSimpleName();
                if(imageName != null && "JPEGImageReader".equalsIgnoreCase(imageName)) {
                    return "jpeg";
                }

                if(imageName != null && "JPGImageReader".equalsIgnoreCase(imageName)) {
                    return "jpg";
                }

                if(imageName != null && "pngImageReader".equalsIgnoreCase(imageName)) {
                    return "png";
                }
            }
        } catch (Exception var6) {
            LOGGER.error(var6);
        }

        return null;
    }

    private static int[] getImageWidthHeight(BufferedImage source, int sourceWidth, int targetWidth) {
        double ts = (double)targetWidth / (double)sourceWidth;
        int newWidth = (int)((double)source.getWidth() * ts);
        int newHeight = (int)((double)source.getHeight() * ts);
        if(newWidth < 3) {
            newWidth = 3;
        }

        if(newHeight < 3) {
            newHeight = 3;
        }

        int[] wh = new int[]{newWidth, newHeight};
        return wh;
    }

    public static int[] getImageWidthHeight(String sourceImagePath) {
        try {
            BufferedImage bi = ImageIO.read(new File(sourceImagePath));
            int[] e = new int[]{bi.getWidth(), bi.getHeight()};
            return e;
        } catch (IOException var3) {
            LOGGER.error(var3);
            return null;
        }
    }

    public static void gray(String sourceImagePath, String savePath) {
        try {
            BufferedImage e = ImageIO.read(new File(sourceImagePath));
            ColorSpace cs = ColorSpace.getInstance(1003);
            ColorConvertOp op = new ColorConvertOp(cs, (RenderingHints)null);
            e = op.filter(e, (BufferedImage)null);
            ImageIO.write(e, "JPEG", new File(savePath));
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    private static boolean isPic(String sourceImagePath) {
        String picSfix = "jpg|png|gif";
        String[] temp = picSfix.split("\\|");
        if(sourceImagePath.indexOf(".") > 0) {
            String fileSfix = sourceImagePath.substring(sourceImagePath.indexOf("."), sourceImagePath.length()).replace(".", "");

            for(int i = 0; i < temp.length; ++i) {
                if(fileSfix.equals(temp[i])) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
    }

    public static BufferedImage resize(String sourceImagePath, int width, int height) {
        try {
            BufferedImage inputBufImage = ImageIO.read(new File(sourceImagePath));
            ResampleOp e1 = new ResampleOp(Math.min(width, inputBufImage.getWidth()), Math.min(height, inputBufImage.getHeight()));
            BufferedImage rescaledTomato = e1.filter(inputBufImage, (BufferedImage)null);
            return rescaledTomato;
        } catch (IOException var6) {
            var6.printStackTrace();
            return null;
        }
    }

    public static boolean resizeImage(File sourceImagePath, String savePath, int width, int height, String sufix) {
        try {
            BufferedImage e = ImageIO.read(sourceImagePath);
            ResampleOp resampleOp = new ResampleOp(Math.min(width, e.getWidth()), Math.min(height, e.getHeight()));
            BufferedImage rescaledTomato = resampleOp.filter(e, (BufferedImage)null);
            ImageIO.write(rescaledTomato, sufix, new File(savePath));
            return true;
        } catch (Exception var8) {
            LOGGER.error(var8);
            return false;
        }
    }

    public static byte[] resizeImageForBytes(String sourceImagePath, int targetW, int targetH, String type) {
        try {
            BufferedImage ex = resize(sourceImagePath, targetW, targetH);
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            ImageIO.write(ex, type, outStream);
            outStream.flush();
            byte[] result = outStream.toByteArray();
            outStream.close();
            return result;
        } catch (Exception var7) {
            LOGGER.error(var7);
            return null;
        }
    }

    public static boolean resizeImageForEncode(File sourceImageFile, String savePath, Integer width, Integer height, String sufix) {
        try {
            BufferedImage e = ImageIO.read(sourceImageFile);
            ResampleOp resampleOp = new ResampleOp(Math.min(width.intValue(), e.getWidth()), Math.min(height.intValue(), e.getHeight()));
            BufferedImage rescaledTomato = resampleOp.filter(e, (BufferedImage)null);
            ImageIO.write(rescaledTomato, sufix, new File(savePath));
            return true;
        } catch (Exception var8) {
            LOGGER.error(var8);
            return false;
        }
    }

    public static void rotate(String sourceImagePath, int direction) {
        File file = null;
        BufferedImage original = null;
        BufferedImage bufOut = null;
        file = new File(sourceImagePath);

        try {
            original = ImageIO.read(file);
        } catch (Exception var11) {
            return;
        }

        int width = original.getWidth();
        int height = original.getHeight();
        bufOut = new BufferedImage(width, height, original.getType());
        AffineTransform atx = new AffineTransform();
        switch(direction) {
            case 0:
                atx.rotate(-1.5707963267948966D, (double)(width / 2), (double)(height / 2));
                break;
            case 1:
                atx.rotate(1.5707963267948966D, (double)(width / 2), (double)(height / 2));
        }

        AffineTransformOp atop = new AffineTransformOp(atx, 3);
        atop.filter(original, bufOut);
        bufOut = bufOut.getSubimage(0, 0, width, height);

        try {
            ImageIO.write(bufOut, "JPG", new File(sourceImagePath));
        } catch (IOException var10) {
            LOGGER.debug(var10);
        }

    }

    public static void saveImageAsJpg(String sourceImagePath, String savePath, int width, int hight) {
        BufferedImage srcImage = null;
        String imgType = "JPEG";
        if(sourceImagePath.toLowerCase().endsWith(".png")) {
            imgType = "PNG";
        }

        File saveFile = new File(savePath);
        File fromFile = new File(sourceImagePath);

        try {
            srcImage = ImageIO.read(fromFile);
        } catch (IOException var10) {
            LOGGER.error(var10);
        }

        if(width > 0 || hight > 0) {
            srcImage = resize(sourceImagePath, width, hight);
        }

        try {
            ImageIO.write(srcImage, imgType, saveFile);
        } catch (IOException var9) {
            LOGGER.error(var9);
        }

    }

    public static void scale(String sourceImagePath, String savePath, double scale, boolean flag) {
        try {
            BufferedImage e = ImageIO.read(new File(sourceImagePath));
            int width = e.getWidth();
            int height = e.getHeight();
            if(flag) {
                width = (int)((double)width * scale);
                height = (int)((double)height * scale);
            } else {
                width = (int)((double)width / scale);
                height = (int)((double)height / scale);
            }

            Image image = e.getScaledInstance(width, height, 1);
            BufferedImage tag = new BufferedImage(width, height, 1);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, (ImageObserver)null);
            g.dispose();
            ImageIO.write(tag, "JPEG", new File(savePath));
        } catch (IOException var11) {
            LOGGER.equals(var11);
        }

    }

    public static void scaleHyaline(String sourceImagePath, String savePath, double scale, boolean flag) {
        if(isPic(sourceImagePath)) {
            try {
                BufferedImage e = ImageIO.read(new File(sourceImagePath));
                BufferedImage dstImage = null;
                AffineTransform transform = AffineTransform.getScaleInstance(scale, scale);
                AffineTransformOp op = new AffineTransformOp(transform, 2);
                dstImage = op.filter(e, (BufferedImage)null);

                try {
                    ImageIO.write(dstImage, "png", new File(savePath));
                } catch (IOException var10) {
                    var10.printStackTrace();
                }
            } catch (IOException var11) {
                LOGGER.equals(var11);
            }
        }

    }

    public static void cut(int x, int y, int width, int height, String sourceImagePath, String savePath) {
        FileInputStream is = null;
        ImageInputStream iis = null;

        try {
            try {
                is = new FileInputStream(sourceImagePath);
            } catch (FileNotFoundException var32) {
                LOGGER.error("图片未找到:" + sourceImagePath);
                LOGGER.error(var32);
            }

            Iterator it = ImageIO.getImageReadersByFormatName("jpg");
            ImageReader reader = (ImageReader)it.next();

            try {
                iis = ImageIO.createImageInputStream(is);
            } catch (IOException var31) {
                var31.printStackTrace();
                LOGGER.error("图片未找到:" + sourceImagePath);
                LOGGER.error(var31);
            }

            reader.setInput(iis, true);
            ImageReadParam param = reader.getDefaultReadParam();

            try {
                reader.read(0);
            } catch (IOException var30) {
                LOGGER.error("图片未找到:" + sourceImagePath);
                LOGGER.error(var30);
            }

            Rectangle rect = new Rectangle(x, y, width, height);
            param.setSourceRegion(rect);
            BufferedImage bi = null;

            try {
                bi = reader.read(0, param);
            } catch (IOException var29) {
                LOGGER.error("图片未找到:" + sourceImagePath);
                LOGGER.error(var29);
            }

            try {
                ImageIO.write(bi, "jpg", new File(savePath));
            } catch (IOException var28) {
                LOGGER.error("输出路径不正确:" + savePath);
                LOGGER.error(var28);
            }
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException var27) {
                    LOGGER.error(var27);
                }
            }

            if(iis != null) {
                try {
                    iis.close();
                } catch (IOException var26) {
                    LOGGER.error(var26);
                }
            }

        }

    }

    public static void formatImage(String path, String fix) {
        String _path = path;

        try {
            File ex = new File(_path);
            FileInputStream is = new FileInputStream(ex);
            BufferedImage image = ImageIO.read(is);
            BufferedImage tag = new BufferedImage(image.getWidth(), image.getHeight(), 1);
            tag.getGraphics().drawImage(image, 0, 0, image.getWidth(), image.getHeight(), (ImageObserver)null);
            FileOutputStream newimage = new FileOutputStream(path.substring(0, path.lastIndexOf(".")) + fix);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(newimage);
            encoder.encode(tag);
            newimage.close();
        } catch (Exception var9) {
            LOGGER.equals(var9);
        }

    }

    public static void zoom(String sourceImagePath, String savePath, int targetWidth, int targetHeight, boolean more, String exp) {
        File file = null;
        BufferedImage original = null;
        BufferedImage bufOut = null;
        int width;
        int height;
        double xScale;
        double yScale;
        if(more) {
            file = new File(sourceImagePath);

            try {
                original = ImageIO.read(file);
            } catch (IOException var27) {
                LOGGER.debug(var27);
            }

            width = original.getWidth();
            height = original.getHeight();
            if(targetWidth >= width && targetHeight >= height) {
                targetWidth = width;
                targetHeight = height;
            }

            bufOut = new BufferedImage(width, height, original.getType());
            double filePath = (new Integer(targetWidth)).doubleValue() / (double)width;
            xScale = (new Integer(targetHeight)).doubleValue() / (double)height;
            yScale = Math.min(filePath, xScale);
            int scale = (new Double((double)original.getWidth() * yScale)).intValue();
            int newHeight = (new Double((double)original.getHeight() * yScale)).intValue();
            AffineTransform newWidth = AffineTransform.getScaleInstance(yScale, yScale);
            AffineTransformOp newHeight1 = new AffineTransformOp(newWidth, 2);
            newHeight1.filter(original, bufOut);
            bufOut = bufOut.getSubimage(0, 0, scale, newHeight);

            try {
                ImageIO.write(bufOut, "JPG", file);
            } catch (IOException var26) {
                LOGGER.debug(var26);
            }
        } else {
            if(sourceImagePath == null && sourceImagePath.length() > 0) {
                return;
            }

            String[] var28 = sourceImagePath.split(exp);
            if(var28.length > 0) {
                for(int i = 0; i < var28.length; ++i) {
                    file = new File(savePath);

                    try {
                        original = ImageIO.read(file);
                    } catch (Exception var25) {
                        return;
                    }

                    width = original.getWidth();
                    height = original.getHeight();
                    if(targetWidth >= width && targetHeight >= height) {
                        targetWidth = width;
                        targetHeight = height;
                    }

                    bufOut = new BufferedImage(width, height, original.getType());
                    xScale = (new Integer(targetWidth)).doubleValue() / (double)width;
                    yScale = (new Integer(targetHeight)).doubleValue() / (double)height;
                    double var29 = Math.min(xScale, yScale);
                    int var30 = (new Double((double)original.getWidth() * var29)).intValue();
                    int var31 = (new Double((double)original.getHeight() * var29)).intValue();
                    AffineTransform atx = AffineTransform.getScaleInstance(var29, var29);
                    AffineTransformOp atop = new AffineTransformOp(atx, 2);
                    atop.filter(original, bufOut);
                    bufOut = bufOut.getSubimage(0, 0, var30, var31);

                    try {
                        ImageIO.write(bufOut, "JPG", new File(savePath));
                    } catch (IOException var24) {
                        LOGGER.debug(var24);
                    }
                }
            }
        }

    }
}
