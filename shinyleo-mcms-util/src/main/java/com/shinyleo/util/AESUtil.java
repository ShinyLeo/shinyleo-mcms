package com.shinyleo.util;

import org.apache.log4j.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by shinyleo on 17/7/20.
 */
public class AESUtil {

    protected static final Logger LOG = Logger.getLogger(AESUtil.class.getClass());

    public AESUtil() {
    }

    public static String decrypt(String decryptStr, String strKey) {
        try {
            if(strKey == null) {
                LOG.debug("Key为空null");
                return null;
            } else if(strKey.length() != 16) {
                LOG.debug("Key长度不是16位");
                return null;
            } else {
                byte[] ex = strKey.getBytes("ASCII");
                SecretKeySpec skeySpec = new SecretKeySpec(ex, "AES");
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(2, skeySpec);
                byte[] encrypted1 = hex2byte(decryptStr);

                try {
                    byte[] e = cipher.doFinal(encrypted1);
                    String originalString = new String(e);
                    return originalString;
                } catch (Exception var8) {
                    System.out.println(var8.toString());
                    return null;
                }
            }
        } catch (Exception var9) {
            System.out.println(var9.toString());
            return null;
        }
    }

    public static String encrypt(String encryptStr, String strKey) {
        if(strKey == null) {
            LOG.debug("Key为空null");
            return null;
        } else if(strKey.length() != 16) {
            LOG.debug("Key长度不是16位");
            return null;
        } else {
            byte[] encrypted = null;

            try {
                byte[] e = strKey.getBytes("ASCII");
                SecretKeySpec skeySpec = new SecretKeySpec(e, "AES");
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(1, skeySpec);
                encrypted = cipher.doFinal(encryptStr.getBytes());
            } catch (UnsupportedEncodingException var6) {
                var6.printStackTrace();
            } catch (NoSuchPaddingException var7) {
                var7.printStackTrace();
            } catch (NoSuchAlgorithmException var8) {
                var8.printStackTrace();
            } catch (InvalidKeyException var9) {
                var9.printStackTrace();
            } catch (IllegalBlockSizeException var10) {
                var10.printStackTrace();
            } catch (BadPaddingException var11) {
                var11.printStackTrace();
            }

            return byte2hex(encrypted).toLowerCase();
        }
    }

    public static byte[] hex2byte(String str) {
        if(str == null) {
            return null;
        } else {
            int l = str.length();
            if(l % 2 == 1) {
                return null;
            } else {
                byte[] bytes = new byte[l / 2];

                for(int i = 0; i != l / 2; ++i) {
                    bytes[i] = (byte)Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16);
                }

                return bytes;
            }
        }
    }

    public static String byte2hex(byte[] bytes) {
        if(bytes == null) {
            return "";
        } else {
            String hs = "";
            String stmp = "";

            for(int n = 0; n < bytes.length; ++n) {
                stmp = Integer.toHexString(bytes[n] & 255);
                if(stmp.length() == 1) {
                    hs = hs + "0" + stmp;
                } else {
                    hs = hs + stmp;
                }
            }

            return hs.toUpperCase();
        }
    }
}
