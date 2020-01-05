package com.sidh.practice.springboot.shiroSample.controller;

import com.sidh.practice.springboot.shiroSample.view.HtmlUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
public class ViewController {

    @GetMapping(value = "/page", produces = MediaType.TEXT_HTML_VALUE)
    public String page(HttpServletResponse response) throws IOException {
        Subject subject = SecurityUtils.getSubject();

        if (subject.getPrincipal() == null)
            response.sendRedirect("/login.html");
        return buildPage();
    }

    public String buildPage() {
        return buildButton1() +
                buildButton2() +
                buildButton3();
    }

    @RequiresPermissions("button3:view")
    private String buildButton3() {
        return HtmlUtils.button("button c");
    }

    @RequiresPermissions("button2:view")
    private String buildButton2() {
        return HtmlUtils.button("button b");
    }

    @RequiresPermissions("button1:view")
    private String buildButton1() {
        return HtmlUtils.button("button a");
    }
}
