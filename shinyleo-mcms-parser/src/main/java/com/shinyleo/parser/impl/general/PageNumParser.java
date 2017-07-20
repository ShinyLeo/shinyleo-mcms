package com.shinyleo.parser.impl.general;

import com.shinyleo.parser.IParser;
import com.shinyleo.util.PageUtil;

/**
 * Created by shinyleo on 17/7/20.
 */
public class PageNumParser  extends IParser {

    private PageUtil page;
    private static final String PAGE_TOTAL = "\\{ms:page.total/\\}";
    private static final String PAGE_CUR = "\\{ms:page.cur/\\}";
    private static final String PAGE_RCOUNT = "\\{ms:page.rcount/\\}";

    public PageNumParser(String htmlContent, PageUtil page) {
        super.htmlCotent = htmlContent;
        this.page = page;
    }

    public String parse() {
        if(this.page != null) {
            super.htmlCotent = this.replaceRegex("\\{ms:page.total/\\}", this.page.getPageCount());
            super.htmlCotent = this.replaceRegex("\\{ms:page.cur/\\}", this.page.getPageNo() + 1);
            super.htmlCotent = this.replaceRegex("\\{ms:page.rcount/\\}", this.page.getRecordCound());
            return super.htmlCotent;
        } else {
            return super.htmlCotent;
        }
    }

    public String replaceRegex(String regex, int num) {
        super.newCotent = Integer.toString(num);
        String html = super.replaceAll(regex);
        return html;
    }

}
