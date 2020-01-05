package com.sidh.practice.springboot.shiroSample.view;

public class HtmlUtils {
    private static String startTag = "<div style='margin:50px;height:100px; width: 300px; padding-top: 20px; font-size: 60px; text-align: center;" +
            "border: 1px solid gray;'>";
    private static String endTag = "</div>";

    public static String button(String text) {
        return startTag + text + endTag;
    }
}
