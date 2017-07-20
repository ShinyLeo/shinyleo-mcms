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
public class GuestTag  extends SecureTag{

    private static final Logger log = Logger.getLogger("AuthenticatedTag");

    public GuestTag() {
    }

    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        if(this.getSubject() != null && this.getSubject().getPrincipal() != null) {
            if(log.isDebugEnabled()) {
                log.debug("Subject exists or has a known identity (aka \'principal\').  Tag body will not be evaluated.");
            }
        } else {
            if(log.isDebugEnabled()) {
                log.debug("Subject does not exist or does not have a known identity (aka \'principal\').  Tag body will be evaluated.");
            }

            this.renderBody(env, body);
        }

    }
}
