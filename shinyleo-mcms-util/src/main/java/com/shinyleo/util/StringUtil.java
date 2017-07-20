package com.shinyleo.util;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhugh on 17/7/20.
 */
public class StringUtil {

    private static StringBuilder sb = new StringBuilder();

    public StringUtil() {
    }

    public static boolean checkEmail(String email) {
        String regex = "^[a-zA-Z][a-zA-Z0-9._-]*\\@\\w+(\\.)*\\w+\\.\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher matcher = p.matcher(email);
        return matcher.matches();
    }

    public static String formatHTMLIn(String html) {
        html = html.replaceAll("&", "&amp;");
        html = html.replaceAll("<", "&lt;");
        html = html.replaceAll(">", "&gt;");
        html = html.replaceAll("\"", "&quot;");
        return html;
    }

    public static String formatHTMLOut(String html) {
        html = html.replaceAll("&amp;", "&");
        html = html.replaceAll("&lt;", "<");
        html = html.replaceAll("&gt;", ">");
        html = html.replaceAll("&quot;", "\"");
        return html;
    }

    public static String subString(String str, int length) {
        if(isBlank(str)) {
            return "";
        } else if(str.getBytes().length <= length) {
            return str;
        } else {
            Object ch = null;
            char[] var7;
            if(str.length() >= length) {
                var7 = str.substring(0, length).toCharArray();
            } else {
                var7 = str.toCharArray();
            }

            int readLen = 0;
            StringBuffer sb = new StringBuffer("");

            for(int i = 0; i < var7.length; ++i) {
                String c = String.valueOf(var7[i]);
                readLen += c.getBytes().length;
                if(readLen > length) {
                    return sb.toString();
                }

                sb.append(c);
            }

            return sb.toString();
        }
    }

    public static boolean checkLength(String str, int minLength, int maxLength) {
        if(str != null) {
            int len = str.length();
            return minLength == 0?len <= maxLength:(maxLength == 0?len >= minLength:len >= minLength && len <= maxLength);
        } else {
            return false;
        }
    }

    public static String decodeStringByUTF8(String str) {
        if(isBlank(str)) {
            return "";
        } else {
            try {
                return URLDecoder.decode(str, "utf-8");
            } catch (UnsupportedEncodingException var1) {
                return "";
            }
        }
    }

    public static String encodeStringByUTF8(String str) {
        if(isBlank(str)) {
            return "";
        } else {
            try {
                return URLEncoder.encode(str, "utf-8");
            } catch (UnsupportedEncodingException var1) {
                return "";
            }
        }
    }

    public static String isoToUTF8(String str) {
        if(isBlank(str)) {
            return "";
        } else {
            try {
                return new String(str.getBytes("ISO-8859-1"), "UTF-8");
            } catch (UnsupportedEncodingException var1) {
                return "";
            }
        }
    }

    public static String utf8ToISO(String str) {
        if(isBlank(str)) {
            return "";
        } else {
            try {
                return new String(str.getBytes("UTF-8"), "ISO-8859-1");
            } catch (UnsupportedEncodingException var1) {
                return "";
            }
        }
    }

    public static String utf8Togb2312(String str) {
        StringBuffer sb = new StringBuffer();

        for(int result = 0; result < str.length(); ++result) {
            char res = str.charAt(result);
            switch(res) {
                case '%':
                    try {
                        sb.append((char)Integer.parseInt(str.substring(result + 1, result + 3), 16));
                    } catch (NumberFormatException var6) {
                        throw new IllegalArgumentException();
                    }

                    result += 2;
                    break;
                case '+':
                    sb.append(' ');
                    break;
                default:
                    sb.append(res);
            }
        }

        String var7 = sb.toString();
        String var8 = null;

        try {
            byte[] inputBytes = var7.getBytes("8859_1");
            var8 = new String(inputBytes, "UTF-8");
        } catch (Exception var5) {
            ;
        }

        return var8;
    }

    public static String getFormatDateStr(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().equals("") || str.length() < 0;
    }

    public static boolean isBlank(Object str) {
        return str == null || str.toString().trim().equals("") || str.toString().length() < 0;
    }

    public static boolean isBlank(String[] args) {
        return args == null || args.length == 0;
    }

    public static boolean isInteger(String str) {
        if(isBlank(str)) {
            return false;
        } else {
            try {
                Integer.parseInt(str);
                return true;
            } catch (Exception var1) {
                return false;
            }
        }
    }

