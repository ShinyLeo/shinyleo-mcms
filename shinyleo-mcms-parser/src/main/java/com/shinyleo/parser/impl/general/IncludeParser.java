package com.shinyleo.parser.impl.general;

import com.shinyleo.parser.IParser;
import com.shinyleo.util.FileUtil;
import com.shinyleo.util.StringUtil;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by shinyleo on 17/7/20.
 */
public class IncludeParser extends IParser {

    String path;
    private static final String INCLUDE = "\\{ms:include filename\\=(.*?)\\s*/}";

    public IncludeParser(String htmlContent, String path, String mobilePath) {
        super.mobilePath = mobilePath;
        super.htmlCotent = htmlContent;
        this.path = path;
        if(!StringUtil.isBlank(mobilePath)) {
            this.path = path + File.separator + super.mobilePath;
        }

    }

    public IncludeParser(String htmlContent, String path) {
        super.htmlCotent = htmlContent;
        this.path = path;
    }

    public String parse() {
        String html = super.htmlCotent;

        for(int strNum = this.includeNum(super.htmlCotent); strNum != 0; strNum = this.includeNum(super.htmlCotent)) {
            String htmlInclude = includeContentPrase(super.htmlCotent, this.path);
            super.newCotent = htmlInclude;
            super.htmlCotent = super.replaceFirst("\\{ms:include filename\\=(.*?)\\s*/}");
            html = super.htmlCotent;
        }

        return html;
    }

    private static String includeContentPrase(String html, String path) {
        String includeContent = "";
        Pattern patternL = Pattern.compile("\\{ms:include filename\\=(.*?)\\s*/}");
        Matcher matcherL = patternL.matcher(html);
        if(matcherL.find()) {
            String includeName = matcherL.group(1);
            File file = new File(path + File.separator + includeName);
            if(!file.exists()) {
                includeContent = includeName + "不存在，请仔细检查该模版的文件！";
            } else {
                includeContent = FileUtil.readFile(path + File.separator + includeName);
            }
        }

        return includeContent;
    }

    private int includeNum(String html) {
        int includeNum = count(html, "\\{ms:include filename\\=(.*?)\\s*/}");
        return includeNum;
    }
}
