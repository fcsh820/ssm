package com.demo.ssm.controller;

import com.demo.ssm.util.ValidateCode;
import com.demo.ssm.util.security.RSA;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;

/**
 * Created by Administrator on 2015/12/5.
 */
@Controller
@Scope("prototype")
public class HomeController {

    @RequestMapping("/index")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("jsp/index");
        return modelAndView;
    }

    @RequestMapping("/welcome")
    public ModelAndView welcome() {
        ModelAndView modelAndView = new ModelAndView("jsp/welcome");
        return modelAndView;
    }

    @RequestMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("jsp/login");
        return modelAndView;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(String uname, String pwd, String validateCode) {
        System.out.println(uname);
        System.out.println(pwd);
        System.out.println(validateCode);

        return "1";
    }

    @RequestMapping("/code")
    public void getCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // 设置响应的类型格式为图片格式
        resp.setContentType("image/jpeg");
        //禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);

        HttpSession session = req.getSession();
        ValidateCode validateCode = new ValidateCode(65, 22, 4, 50);
        session.setAttribute("code", validateCode.getCode());
        validateCode.write(resp.getOutputStream());
    }
}
