package com.shinyleo.util;

import org.apache.commons.codec.binary.Base64;

/**
 * Created by shinyleo on 17/7/20.
 */
public class Base64Util {

    private static final int BASELENGTH = 255;
    private static final byte[] base64Alphabet = new byte[255];
    private static final int EIGHTBIT = 8;
    private static final boolean fDebug = false;
    private static final int FOURBYTE = 4;
    private static final int LOOKUPLENGTH = 64;
    private static final char[] lookUpBase64Alphabet = new char[64];
    private static final char PAD = '=';
    private static final int SIGN = -128;
    private static final int SIXBIT = 6;
    private static final int SIXTEENBIT = 16;
    private static final int TWENTYFOURBITGROUP = 24;

    static {
        int i;
        for(i = 0; i < 255; ++i) {
            base64Alphabet[i] = -1;
        }

        for(i = 90; i >= 65; --i) {
            base64Alphabet[i] = (byte)(i - 65);
        }

        for(i = 122; i >= 97; --i) {
            base64Alphabet[i] = (byte)(i - 97 + 26);
        }

        for(i = 57; i >= 48; --i) {
            base64Alphabet[i] = (byte)(i - 48 + 52);
        }

        base64Alphabet[43] = 62;
        base64Alphabet[47] = 63;

        for(i = 0; i <= 25; ++i) {
            lookUpBase64Alphabet[i] = (char)(65 + i);
        }

        i = 26;

        int j;
        for(j = 0; i <= 51; ++j) {
            lookUpBase64Alphabet[i] = (char)(97 + j);
            ++i;
        }

        i = 52;

        for(j = 0; i <= 61; ++j) {
            lookUpBase64Alphabet[i] = (char)(48 + j);
            ++i;
        }

        lookUpBase64Alphabet[62] = 43;
        lookUpBase64Alphabet[63] = 47;
    }

    public Base64Util() {
    }

    public static byte[] decode(String encoded) {
        if(encoded == null) {
            return null;
        } else {
            char[] base64Data = encoded.toCharArray();
            int len = removeWhiteSpace(base64Data);
            if(len % 4 != 0) {
                return null;
            } else {
                int numberQuadruple = len / 4;
                if(numberQuadruple == 0) {
                    return new byte[0];
                } else {
                    Object decodedData = null;
                    boolean b1 = false;
                    boolean b2 = false;
                    boolean b3 = false;
                    boolean b4 = false;
                    boolean d1 = false;
                    boolean d2 = false;
                    boolean d3 = false;
                    boolean d4 = false;
                    int i = 0;
                    int encodedIndex = 0;
                    int dataIndex = 0;

                    byte[] var17;
                    byte var18;
                    byte var19;
                    byte var20;
                    byte var21;
                    char var22;
                    char var23;
                    char var24;
                    char var25;
                    for(var17 = new byte[numberQuadruple * 3]; i < numberQuadruple - 1; ++i) {
                        if(!isData(var22 = base64Data[dataIndex++]) || !isData(var23 = base64Data[dataIndex++]) || !isData(var24 = base64Data[dataIndex++]) || !isData(var25 = base64Data[dataIndex++])) {
                            return null;
                        }

                        var18 = base64Alphabet[var22];
                        var19 = base64Alphabet[var23];
                        var20 = base64Alphabet[var24];
                        var21 = base64Alphabet[var25];
                        var17[encodedIndex++] = (byte)(var18 << 2 | var19 >> 4);
                        var17[encodedIndex++] = (byte)((var19 & 15) << 4 | var20 >> 2 & 15);
                        var17[encodedIndex++] = (byte)(var20 << 6 | var21);
                    }

                    if(isData(var22 = base64Data[dataIndex++]) && isData(var23 = base64Data[dataIndex++])) {
                        var18 = base64Alphabet[var22];
                        var19 = base64Alphabet[var23];
                        var24 = base64Data[dataIndex++];
                        var25 = base64Data[dataIndex++];
                        if(isData(var24) && isData(var25)) {
                            var20 = base64Alphabet[var24];
                            var21 = base64Alphabet[var25];
                            var17[encodedIndex++] = (byte)(var18 << 2 | var19 >> 4);
                            var17[encodedIndex++] = (byte)((var19 & 15) << 4 | var20 >> 2 & 15);
                            var17[encodedIndex++] = (byte)(var20 << 6 | var21);
                            return var17;
                        } else {
                            byte[] tmp;
                            if(isPad(var24) && isPad(var25)) {
                                if((var19 & 15) != 0) {
                                    return null;
                                } else {
                                    tmp = new byte[i * 3 + 1];
                                    System.arraycopy(var17, 0, tmp, 0, i * 3);
                                    tmp[encodedIndex] = (byte)(var18 << 2 | var19 >> 4);
                                    return tmp;
                                }
                            } else if(!isPad(var24) && isPad(var25)) {
                                var20 = base64Alphabet[var24];
                                if((var20 & 3) != 0) {
                                    return null;
                                } else {
                                    tmp = new byte[i * 3 + 2];
                                    System.arraycopy(var17, 0, tmp, 0, i * 3);
                                    tmp[encodedIndex++] = (byte)(var18 << 2 | var19 >> 4);
                                    tmp[encodedIndex] = (byte)((var19 & 15) << 4 | var20 >> 2 & 15);
                                    return tmp;
                                }
                            } else {
                                return null;
                            }
                        }
                    } else {
                        return null;
                    }
                }
            }
        }
    }

