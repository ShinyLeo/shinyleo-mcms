package com.shinyleo.parser.impl.general;

import com.shinyleo.parser.IParser;
import com.shinyleo.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shinyleo on 17/7/20.
 */
public class DateParser  extends IParser {

    private String dateReg = "\\[field.date\\s{0,}(fmt=(.*?))?/]";
    private Date date;

    public DateParser(String htmlContent, Date date) {
        super.htmlCotent = htmlContent;
        this.date = date;
    }

    public DateParser(String htmlContent, Date date, String dateReg) {
        super.htmlCotent = htmlContent;
        this.date = date;
        this.dateReg = dateReg;
    }

    public DateParser(String htmlContent, String newContent) {
        super.htmlCotent = htmlContent;
        super.newCotent = newContent;
    }

    public String parse() {
        Pattern pattern = Pattern.compile(this.dateReg);

        String date;
        for(Matcher matcher = pattern.matcher(this.htmlCotent); matcher.find(); this.htmlCotent = this.htmlCotent.replace(date, this.date(date))) {
            date = matcher.group();
        }

        return this.htmlCotent;
    }

    private String date(String reg) {
        String typeDate = "yyyy-MM-dd hh:mm:ss";
        String fmt = parseFirst(this.htmlCotent, this.dateReg, 2);
        if(!StringUtil.isBlank(fmt)) {
            typeDate = fmt;
        }

        String srtDate = "时间读取失败";
        if(this.date != null) {
            try {
                SimpleDateFormat forDate = new SimpleDateFormat(typeDate);
                srtDate = forDate.format(this.date);
            } catch (Exception var6) {
                ;
            }
        }

        return srtDate;
    }
}
