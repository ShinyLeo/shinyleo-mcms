package com.shinyleo.parser.impl.general;

import com.shinyleo.parser.IParser;

/**
 * Created by shinyleo on 17/7/20.
 */
public class GlobalLogoParser extends IParser {

    private static final String GLOBAL_LOGO = "\\{ms:global.logo/\\}";

    public GlobalLogoParser(String htmlContent, String newContent) {
        super.htmlCotent = htmlContent;
        super.newCotent = newContent;
    }

    public String parse() {
        return super.replaceAll("\\{ms:global.logo/\\}");
    }
}
