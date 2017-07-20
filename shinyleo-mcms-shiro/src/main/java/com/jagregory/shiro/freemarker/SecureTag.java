package com.jagregory.shiro.freemarker;

import freemarker.core.Environment;
import freemarker.template.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.io.IOException;
import java.util.Map;

/**
 * Created by shinyleo on 17/7/20.
 */
public abstract class SecureTag implements TemplateDirectiveModel {

    public SecureTag() {
    }

    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        this.verifyParameters(params);
        this.render(env, params, body);
    }

    public abstract void render(Environment var1, Map var2, TemplateDirectiveBody var3) throws IOException, TemplateException;

    protected String getParam(Map params, String name) {
        Object value = params.get(name);
        return value instanceof SimpleScalar ?((SimpleScalar)value).getAsString():null;
    }

    protected Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    protected void verifyParameters(Map params) throws TemplateModelException {
    }

    protected void renderBody(Environment env, TemplateDirectiveBody body) throws IOException, TemplateException {
        if(body != null) {
            body.render(env.getOut());
        }

    }
}
