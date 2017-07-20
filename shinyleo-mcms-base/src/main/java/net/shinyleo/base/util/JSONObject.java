package net.shinyleo.base.util;

import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by shinyleo on 17/7/20.
 */
public class JSONObject extends com.alibaba.fastjson.JSONObject{

    public static final String toJSONString(Object object, SerializeFilter... filters) {
        return com.alibaba.fastjson.JSONObject.toJSONString(object, filters, SerializerFeature.WriteMapNullValue);
    }
}
