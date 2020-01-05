package com.sidh.practice.springboot.shiroSample.controller;

import com.sidh.practice.springboot.shiroSample.service.PageBuilder;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
public class ViewController {
    @Autowired
    PageBuilder pageBuilder;

    @GetMapping(value = "/page", produces = MediaType.TEXT_HTML_VALUE)
    public String page(HttpServletResponse response) throws IOException {
        Subject subject = SecurityUtils.getSubject();
        System.out.println("at page: subject principal:" + subject.getPrincipal());
        if (subject.getPrincipal() == null)
            response.sendRedirect("/login.html");
        return buildPage();
    }

    public String buildPage() {
        String s = "";

        try {
            s += pageBuilder.buildButton1();
        } catch (UnauthorizedException e) {

        }
        try {
            s += pageBuilder.buildButton2();

        } catch (UnauthorizedException e) {

        }
        try {
            s += pageBuilder.buildButton3();
        } catch (UnauthorizedException e) {

        }
        return s;
    }
}
