package com.jagregory.shiro.freemarker;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

/**
 * Created by shinyleo on 17/7/20.
 */
public abstract class RoleTag  extends SecureTag {

    public RoleTag() {
    }

    String getName(Map params) {
        return this.getParam(params, "name");
    }

    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        boolean show = this.showTagBody(this.getName(params));
        if(show) {
            this.renderBody(env, body);
        }

    }

    protected abstract boolean showTagBody(String var1);
}