    public static String encode(byte[] binaryData) {
        if(binaryData == null) {
            return null;
        } else {
            int lengthDataBits = binaryData.length * 8;
            if(lengthDataBits == 0) {
                return "";
            } else {
                int fewerThan24bits = lengthDataBits % 24;
                int numberTriplets = lengthDataBits / 24;
                int numberQuartet = fewerThan24bits != 0?numberTriplets + 1:numberTriplets;
                int numberLines = (numberQuartet - 1) / 19 + 1;
                Object encodedData = null;
                char[] var20 = new char[numberQuartet * 4 + numberLines];
                boolean k = false;
                boolean l = false;
                boolean b1 = false;
                boolean b2 = false;
                boolean b3 = false;
                int encodedIndex = 0;
                int dataIndex = 0;
                int i = 0;

                byte val3;
                byte var21;
                byte var22;
                byte var23;
                byte var24;
                byte var25;
                for(int val1 = 0; val1 < numberLines - 1; ++val1) {
                    for(int val2 = 0; val2 < 19; ++val2) {
                        var23 = binaryData[dataIndex++];
                        var24 = binaryData[dataIndex++];
                        var25 = binaryData[dataIndex++];
                        var22 = (byte)(var24 & 15);
                        var21 = (byte)(var23 & 3);
                        val3 = (var23 & -128) == 0?(byte)(var23 >> 2):(byte)(var23 >> 2 ^ 192);
                        byte val21 = (var24 & -128) == 0?(byte)(var24 >> 4):(byte)(var24 >> 4 ^ 240);
                        byte val31 = (var25 & -128) == 0?(byte)(var25 >> 6):(byte)(var25 >> 6 ^ 252);
                        var20[encodedIndex++] = lookUpBase64Alphabet[val3];
                        var20[encodedIndex++] = lookUpBase64Alphabet[val21 | var21 << 4];
                        var20[encodedIndex++] = lookUpBase64Alphabet[var22 << 2 | val31];
                        var20[encodedIndex++] = lookUpBase64Alphabet[var25 & 63];
                        ++i;
                    }

                    var20[encodedIndex++] = 10;
                }

                byte var26;
                byte var27;
                while(i < numberTriplets) {
                    var23 = binaryData[dataIndex++];
                    var24 = binaryData[dataIndex++];
                    var25 = binaryData[dataIndex++];
                    var22 = (byte)(var24 & 15);
                    var21 = (byte)(var23 & 3);
                    var26 = (var23 & -128) == 0?(byte)(var23 >> 2):(byte)(var23 >> 2 ^ 192);
                    var27 = (var24 & -128) == 0?(byte)(var24 >> 4):(byte)(var24 >> 4 ^ 240);
                    val3 = (var25 & -128) == 0?(byte)(var25 >> 6):(byte)(var25 >> 6 ^ 252);
                    var20[encodedIndex++] = lookUpBase64Alphabet[var26];
                    var20[encodedIndex++] = lookUpBase64Alphabet[var27 | var21 << 4];
                    var20[encodedIndex++] = lookUpBase64Alphabet[var22 << 2 | val3];
                    var20[encodedIndex++] = lookUpBase64Alphabet[var25 & 63];
                    ++i;
                }

                if(fewerThan24bits == 8) {
                    var23 = binaryData[dataIndex];
                    var21 = (byte)(var23 & 3);
                    var26 = (var23 & -128) == 0?(byte)(var23 >> 2):(byte)(var23 >> 2 ^ 192);
                    var20[encodedIndex++] = lookUpBase64Alphabet[var26];
                    var20[encodedIndex++] = lookUpBase64Alphabet[var21 << 4];
                    var20[encodedIndex++] = 61;
                    var20[encodedIndex++] = 61;
                } else if(fewerThan24bits == 16) {
                    var23 = binaryData[dataIndex];
                    var24 = binaryData[dataIndex + 1];
                    var22 = (byte)(var24 & 15);
                    var21 = (byte)(var23 & 3);
                    var26 = (var23 & -128) == 0?(byte)(var23 >> 2):(byte)(var23 >> 2 ^ 192);
                    var27 = (var24 & -128) == 0?(byte)(var24 >> 4):(byte)(var24 >> 4 ^ 240);
                    var20[encodedIndex++] = lookUpBase64Alphabet[var26];
                    var20[encodedIndex++] = lookUpBase64Alphabet[var27 | var21 << 4];
                    var20[encodedIndex++] = lookUpBase64Alphabet[var22 << 2];
                    var20[encodedIndex++] = 61;
                }

                var20[encodedIndex] = 10;
                return new String(var20);
            }
        }
    }

    public static String getBASE64(byte[] bytes) {
        byte[] s = null;
        if(bytes != null) {
            s = Base64.encodeBase64(bytes);
        }

        return new String(s);
    }

    public static byte[] getFromBASE64(String str) {
        Object b = null;
        if(str != null) {
            try {
                byte[] b1 = Base64.encodeBase64(str.getBytes());
                return b1;
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }

        return (byte[])b;
    }

    protected static boolean isBase64(char octect) {
        return isWhiteSpace(octect) || isPad(octect) || isData(octect);
    }

    protected static boolean isData(char octect) {
        return base64Alphabet[octect] != -1;
    }

    protected static boolean isPad(char octect) {
        return octect == 61;
    }

    protected static boolean isWhiteSpace(char octect) {
        return octect == 32 || octect == 13 || octect == 10 || octect == 9;
    }

    public static void main(String[] args) {
        String str = "asfd";
        System.out.println(getBASE64(str.getBytes()));
    }

    protected static int removeWhiteSpace(char[] data) {
        if(data == null) {
            return 0;
        } else {
            int newSize = 0;
            int len = data.length;

            for(int i = 0; i < len; ++i) {
                if(!isWhiteSpace(data[i])) {
                    data[newSize++] = data[i];
                }
            }

            return newSize;
        }
    }

}
