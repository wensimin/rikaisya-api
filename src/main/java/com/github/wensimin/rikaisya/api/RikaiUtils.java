package com.github.wensimin.rikaisya.api;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 理解工具类
 */
public class RikaiUtils {
    // b站相关正则
    private static final String BILIBILI_REX = "(BV|AV|bv|av)([a-zA-Z]|[0-9])+";
    // url正则
    private static final String URL_REX = "(https?|ss|ftp)://(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&/=]*)";
    // ip正则
    private static final String IP_REX = "([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])\\.([01]?[0-9]{1,2}|2[0-4][0-9]|25[0-5])";
    // 验证码
    private static final String CODE_REX = "[0-9]{4,8}";
    // base64
    private static final String BASE64_REX = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}(={1,2})?)?$";

    private static final ArrayList<RikaiMethod> METHODS = new ArrayList<>();
    static {
        METHODS.add(new RikaiMethod(RikaiType.bilibili, Pattern.compile(BILIBILI_REX), null));
        METHODS.add(new RikaiMethod(RikaiType.url, Pattern.compile(URL_REX), null));
        METHODS.add(new RikaiMethod(RikaiType.ip, Pattern.compile(IP_REX), null));
        METHODS.add(new RikaiMethod(RikaiType.code, Pattern.compile(CODE_REX), null));
        METHODS.add(new RikaiMethod(RikaiType.base64, Pattern.compile(BASE64_REX), s -> new String(Base64.getDecoder().decode(s))));
    }

    /**
     * 理解text
     * @param sourceText 原始text
     * @return 结果set
     */
    public static Set<Rikai> rikai(String sourceText) {
        Set<Rikai> rikaiSet = new HashSet<>();
        METHODS.forEach(m -> {
            Matcher matcher = m.getPattern().matcher(sourceText);
            while (matcher.find()) {
                String text = matcher.group();
                String rikaiText = null;
                if (m.getFunction() != null) {
                    try {
                        rikaiText = m.getFunction().rikai(text);
                    } catch (Exception e) {
                        rikaiText = "error";
                    }
                }
                rikaiSet.add(new Rikai(text, m.getType(), rikaiText));
            }
        });
        return rikaiSet;
    }

}
