package com.shinyleo.basic.servlet;

import com.shinyleo.base.constant.Const;
import com.shinyleo.basic.biz.IAppBiz;
import com.shinyleo.basic.entity.AppEntity;
import com.shinyleo.parser.IParserRegexConstant;
import com.shinyleo.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * Created by shinyleo on 17/7/20.
 * * CMS系统分发serlvet,根据地址栏判断网站来源,
 * 注意：每个网站的静态文件必须加上网站绝对地址。例如：http://www.abc.com/html/abc.html
 */

@WebServlet("/index")
public class IndexServlet  extends BaseServlet {


    private static final long serialVersionUID = -7580260477467138079L;

    private static String  INDEX = "index.html", DEFAULT = "default.html";

    /**
     * 注入站点业务层
     */
    private IAppBiz appBiz;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取用户所请求的域名地址

        appBiz = (IAppBiz) getBean(request.getServletContext(), "appBiz");
        // 查询数据库获取域名对应Id
        int websiteId = 0;
        AppEntity website = appBiz.getByUrl(getDomain(request));
        if (website != null) {
            websiteId = website.getAppId();
        } else {
            this.outString(response, this.getResString("err.not.exist",this.getDomain(request)));
            return;
        }
        String path = "";

        if (!StringUtil.isBlank(website.getAppMobileStyle())) {
            path = isMobileDevice(request) ? IParserRegexConstant.MOBILE : ""; // 如果是手机访问就跳转到相应页面
        }

        String defaultHtmlPath = this.getRealPath(request, IParserRegexConstant.HTML_SAVE_PATH + File.separator
                + websiteId + File.separator + path + File.separator + "default.html");
        File file = new File(defaultHtmlPath);
        String url = IParserRegexConstant.HTML_SAVE_PATH + Const.SEPARATOR + websiteId + Const.SEPARATOR + path;
        String indexPosition = url + Const.SEPARATOR + INDEX;
        if (file.exists()) {
            indexPosition = url + Const.SEPARATOR + DEFAULT;
        }
        // 转发到网站首页
        request.getRequestDispatcher(indexPosition).forward(request, response);

    }
}