    public static boolean isInteger(Object str) {
        String temp = "" + str;
        if(isBlank(str)) {
            return false;
        } else {
            try {
                Integer.parseInt(temp);
                return true;
            } catch (Exception var2) {
                return false;
            }
        }
    }

    public static String null2String(String str) {
        if(str != null && !str.equals("") && str.trim().length() != 0) {
            return str;
        } else {
            str = "";
            return "";
        }
    }

    public static int string2Int(String str) {
        int valueInt = 0;
        if(!isBlank(str)) {
            valueInt = Integer.parseInt(str);
        }

        return valueInt;
    }

    public static String int2String(int comment) {
        String srt = "";
        srt = Integer.toString(comment);
        return srt;
    }

    public static boolean isMaxZeroInteger(Object str) {
        if(isBlank(str)) {
            return false;
        } else {
            try {
                int temp = Integer.parseInt(str.toString());
                return temp > 0;
            } catch (Exception var2) {
                return false;
            }
        }
    }

    public static boolean isLong(String str) {
        if(isBlank(str)) {
            return false;
        } else {
            try {
                Long.parseLong(str);
                return true;
            } catch (Exception var1) {
                return false;
            }
        }
    }

    public static boolean isLongs(String[] str) {
        try {
            for(int i = 0; i < str.length; ++i) {
                Long.parseLong(str[i]);
            }

            return true;
        } catch (Exception var2) {
            return false;
        }
    }

    public static boolean isIntegers(String[] str) {
        try {
            for(int i = 0; i < str.length; ++i) {
                Integer.parseInt(str[i]);
            }

            return true;
        } catch (Exception var2) {
            return false;
        }
    }

    public static boolean isDoubles(String[] str) {
        try {
            for(int i = 0; i < str.length; ++i) {
                Double.parseDouble(str[i]);
            }

            return true;
        } catch (Exception var2) {
            return false;
        }
    }

    /** @deprecated */
    @Deprecated
    public static String Md5(String plainText) {
        StringBuffer buf = new StringBuffer("");

        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            e.update(plainText.getBytes());
            byte[] b = e.digest();
            boolean i = false;

            for(int offset = 0; offset < b.length; ++offset) {
                int var7 = b[offset];
                if(var7 < 0) {
                    var7 += 256;
                }

                if(var7 < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(var7));
            }
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return buf.toString();
    }

    public static String Md5(String plainText, String coding) {
        StringBuffer buf = new StringBuffer("");

        try {
            MessageDigest e = MessageDigest.getInstance("MD5");
            e.update(plainText.getBytes(coding));
            byte[] b = e.digest();
            boolean i = false;

            for(int offset = 0; offset < b.length; ++offset) {
                int var8 = b[offset];
                if(var8 < 0) {
                    var8 += 256;
                }

                if(var8 < 16) {
                    buf.append("0");
                }

                buf.append(Integer.toHexString(var8));
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return buf.toString();
    }

    public static long[] stringsToLongs(String[] str) {
        long[] lon = new long[str.length];

        for(int i = 0; i < lon.length; ++i) {
            lon[i] = Long.parseLong(str[i]);
        }

        return lon;
    }

    public static Integer[] stringsToIntegers(String[] str) {
        Integer[] array = new Integer[str.length];

        for(int i = 0; i < array.length; ++i) {
            array[i] = Integer.valueOf(Integer.parseInt(str[i]));
        }

        return array;
    }

    public static int[] stringsToInts(String[] str) {
        int[] array = new int[str.length];

        for(int i = 0; i < array.length; ++i) {
            array[i] = Integer.parseInt(str[i]);
        }

        return array;
    }

    public static double[] stringsToDoubles(String[] str) {
        double[] array = new double[str.length];

        for(int i = 0; i < array.length; ++i) {
            array[i] = Double.parseDouble(str[i]);
        }

        return array;
    }

    public static String[] delLopStrings(String[] str) {
        ArrayList list = new ArrayList();

        for(int array = 0; array < str.length; ++array) {
            if(!list.contains(str[array])) {
                list.add(str[array]);
            }
        }

        String[] var4 = new String[list.size()];

        for(int i = 0; i < list.size(); ++i) {
            var4[i] = (String)list.get(i);
        }

        return var4;
    }

    public static boolean[] stringsToBooleans(String[] str) {
        boolean[] array = new boolean[str.length];

        for(int i = 0; i < array.length; ++i) {
            array[i] = Boolean.parseBoolean(str[i]);
        }

        return array;
    }

    public static boolean isTimestamp(String str) {
        try {
            java.sql.Date.valueOf(str);
            return true;
        } catch (Exception var1) {
            return false;
        }
    }

    public static int getPageStart(String pageNo) {
        int istart = 1;
        if(isBlank(pageNo)) {
            return istart;
        } else {
            try {
                istart = Integer.parseInt(pageNo) < 0?istart:Integer.parseInt(pageNo);
            } catch (NumberFormatException var2) {
                ;
            }

            return istart;
        }
    }

    public static String getDateSimpleStr() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static Long[] StrToLong(String[] args) {
        if(args == null) {
            return null;
        } else {
            Long[] _ref = new Long[args.length];

            for(int i = 0; i < args.length; ++i) {
                _ref[i] = new Long(args[i]);
            }

            return _ref;
        }
    }

    public static Integer[] StrToInteger(String[] args) {
        if(args == null) {
            return null;
        } else {
            Integer[] _ref = new Integer[args.length];

            for(int i = 0; i < args.length; ++i) {
                _ref[i] = new Integer(args[i]);
            }

            return _ref;
        }
    }

    public static String getSimpleDateStr(Date day, String fomStr) {
        SimpleDateFormat format = new SimpleDateFormat(fomStr);
        return format.format(day);
    }

    public static Date getDateForStr(String str) {
        java.sql.Date sqlDate = java.sql.Date.valueOf(str);
        return sqlDate;
    }

    public static Date addDays(Date time, int day) {
        if(time == null) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(time);
            c.set(5, c.get(5) + day);
            return c.getTime();
        }
    }

    public static Date addMonths(Date time, int month) {
        if(time == null) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(time);
            c.set(2, c.get(2) + month);
            return c.getTime();
        }
    }

