package com.jagregory.shiro.freemarker;

import freemarker.core.Environment;
import freemarker.log.Logger;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.Map;

/**
 * Created by shinyleo on 17/7/20.
 */
public class PrincipalTag  extends SecureTag{

    static final Logger log = Logger.getLogger("PrincipalTag");

    public PrincipalTag() {
    }

    String getType(Map params) {
        return this.getParam(params, "type");
    }

    String getProperty(Map params) {
        return this.getParam(params, "property");
    }

    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        String result = null;
        if(this.getSubject() != null) {
            Object ex;
            if(this.getType(params) == null) {
                ex = this.getSubject().getPrincipal();
            } else {
                ex = this.getPrincipalFromClassName(params);
            }

            if(ex != null) {
                String property = this.getProperty(params);
                if(property == null) {
                    result = ex.toString();
                } else {
                    result = this.getPrincipalProperty(ex, property);
                }
            }
        }

        if(result != null) {
            try {
                env.getOut().write(result);
            } catch (IOException var7) {
                throw new TemplateException("Error writing [" + result + "] to Freemarker.", var7, env);
            }
        }

    }

    Object getPrincipalFromClassName(Map params) {
        String type = this.getType(params);

        try {
            Class ex = Class.forName(type);
            return this.getSubject().getPrincipals().oneByType(ex);
        } catch (ClassNotFoundException var4) {
            log.error("Unable to find class for name [" + type + "]", var4);
            return null;
        }
    }

    String getPrincipalProperty(Object principal, String property) throws TemplateModelException {
        try {
            BeanInfo ex = Introspector.getBeanInfo(principal.getClass());
            PropertyDescriptor[] var7;
            int var6 = (var7 = ex.getPropertyDescriptors()).length;

            for(int var5 = 0; var5 < var6; ++var5) {
                PropertyDescriptor propertyDescriptor = var7[var5];
                if(propertyDescriptor.getName().equals(property)) {
                    Object value = propertyDescriptor.getReadMethod().invoke(principal, (Object[])null);
                    return String.valueOf(value);
                }
            }

            throw new TemplateModelException("Property [" + property + "] not found in principal of type [" + principal.getClass().getName() + "]");
        } catch (Exception var9) {
            throw new TemplateModelException("Error reading property [" + property + "] from principal of type [" + principal.getClass().getName() + "]", var9);
        }
    }
}
