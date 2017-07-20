package com.shinyleo.base.constant.e;

/**
 * Created by zhugh on 17/7/20.
 */
public enum TableEnum implements BaseEnum{
    ALTER_ADD("add"), ALTER_MODIFY("modify"), ALTER_DROP("drop");
    private String obj;

    TableEnum(String obj) {
        this.obj = obj;
    }

    @Override
    public int toInt() {
        // TODO Auto-generated method stub
        return 0;
    }

    public String toString() {
        return this.obj.toString();
    }
}
