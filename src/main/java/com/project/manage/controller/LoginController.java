package com.project.manage.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.manage.entity.TSysUser;

@Controller
@RequestMapping(value = "/auth")
public class LoginController {
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String submitLogin(String userName, String password, HttpServletRequest request) {
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            subject.login(token);
            TSysUser user = (TSysUser) subject.getPrincipal();
        } catch (DisabledAccountException e) {
            request.setAttribute("msg", "账户已被禁用");
            return "auth/login";
        } catch (AuthenticationException e) {
            request.setAttribute("msg", "用户名或密码错误");
            return "auth/login";
        }

        // 执行到这里说明用户已登录成功
        return "auth/index";
    }


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "auth/login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String loginSuccessMessage(HttpServletRequest request) {
        String username = "未登录";
        TSysUser currentLoginUser = (TSysUser)SecurityUtils.getSubject().getPrincipal();

        if (currentLoginUser != null && StringUtils.isNotEmpty(currentLoginUser.getUserName())) {
            username = currentLoginUser.getUserName();
        } else {
            return "auth/login";
        }
        request.setAttribute("username", username);
        return "auth/index";
    }

    //被踢出后跳转的页面
    @RequestMapping(value = "/kickout", method = RequestMethod.GET)
    public String kickOut() {
        return "auth/kickout";
    }
	
}
