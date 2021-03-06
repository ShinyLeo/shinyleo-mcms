package com.shinyleo.parser.impl.general;

import com.shinyleo.parser.IParser;

/**
 * Created by shinyleo on 17/7/20.
 */
public class GlobalCopyrightParser extends IParser {

    private static final String GLOBAL_COPYRIGHT = "\\{ms:global.copyright/\\}";

    public GlobalCopyrightParser(String htmlContent, String newContent) {
        super.htmlCotent = htmlContent;
        super.newCotent = newContent;
    }

    public String parse() {
        return super.replaceAll("\\{ms:global.copyright/\\}");
    }
}
