package com.shinyleo.parser.impl.general;

import com.shinyleo.parser.IParser;

/**
 * Created by shinyleo on 17/7/20.
 */
public class GlobalSkinUrlParser  extends IParser {

    private static final String GLOBAL_SKIN = "\\{ms:globalskin.url/\\}";

    public GlobalSkinUrlParser(String htmlContent, String newContent) {
        super.htmlCotent = htmlContent;
        super.newCotent = newContent;
    }

    public String parse() {
        return super.replaceAll("\\{ms:globalskin.url/\\}");
    }
}
