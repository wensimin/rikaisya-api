package com.github.wensimin.rikaisya.api;

import java.util.regex.Pattern;

/**
 * 理解方法对象
 */
public class RikaiMethod {
    private RikaiType type;
    /**
     * 抓取文本的正则
     */
    private Pattern pattern;
    private RikaiFunction function;

    public RikaiMethod(RikaiType type, Pattern pattern, RikaiFunction function) {
        this.type = type;
        this.pattern = pattern;
        this.function = function;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public RikaiType getType() {
        return type;
    }

    public void setType(RikaiType type) {
        this.type = type;
    }

    public RikaiFunction getFunction() {
        return function;
    }

    public void setFunction(RikaiFunction function) {
        this.function = function;
    }
}
