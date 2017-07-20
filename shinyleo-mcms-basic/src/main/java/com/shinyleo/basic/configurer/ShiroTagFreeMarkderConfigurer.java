package com.shinyleo.basic.configurer;

import com.jagregory.shiro.freemarker.ShiroTags;
import freemarker.template.TemplateException;

import java.io.IOException;

/**
 * Created by shinyleo on 17/7/20.
 */
public class ShiroTagFreeMarkderConfigurer  extends org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer {
    @Override
    public void afterPropertiesSet() throws IOException, TemplateException {
        super.afterPropertiesSet();
        this.getConfiguration().setSharedVariable("shiro", new ShiroTags());
    }
}
