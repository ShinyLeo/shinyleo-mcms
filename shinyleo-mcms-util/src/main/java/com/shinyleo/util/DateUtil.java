package com.shinyleo.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by shinyleo on 17/7/20.
 */
public class DateUtil {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    private static final int[] dayArray = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    public static final SimpleDateFormat DATE_TIME_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    public static final SimpleDateFormat DATE_TIME_EXTENDED_FORMAT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    public static final SimpleDateFormat ORA_DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");
    public static final SimpleDateFormat ORA_DATE_TIME_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");
    public static final SimpleDateFormat ORA_DATE_TIME_EXTENDED_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final SimpleDateFormat CHN_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat CHN_DATE_TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public static final SimpleDateFormat CHN_DATE_TIME_EXTENDED_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final String dtLong = "yyyyMMddHHmmss";
    public static final String simple = "yyyy-MM-dd HH:mm:ss";
    public static final String dtShort = "yyyyMMdd";

    public static String getOrderNum() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(date);
    }

    public DateUtil() {
        this.today();
    }

    DateUtil(String inValue) {
        this.SetDate(inValue);
    }

    public DateUtil(long mills) {
        this.setTimeInMillis(mills);
    }

    public DateUtil(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, hour, minute, second);
        this.year = calendar.get(1);
        this.month = calendar.get(2) + 1;
        this.day = calendar.get(5);
        this.hour = calendar.get(11);
        this.minute = calendar.get(12);
        this.second = calendar.get(13);
    }

    private void SetDate(String inValue) {
        int e;
        if(inValue.length() != 14) {
            for(e = inValue.length(); e < 14; ++e) {
                inValue = inValue + "0";
            }

            System.out.println(inValue);
        }

        try {
            e = Integer.parseInt(inValue.substring(0, 4));
            int month = Integer.parseInt(inValue.substring(4, 6));
            int day = Integer.parseInt(inValue.substring(6, 8));
            int hour = Integer.parseInt(inValue.substring(8, 10));
            int minute = Integer.parseInt(inValue.substring(10, 12));
            int second = Integer.parseInt(inValue.substring(12));
            Calendar calendar = Calendar.getInstance();
            calendar.set(e, month - 1, day, hour, minute, second);
            this.year = calendar.get(1);
            this.month = calendar.get(2) + 1;
            this.day = calendar.get(5);
            this.hour = calendar.get(11);
            this.minute = calendar.get(12);
            this.second = calendar.get(13);
        } catch (Exception var9) {
            System.out.println(var9.getMessage());
        }

    }

    private void today() {
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(1);
        this.month = calendar.get(2) + 1;
        this.day = calendar.get(5);
        this.hour = calendar.get(11);
        this.minute = calendar.get(12);
        this.second = calendar.get(13);
    }

    public String format(SimpleDateFormat DateFormat) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.year, this.month - 1, this.day, this.hour, this.minute, this.second);
        return DateFormat.format(calendar.getTime());
    }

    public String toString() {
        return this.format(CHN_DATE_TIME_EXTENDED_FORMAT);
    }

    public Date getDate() {
        Calendar date = Calendar.getInstance();
        date.set(5, this.getDay());
        date.set(2, this.getMonth() - 1);
        date.set(1, this.getYear());
        date.set(11, this.getHour());
        date.set(12, this.getMinute());
        date.set(13, this.getSecond());
        return date.getTime();
    }

    public long getTimeInMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.year, this.month - 1, this.day, this.hour, this.minute, this.second);
        return calendar.getTime().getTime();
    }

    public void setTimeInMillis(long mills) {
        Date dd = new Date(mills);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dd);
        this.year = calendar.get(1);
        this.month = calendar.get(2) + 1;
        this.day = calendar.get(5);
        this.hour = calendar.get(11);
        this.minute = calendar.get(12);
        this.second = calendar.get(13);
    }

    public boolean isLeapYear() {
        return this.isLeapYear(this.year);
    }

    public boolean isLeapYear(int year) {
        return year % 400 == 0?true:(year % 4 == 0?year % 100 != 0:false);
    }

    public void setDateTime(int years, int months, int days, int hours, int minutes, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.year + years, this.month - 1 + months, this.day + days, this.hour + hours, this.minute + minutes, this.second + seconds);
        this.setTimeInMillis(calendar.getTime().getTime());
    }

    public void addYear(int years) {
        if(this.month == 2 && this.day == 29) {
            if(this.isLeapYear(this.year + years)) {
                this.setDateTime(years, 0, 0, 0, 0, 0);
            } else {
                this.setDateTime(years, 0, -1, 0, 0, 0);
            }
        } else {
            this.setDateTime(years, 0, 0, 0, 0, 0);
        }

    }

    public void addMonth(int months) {
        int this_day_end = this.daysOfMonth();
        int that_day_end = this.dayOfMonth(months);
        if(this.day == this_day_end) {
            this.day = that_day_end;
        } else if(this.day > that_day_end) {
            this.day = that_day_end;
        }

        this.setDateTime(0, months, 0, 0, 0, 0);
    }

    public void addDay(int days) {
        this.setDateTime(0, 0, days, 0, 0, 0);
    }

    public void addHour(int hours) {
        this.setDateTime(0, 0, 0, hours, 0, 0);
    }

    public void addMinute(int minutes) {
        this.setDateTime(0, 0, 0, 0, minutes, 0);
    }

    public void addSecond(int seconds) {
        this.setDateTime(0, 0, 0, 0, 0, seconds);
    }

    public int daysOfMonth() {
        return this.month <= 12 && this.month >= 0?(this.month == 2 && this.isLeapYear()?29:dayArray[this.month - 1]):0;
    }

    public int dayOfMonth(int monthNumber) {
        int yy = monthNumber / 12;
        int mm = monthNumber % 12;
        int year = this.year + yy;
        int month = this.month + mm;
        if(month > 12) {
            month -= 12;
            ++year;
        }

        if(month < 1) {
            month += 12;
            --year;
        }

        return month == 2 && this.isLeapYear(year)?29:dayArray[month - 1];
    }

    public static long diffSec(DateUtil firstDate, DateUtil Lastdate) {
        return (firstDate.getTimeInMillis() - Lastdate.getTimeInMillis()) / 1000L;
    }

    public static int diffMonth(Date firstDate, Date Lastdate) {
        if(firstDate.after(Lastdate)) {
            Date startCalendar = firstDate;
            firstDate = Lastdate;
            Lastdate = startCalendar;
        }

        Calendar startCalendar1 = Calendar.getInstance();
        startCalendar1.setTime(firstDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(Lastdate);
        Calendar temp = Calendar.getInstance();
        temp.setTime(Lastdate);
        temp.add(5, 1);
        int year = endCalendar.get(1) - startCalendar1.get(1);
        int month = endCalendar.get(2) - startCalendar1.get(2);
        return startCalendar1.get(5) == 1 && temp.get(5) == 1?year * 12 + month + 1:(startCalendar1.get(5) != 1 && temp.get(5) == 1?year * 12 + month:(startCalendar1.get(5) == 1 && temp.get(5) != 1?year * 12 + month:(year * 12 + month - 1 < 0?0:year * 12 + month)));
    }

    public static int diffDay(DateUtil firstDate, DateUtil Lastdate) {
        return (int)(firstDate.getTimeInMillis() - Lastdate.getTimeInMillis()) / 1000 / 86400;
    }

    public static int diffDay(Date firstDate, Date Lastdate) {
        firstDate = stringToDate(dateFmtToString(firstDate, "yyyy-MM-dd"), "yyyy-MM-dd");
        Lastdate = stringToDate(dateFmtToString(Lastdate, "yyyy-MM-dd"), "yyyy-MM-dd");
        int _day = (int)((firstDate.getTime() - Lastdate.getTime()) / 1000L / 86400L);
        return _day;
    }

    public static int diffDays(Calendar firstDate, Calendar Lastdate) {
        if(firstDate.after(Lastdate)) {
            Calendar days = firstDate;
            firstDate = Lastdate;
            Lastdate = days;
        }

        int days1 = Lastdate.get(6) - firstDate.get(6);
        int y2 = Lastdate.get(1);
        if(firstDate.get(1) != y2) {
            firstDate = (Calendar)firstDate.clone();

            do {
                days1 += firstDate.getActualMaximum(6);
                firstDate.add(1, 1);
            } while(firstDate.get(1) != y2);
        }

        return days1;
    }

    public static Date addDays(Date date, int day) {
        if(date == null) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.set(5, c.get(5) + day);
            return c.getTime();
        }
    }

    public static Date removeDays(Date date, int day) {
        if(date == null) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.set(5, c.get(5) - day);
            return c.getTime();
        }
    }

    public static Date addMonths(Date date, int month) {
        if(date == null) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.set(2, c.get(2) + month);
            return c.getTime();
        }
    }

    public static Date removeMonths(Date date, int month) {
        if(date == null) {
            return null;
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.set(2, c.get(2) - month);
            return c.getTime();
        }
    }

    public static String dateFmtToString(Date date, SimpleDateFormat fmt) {
        return fmt.format(date);
    }

    public static String dateFmtToString(Date date) {
        return (new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(date);
    }

    public static String dateFmtToString(Date date, String fmt) {
        return (new SimpleDateFormat(fmt)).format(date);
    }

    public static Date stringToDate(String date) {
        return java.sql.Date.valueOf(date);
    }

    public static Date stringToDate(String date, String ftm) {
        SimpleDateFormat sdf = new SimpleDateFormat(ftm);

        try {
            return sdf.parse(date);
        } catch (ParseException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static Date stringFmtToDate(String date, String dataFmt) {
        SimpleDateFormat df = new SimpleDateFormat(dataFmt);
        return java.sql.Date.valueOf(df.format(java.sql.Date.valueOf(date)));
    }

    public static Timestamp dateToTimestamp(Date date) {
        String temp = CHN_DATE_TIME_EXTENDED_FORMAT.format(date);
        return Timestamp.valueOf(temp);
    }

    public int getDay() {
        return this.day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return this.hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return this.minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getSecond() {
        return this.second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean hasCommon(Date start1, Date end1, Date start2, Date end2) {
        return !end1.before(start2) && !end2.before(start1);
    }

    public static boolean judgeDateMsg(String date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        try {
            java.sql.Date.valueOf(df.format(java.sql.Date.valueOf(date)));
            return true;
        } catch (NumberFormatException var2) {
            return false;
        }
    }

    public static int daysBetween(String beginDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        try {
            cal.setTime(sdf.parse(beginDate));
            cal2.setTime(sdf.parse(endDate));
            long e = cal.getTimeInMillis();
            long time2 = cal2.getTimeInMillis();
            long between_days = (time2 - e) / 86400000L;
            return (int)between_days;
        } catch (ParseException var11) {
            var11.printStackTrace();
            return 1;
        }
    }

    public static int secondBetween(String date) {
        SimpleDateFormat sdfSecond = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            long e = sdfSecond.parse(sdfSecond.format(new Date())).getTime();
            long enterDate = sdfSecond.parse(date).getTime();
            return (int)((e - enterDate) / 1000L);
        } catch (ParseException var6) {
            var6.printStackTrace();
            return 1;
        }
    }

    public static int secondBetween(Date date) {
        if(date == null) {
            return 0;
        } else {
            SimpleDateFormat sdfSecond = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            try {
                long e = sdfSecond.parse(sdfSecond.format(new Date())).getTime();
                long enterDate = date.getTime();
                return (int)((e - enterDate) / 1000L);
            } catch (ParseException var6) {
                var6.printStackTrace();
                return 1;
            }
        }
    }

    public static String pastTime(Date date) {
        int second = secondBetween(date);
        return second < 60?second + "秒前":(second > 60 && second < 1800?second / 60 + "分钟前":(second > 1800 && second < 3600?"半小时前":(second > 3600 && second < 86400?second / 60 / 60 + "小时前":dateFmtToString(date, "yyyy-MM-dd HH:mm:ss"))));
    }

    public static int secondBetween(String beginDate, String endDate) {
        SimpleDateFormat sdfSecond = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            long e = sdfSecond.parse(beginDate).getTime();
            long _endDate = sdfSecond.parse(endDate).getTime();
            return (int)((e - _endDate) / 1000L);
        } catch (ParseException var7) {
            var7.printStackTrace();
            return 1;
        }
    }

    public static Date[] beginEndStringToDate(String date, String split, String fmt) {
        if(!StringUtil.isBlank(date) && !StringUtil.isBlank(split)) {
            String[] _date = date.split(split);
            if(_date.length == 2) {
                Date[] d = new Date[]{stringFmtToDate(_date[0], fmt), stringFmtToDate(_date[1], fmt)};
                return d;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public static Calendar date2Calendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
}
