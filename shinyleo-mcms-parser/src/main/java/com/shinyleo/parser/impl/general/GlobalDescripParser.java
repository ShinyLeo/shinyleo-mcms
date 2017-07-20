package com.shinyleo.parser.impl.general;

import com.shinyleo.parser.IParser;

/**
 * Created by shinyleo on 17/7/20.
 */
public class GlobalDescripParser extends IParser {

    private static final String GLOBAL_DESCRIP = "\\{ms:global.descrip/\\}";

    public GlobalDescripParser(String htmlContent, String newContent) {
        super.htmlCotent = htmlContent;
        super.newCotent = newContent;
    }

    public String parse() {
        return super.replaceAll("\\{ms:global.descrip/\\}");
    }
}
