package com.shinyleo.basic.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by shinyleo on 17/7/20.
 */
@Controller
@RequestMapping("/${managerPath}/basicType")
public class BasicTypeAction extends BaseAction{

    public void query(HttpServletRequest request, HttpServletResponse response) {

    }
}
