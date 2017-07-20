package net.shinyleo.base.util;

import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by shinyleo on 17/7/20.
 */
public class JSONArray {

    public static final String toJSONString(Object object, SerializeFilter... filters) {
        return com.alibaba.fastjson.JSONArray.toJSONString(object, filters, SerializerFeature.WriteMapNullValue);
    }
}
