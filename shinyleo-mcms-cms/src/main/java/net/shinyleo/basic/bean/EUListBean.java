package net.shinyleo.basic.bean;

import java.util.List;

/**
 * Created by shinyleo on 17/7/20.
 */
public class EUListBean {

    private int total;

    private List rows;


    public EUListBean(List rows, int total) {
        this.total = total;
        this.rows = rows;
    }


    public int getTotal() {
        return total;
    }


    public void setTotal(int total) {
        this.total = total;
    }


    public List getRows() {
        return rows;
    }


    public void setRows(List rows) {
        this.rows = rows;
    }
}
