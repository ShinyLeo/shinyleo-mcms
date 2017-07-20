package com.shinyleo.proxy.util;

/**
 * Created by shinyleo on 17/7/20.
 */
public class SHA1 {

    private final int[] abcde = new int[]{1732584193, -271733879, -1732584194, 271733878, -1009589776};
    private int[] digestInt = new int[5];
    private int[] tmpData = new int[80];

    public SHA1() {
    }

    private int process_input_bytes(byte[] bytedata) {
        System.arraycopy(this.abcde, 0, this.digestInt, 0, this.abcde.length);
        byte[] newbyte = this.byteArrayFormatData(bytedata);
        int MCount = newbyte.length / 64;

        for(int pos = 0; pos < MCount; ++pos) {
            for(int j = 0; j < 16; ++j) {
                this.tmpData[j] = this.byteArrayToInt(newbyte, pos * 64 + j * 4);
            }

            this.encrypt();
        }

        return 20;
    }

    private byte[] byteArrayFormatData(byte[] bytedata) {
        boolean zeros = false;
        boolean size = false;
        int n = bytedata.length;
        int m = n % 64;
        int var18;
        int var19;
        if(m < 56) {
            var18 = 55 - m;
            var19 = n - m + 64;
        } else if(m == 56) {
            var18 = 63;
            var19 = n + 8 + 64;
        } else {
            var18 = 63 - m + 56;
            var19 = n + 64 - m + 64;
        }

        byte[] newbyte = new byte[var19];
        System.arraycopy(bytedata, 0, newbyte, 0, n);
        int l = n + 1;
        newbyte[n] = -128;

        for(int N = 0; N < var18; ++N) {
            newbyte[l++] = 0;
        }

        long var20 = (long)n * 8L;
        byte h8 = (byte)((int)(var20 & 255L));
        byte h7 = (byte)((int)(var20 >> 8 & 255L));
        byte h6 = (byte)((int)(var20 >> 16 & 255L));
        byte h5 = (byte)((int)(var20 >> 24 & 255L));
        byte h4 = (byte)((int)(var20 >> 32 & 255L));
        byte h3 = (byte)((int)(var20 >> 40 & 255L));
        byte h2 = (byte)((int)(var20 >> 48 & 255L));
        byte h1 = (byte)((int)(var20 >> 56));
        newbyte[l++] = h1;
        newbyte[l++] = h2;
        newbyte[l++] = h3;
        newbyte[l++] = h4;
        newbyte[l++] = h5;
        newbyte[l++] = h6;
        newbyte[l++] = h7;
        newbyte[l++] = h8;
        return newbyte;
    }

    private int f1(int x, int y, int z) {
        return x & y | ~x & z;
    }

    private int f2(int x, int y, int z) {
        return x ^ y ^ z;
    }

    private int f3(int x, int y, int z) {
        return x & y | x & z | y & z;
    }

    private int f4(int x, int y) {
        return x << y | x >>> 32 - y;
    }

    private void encrypt() {
        for(int tmpabcde = 16; tmpabcde <= 79; ++tmpabcde) {
            this.tmpData[tmpabcde] = this.f4(this.tmpData[tmpabcde - 3] ^ this.tmpData[tmpabcde - 8] ^ this.tmpData[tmpabcde - 14] ^ this.tmpData[tmpabcde - 16], 1);
        }

        int[] var4 = new int[5];

        int n;
        for(n = 0; n < var4.length; ++n) {
            var4[n] = this.digestInt[n];
        }

        int tmp;
        for(n = 0; n <= 19; ++n) {
            tmp = this.f4(var4[0], 5) + this.f1(var4[1], var4[2], var4[3]) + var4[4] + this.tmpData[n] + 1518500249;
            var4[4] = var4[3];
            var4[3] = var4[2];
            var4[2] = this.f4(var4[1], 30);
            var4[1] = var4[0];
            var4[0] = tmp;
        }

        for(n = 20; n <= 39; ++n) {
            tmp = this.f4(var4[0], 5) + this.f2(var4[1], var4[2], var4[3]) + var4[4] + this.tmpData[n] + 1859775393;
            var4[4] = var4[3];
            var4[3] = var4[2];
            var4[2] = this.f4(var4[1], 30);
            var4[1] = var4[0];
            var4[0] = tmp;
        }

        for(n = 40; n <= 59; ++n) {
            tmp = this.f4(var4[0], 5) + this.f3(var4[1], var4[2], var4[3]) + var4[4] + this.tmpData[n] + -1894007588;
            var4[4] = var4[3];
            var4[3] = var4[2];
            var4[2] = this.f4(var4[1], 30);
            var4[1] = var4[0];
            var4[0] = tmp;
        }

        for(n = 60; n <= 79; ++n) {
            tmp = this.f4(var4[0], 5) + this.f2(var4[1], var4[2], var4[3]) + var4[4] + this.tmpData[n] + -899497514;
            var4[4] = var4[3];
            var4[3] = var4[2];
            var4[2] = this.f4(var4[1], 30);
            var4[1] = var4[0];
            var4[0] = tmp;
        }

        for(n = 0; n < var4.length; ++n) {
            this.digestInt[n] += var4[n];
        }

        for(n = 0; n < this.tmpData.length; ++n) {
            this.tmpData[n] = 0;
        }

    }

    private int byteArrayToInt(byte[] bytedata, int i) {
        return (bytedata[i] & 255) << 24 | (bytedata[i + 1] & 255) << 16 | (bytedata[i + 2] & 255) << 8 | bytedata[i + 3] & 255;
    }

    private void intToByteArray(int intValue, byte[] byteData, int i) {
        byteData[i] = (byte)(intValue >>> 24);
        byteData[i + 1] = (byte)(intValue >>> 16);
        byteData[i + 2] = (byte)(intValue >>> 8);
        byteData[i + 3] = (byte)intValue;
    }

    private static String byteToHexString(byte ib) {
        char[] Digit = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] ob = new char[]{Digit[ib >>> 4 & 15], Digit[ib & 15]};
        String s = new String(ob);
        return s;
    }

    private static String byteArrayToHexString(byte[] bytearray) {
        String strDigest = "";

        for(int i = 0; i < bytearray.length; ++i) {
            strDigest = strDigest + byteToHexString(bytearray[i]);
        }

        return strDigest;
    }

    public byte[] getDigestOfBytes(byte[] byteData) {
        this.process_input_bytes(byteData);
        byte[] digest = new byte[20];

        for(int i = 0; i < this.digestInt.length; ++i) {
            this.intToByteArray(this.digestInt[i], digest, i * 4);
        }

        return digest;
    }

    public String getDigestOfString(byte[] byteData) {
        return byteArrayToHexString(this.getDigestOfBytes(byteData));
    }

    public static void main(String[] args) {
        String data = "123456";
        System.out.println(data);
        String digest = (new SHA1()).getDigestOfString(data.getBytes());
        System.out.println(digest);
    }

}
