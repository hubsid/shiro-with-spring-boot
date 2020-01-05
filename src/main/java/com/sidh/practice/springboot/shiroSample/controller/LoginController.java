package com.sidh.practice.springboot.shiroSample.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@RestController
public class LoginController {

    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response, @RequestParam String user, @RequestParam String pass) throws IOException, ServletException {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(user, pass));
        } catch (ShiroException e) {
            response.getOutputStream().println(e.getMessage());
            response.setContentType("text/html");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/login.html");
            requestDispatcher.include(request, response);
        }
        Cookie[] cookies = request.getCookies();
        Cookie cookie = Arrays.stream(cookies).filter(cookie1 -> cookie1.getName().equals("redirectUrl")).findFirst().orElse(null);
        if(cookie == null || cookie.getName() == null || cookie.getName().isEmpty()) {
            cookie = new Cookie("redirectUrl", "/page");
        }
        else {
            cookie = new Cookie("redirectUrl", cookie.getValue());
        }
        response.addCookie(cookie);
        response.sendRedirect(cookie.getValue());
    }

    @RequestMapping("/logout")
    public void logout(HttpServletResponse response) throws IOException {
        SecurityUtils.getSubject().logout();
        response.sendRedirect("/login.html");
    }
}