    public static String getIpStringFromBytes(byte[] ip) {
        sb.delete(0, sb.length());
        sb.append(ip[0] & 255);
        sb.append('.');
        sb.append(ip[1] & 255);
        sb.append('.');
        sb.append(ip[2] & 255);
        sb.append('.');
        sb.append(ip[3] & 255);
        return sb.toString();
    }

    public static boolean isIpEquals(byte[] ip1, byte[] ip2) {
        return ip1[0] == ip2[0] && ip1[1] == ip2[1] && ip1[2] == ip2[2] && ip1[3] == ip2[3];
    }

    public static String getString(byte[] b, int offset, int len, String encoding) {
        try {
            return new String(b, offset, len, encoding);
        } catch (UnsupportedEncodingException var4) {
            return new String(b, offset, len);
        }
    }

    public static String stringToBinary(byte[] src) {
        StringBuffer sb = new StringBuffer();
        byte[][] des = new byte[src.length][16];

        int i;
        int j;
        for(i = 0; i < src.length; ++i) {
            for(j = 0; j < 16; ++j) {
                des[i][j] = (byte)(src[i] >> j & 1);
            }
        }

        for(i = 0; i < src.length; ++i) {
            for(j = 0; j < 16; ++j) {
                sb.append(des[i][j]);
            }
        }

        return sb.toString();
    }

    public static String randomNumber(int len) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();

        for(int i = 0; i < len; ++i) {
            sb.append(Math.abs(random.nextInt()) % 10);
        }

