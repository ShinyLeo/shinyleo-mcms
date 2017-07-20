package com.jagregory.shiro.freemarker;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

import java.io.IOException;
import java.util.Map;

/**
 * Created by shinyleo on 17/7/20.
 */
public abstract class PermissionTag extends SecureTag{

    public PermissionTag() {
    }

    String getName(Map params) {
        return this.getParam(params, "name");
    }

    protected void verifyParameters(Map params) throws TemplateModelException {
        String permission = this.getName(params);
        if(permission == null || permission.length() == 0) {
            throw new TemplateModelException("The \'name\' tag attribute must be set.");
        }
    }

    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        String p = this.getName(params);
        boolean show = this.showTagBody(p);
        if(show) {
            this.renderBody(env, body);
        }

    }

    protected boolean isPermitted(String p) {
        return this.getSubject() != null && this.getSubject().isPermitted(p);
    }

    protected abstract boolean showTagBody(String var1);
}
