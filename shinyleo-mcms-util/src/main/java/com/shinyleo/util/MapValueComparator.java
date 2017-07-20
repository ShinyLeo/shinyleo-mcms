package com.shinyleo.util;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by zhugh on 17/7/20.
 */
public class MapValueComparator implements Comparator<Map.Entry<String, String>> {

    MapValueComparator() {
    }

    public int compare(Map.Entry<String, String> str1, Map.Entry<String, String> str2) {
        return ((String)str1.getValue()).compareTo((String)str2.getValue());
    }

}
