package com.shinyleo.base.entity;

import java.util.List;

/**
 * Created by zhugh on 17/7/20.
 */
public class ListJson {


    private int count;
    private List list;

    /**
     * 构造方法，添加条数和数组
     * @param count
     * @param list
     */
    public ListJson(int count, List list) {
        this.count = count;
        this.list = list;
    }

    /**
     * 获取数量
     * @return 返回数量
     */
    public int getCount() {
        return count;
    }

    /**
     * 获取list数组
     * @return 返回list数组
     */
    public List getList() {
        return list;
    }
}
