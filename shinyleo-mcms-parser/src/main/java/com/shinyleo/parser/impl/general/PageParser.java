package com.shinyleo.parser.impl.general;

import com.shinyleo.parser.IParser;
import com.shinyleo.util.PageUtil;

/**
 * Created by shinyleo on 17/7/20.
 */
public class PageParser  extends IParser {

    private final String PAGE_INDEX = "\\{ms:page.index/\\}";
    private final String PAGE_PRE = "\\{ms:page.pre/\\}";
    private final String PAGE_NEXT = "\\{ms:page.next/\\}";
    private final String PAGE_OVER = "\\{ms:page.last/\\}";
    private PageUtil page;

    public PageParser(String htmlContent, PageUtil page) {
        super.htmlCotent = htmlContent;
        this.page = page;
    }

    public String parse() {
        if(this.page != null) {
            super.newCotent = this.page.getIndexUrl();
            String indexHtml = super.replaceAll("\\{ms:page.index/\\}");
            super.htmlCotent = indexHtml;
            super.newCotent = this.page.getPreviousUrl();
            String preHtml = super.replaceAll("\\{ms:page.pre/\\}");
            super.htmlCotent = preHtml;
            super.newCotent = this.page.getNextUrl();
            String nextHtml = super.replaceAll("\\{ms:page.next/\\}");
            super.htmlCotent = nextHtml;
            super.newCotent = this.page.getLastUrl();
            String traileHtml = super.replaceAll("\\{ms:page.last/\\}");
            return traileHtml;
        } else {
            return this.htmlCotent;
        }
    }

}
