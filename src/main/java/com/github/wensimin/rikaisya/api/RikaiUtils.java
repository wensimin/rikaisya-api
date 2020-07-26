package com.github.wensimin.rikaisya.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 理解工具类
 */
public class RikaiUtils {
    // b站相关正则
    private static final String BILIBILI_REX = "(BV|AV|bv|av)([a-zA-Z]|[0-9])+";
    // 协议正则
    private static final String PROTOCOL_REX = "protno";
    // ip正则
    private static final String IP_REX = "ipno";
    // 验证码
    private static final String CODE_REX = "codeno";
    // 正则map
    private static final HashMap<RikaiType, Pattern> PATTERN_MAP = new HashMap<>();

    static {
        PATTERN_MAP.put(RikaiType.bilibili, Pattern.compile(BILIBILI_REX));
        PATTERN_MAP.put(RikaiType.protocol, Pattern.compile(PROTOCOL_REX));
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
            while (matcher.find()) {
                groups.add(matcher.group());
            }
            if (!groups.isEmpty()) {
                resMap.put(key, groups);
            }
        });
        return resMap;
    }
}
