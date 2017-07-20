package com.shinyleo.basic.parser;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by shinyleo on 17/7/20.
 */

@Component
@Scope("prototype")
public class BaseParser extends IGeneralParser{

    @Override
    public String parse(String html, Object... obj) {
        super.htmlContent = html;
        init(obj);
        htmlContent = parseGeneral();
        return htmlContent;
    }
}
