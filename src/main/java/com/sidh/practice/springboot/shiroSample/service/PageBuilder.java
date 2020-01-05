package com.sidh.practice.springboot.shiroSample.service;

import com.sidh.practice.springboot.shiroSample.view.HtmlUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class PageBuilder {

    @RequiresPermissions("button3:view")
    public String buildButton3() {
        return HtmlUtils.button("button c");
    }

    @RequiresPermissions("button2:view")
    public String buildButton2() {
        return HtmlUtils.button("button b");
    }

    @RequiresPermissions("button1:view")
    public String buildButton1() {
        return HtmlUtils.button("button a");
    }
}
