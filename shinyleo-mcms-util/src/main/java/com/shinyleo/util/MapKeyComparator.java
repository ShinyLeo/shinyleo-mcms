package com.shinyleo.util;

import java.util.Comparator;

/**
 * Created by zhugh on 17/7/20.
 */
public class MapKeyComparator implements Comparator<String> {

    MapKeyComparator() {
    }

    public int compare(String str1, String str2) {
        return str1.compareTo(str2);
    }
}
