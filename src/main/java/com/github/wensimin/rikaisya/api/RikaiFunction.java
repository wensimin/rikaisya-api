package com.github.wensimin.rikaisya.api;

@FunctionalInterface
interface RikaiFunction {
    /**
     * 理解text 进行解码之类的操作
     * @param text 原始text
     * @return 解码后text
     */
    String rikai(String text);
}
