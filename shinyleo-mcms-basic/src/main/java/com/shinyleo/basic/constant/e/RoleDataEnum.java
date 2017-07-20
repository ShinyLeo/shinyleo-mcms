package com.shinyleo.basic.constant.e;

import com.shinyleo.base.constant.e.BaseEnum;

/**
 * Created by shinyleo on 17/7/20.
 */
public enum RoleDataEnum implements BaseEnum {

    ALL(1,"所有数据"),
    SELF(1,"仅本人数据");


    /**
     * 设置CookieConst的常量
     * @param attr 常量
     */
    RoleDataEnum(int id,String code) {
        this.id = id;
        this.code = code;
    }

    private int id;
    private String code;


    /**
     * 返回该CookieConst常量的字符串表示
     * @return 字符串
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return code;
    }


    @Override
    public int toInt() {
        return id;
    }
}
