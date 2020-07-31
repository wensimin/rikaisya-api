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
    // 正则map
    private static final HashMap<RikaiType, Pattern> PATTERN_MAP = new HashMap<>();

    static {
        PATTERN_MAP.put(RikaiType.bilibili, Pattern.compile(BILIBILI_REX));
        PATTERN_MAP.put(RikaiType.url, Pattern.compile(URL_REX));
        PATTERN_MAP.put(RikaiType.ip, Pattern.compile(IP_REX));
        PATTERN_MAP.put(RikaiType.code, Pattern.compile(CODE_REX));
    }

    /**
     * 解析文本
     * @param sourceText 源文本
     * @return 文本类型与解析到的文本map
     */
    public static Map<RikaiType, List<String>> rikai(String sourceText) {
        HashMap<RikaiType, List<String>> resMap = new HashMap<>();
        PATTERN_MAP.forEach((key, value) -> {
            Matcher matcher = value.matcher(sourceText);
            List<String> groups = new ArrayList<>();
            Set<String> set = new HashSet<>();
            while (matcher.find()) {
                set.add(matcher.group());
            }
            groups.addAll(set);
            if (!groups.isEmpty()) {
                resMap.put(key, groups);
            }
        });
        return resMap;
    }
}
