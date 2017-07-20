package com.shinyleo.parser.impl.general;

import com.shinyleo.parser.IParser;

/**
 * Created by shinyleo on 17/7/20.
 */
public class GlobalKeywordParser extends IParser {

    private static final String GLOBAL_KEYWORD = "\\{ms:global.keyword/\\}";

    public GlobalKeywordParser(String htmlContent, String newContent) {
        super.htmlCotent = htmlContent;
        super.newCotent = newContent;
    }

    public String parse() {
        return super.replaceAll("\\{ms:global.keyword/\\}");
    }
}