        return sb.toString();
    }

    public static String timeForString() {
        Long l = Long.valueOf(System.currentTimeMillis());
        return String.valueOf(Math.abs(l.intValue()));
    }

    public static String getParString(String str) {
        return isBlank(str)?"":str;
    }

    public static boolean isChinese(char chChar) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(chChar);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    public static boolean isMobile(String phoneNumber) {
        phoneNumber = phoneNumber.trim();
        String pattern = "^[1][1-8][0-9]{9}";
        return phoneNumber.matches(pattern);
    }

    public static String formatResource(Object[] info, String require) {
        require = require.replaceAll("\'", "\"");
        String result = MessageFormat.format(require, info);
        return result.replaceAll("\"", "\'");
    }

    public static int getDaysBetween(Calendar beginDate, Calendar endDate) {
        if(beginDate.after(endDate)) {
            Calendar days = beginDate;
            beginDate = endDate;
            endDate = days;
        }

        int days1 = endDate.get(6) - beginDate.get(6);
        int y2 = endDate.get(1);
        if(beginDate.get(1) != y2) {
            beginDate = (Calendar)beginDate.clone();

            do {
                days1 += beginDate.getActualMaximum(6);
                beginDate.add(1, 1);
            } while(beginDate.get(1) != y2);
        }

        return days1;
    }

    public static String getFileFix(String filePath) {
        String temp = "";
        if(filePath != null) {
            temp = filePath.substring(filePath.indexOf("."), filePath.length());
        }

        return temp;
    }

    public static String convertStreamToString(InputStream dataFlow) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(dataFlow));
        StringBuilder sb = new StringBuilder();
        String line = null;

        try {
            while((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException var13) {
            var13.printStackTrace();
        } finally {
            try {
                dataFlow.close();
            } catch (IOException var12) {
                var12.printStackTrace();
            }

        }

        return sb.toString();
    }

    public static String checkStr(String str) {
        String s = null;
        char[] cc = str.toCharArray();

        for(int i = 0; i < cc.length; ++i) {
            boolean b = isValidChar(cc[i]);
            if(!b) {
                cc[i] = 32;
            }
        }

        s = String.valueOf(cc);
        return s;
    }

    private static boolean isValidChar(char ch) {
        return (ch < 48 || ch > 57) && (ch < 65 || ch > 90) && (ch < 97 || ch > 122)?ch >= 19968 && ch <= 32767 || ch >= '耀' && ch <= '锯':true;
    }

    public static String removeRepeatStr(String content, String target) {
        StringBuffer sb = new StringBuffer(content);

        for(int i = 0; i < sb.length() - 1; ++i) {
            if(sb.substring(i, i + target.length()).equals(target) && sb.substring(i, i + target.length()).equals(sb.substring(i + 1, i + target.length() + 1))) {
                sb.delete(i, i + target.length());
                if(i + target.length() + 1 > sb.length()) {
                    break;
                }

                --i;
            }
        }

        return sb.toString();
    }

    public static Boolean isEmail(String email) {
        boolean tag = true;
        Pattern pattern = Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
        Matcher mat = pattern.matcher(email);
        if(!mat.find()) {
            tag = false;
        }

        return Boolean.valueOf(tag);
    }

    public static String buildUrl(String url, String parm) {
        return url.indexOf("?") > 0?url + "&" + parm:url + "?" + parm;
    }

    public static String buildPath(Object... params) {
        String temp = "";
        Object[] var5 = params;
        int var4 = params.length;

        for(int var3 = 0; var3 < var4; ++var3) {
            Object o = var5[var3];
            temp = temp + File.separator + o;
        }

        return temp;
    }

    public static String buildUrl(String url, Map parms) {
        Iterator key = parms.keySet().iterator();
        String paramsStr = "";

        while(key.hasNext()) {
            Object temp = key.next();
            if(!isBlank(parms.get(temp))) {
                if(paramsStr != "") {
                    paramsStr = paramsStr + "&";
                }

                paramsStr = paramsStr + temp + "=" + parms.get(temp);
            }
        }

        if(paramsStr != "") {
            if(url.indexOf("?") > 0) {
                return url + "&" + paramsStr;
            } else {
                return url + "?" + paramsStr;
            }
        } else {
            return url;
        }
    }

    public static String javaProperty2DatabaseCloumn(String property) {
        String[] ss = property.split("(?<!^)(?=[A-Z])");
        StringBuffer sb = new StringBuffer();

        for(int i = 0; i < ss.length; ++i) {
            sb.append(ss[i]);
            if(i < ss.length - 1) {
                sb.append("_");
            }
        }

        if(!isBlank((Object)sb)) {
            return sb.toString().toUpperCase();
        } else {
            return null;
        }
    }

    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if(map != null && !map.isEmpty()) {
            TreeMap sortMap = new TreeMap(new MapKeyComparator());
            sortMap.putAll(map);
            return sortMap;
        } else {
            return null;
        }
    }

    public static Map<String, String> sortMapByValue(Map<String, String> map) {
        if(map != null && !map.isEmpty()) {
            LinkedHashMap sortedMap = new LinkedHashMap();
            ArrayList entryList = new ArrayList(map.entrySet());
            Collections.sort(entryList, new MapValueComparator());
            Iterator iter = entryList.iterator();
            Map.Entry tmpEntry = null;

            while(iter.hasNext()) {
                tmpEntry = (Map.Entry)iter.next();
                sortedMap.put((String)tmpEntry.getKey(), (String)tmpEntry.getValue());
            }

            return sortedMap;
        } else {
            return null;
        }
    }

    public static boolean isExpressNo(String str) {
        return isBlank(str)?false:(str.length() == 13?true:(str.length() == 12?true:true));
    }
}
