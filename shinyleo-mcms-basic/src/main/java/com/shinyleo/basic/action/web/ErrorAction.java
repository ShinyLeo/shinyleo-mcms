package com.shinyleo.basic.action.web;

import com.shinyleo.basic.action.BaseAction;
import com.shinyleo.basic.parser.BaseParser;
import com.shinyleo.util.FileUtil;
import com.shinyleo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * Created by shinyleo on 17/7/20.
 */
@Controller("errorAction")
@RequestMapping("/error")
public class ErrorAction extends BaseAction {
    /**
     *文章解析器
     */
    @Autowired
    private BaseParser baseParser;

    /**
     * 返回404页面
     */
    @RequestMapping("/{code}")
    @ResponseBody
    public void code(@PathVariable("code") String code, HttpServletRequest req, HttpServletResponse resp){
        String tmpFilePath = this.getTemplatePath(req) + File.separator + code+".htm";
        String content = FileUtil.readFile(tmpFilePath);
        if (StringUtil.isBlank(content)) {
            content = FileUtil.readFile(this.getRealPath(req,"/errors/"+code+".htm"));
            if (StringUtil.isBlank(content)) {
                content = FileUtil.readFile(this.getRealPath(req,"/errors/error.htm"));
            }
            content = content.replace("{code/}", code);
        } else {
            content = this.parserMsTag(content,baseParser, req);
        }
        this.outString(resp, content);
    }
}
