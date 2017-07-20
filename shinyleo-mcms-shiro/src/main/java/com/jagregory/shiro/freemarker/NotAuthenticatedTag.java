package com.jagregory.shiro.freemarker;

import freemarker.core.Environment;
import freemarker.log.Logger;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

/**
 * Created by shinyleo on 17/7/20.
 */
public class NotAuthenticatedTag  extends SecureTag{

    static final Logger log = Logger.getLogger("NotAuthenticatedTag");

    public NotAuthenticatedTag() {
    }

    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        if(this.getSubject() != null && this.getSubject().isAuthenticated()) {
            log.debug("Subject exists and is authenticated.  Tag body will not be evaluated.");
        } else {
            log.debug("Subject does not exist or is not authenticated.  Tag body will be evaluated.");
            this.renderBody(env, body);
        }

    }
}
