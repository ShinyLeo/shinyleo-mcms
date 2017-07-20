package com.shinyleo.base.filter;

import com.alibaba.fastjson.serializer.ValueFilter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by shinyleo on 17/7/20.
 */
public class DateValueFilter implements ValueFilter {

    private static String fmt="yyyy-MM-dd HH:mm:ss";

    public DateValueFilter(){}

    /**
     *  指定保留小数个数
     * @param fmt 日期格式，默认yyyy-MM-dd hh:mm:ss
     */
    public DateValueFilter(String fmt){
        this.fmt = fmt;
    }

    @Override
    public Object process(Object object, String name, Object value) {
        // TODO Auto-generated method stub
        if (value instanceof Date || value instanceof Timestamp) {
            SimpleDateFormat sdf = new SimpleDateFormat(this.fmt);
            return sdf.format(value);
        }
        return value;
    }
}
