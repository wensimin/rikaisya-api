package com.github.wensimin.rikaisya.api;

/**
 * 理解结果对象
 */
public class Rikai {
    /**
     * 抓取到的text
     */
    private String text;
    /**
     * 类型
     */
    private RikaiType type;
    /**
     * 解码后的text
     */
    private String rikaiText;

    public Rikai(String text, RikaiType type, String rikaiText) {
        if (text == null || type == null) {
            throw new NullPointerException();
        }
        this.text = text;
        this.type = type;
        this.rikaiText = rikaiText;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public RikaiType getType() {
        return type;
    }

    public void setType(RikaiType type) {
        this.type = type;
    }

    public String getRikaiText() {
        return rikaiText;
    }

    public void setRikaiText(String rikaiText) {
        this.rikaiText = rikaiText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rikai rikai = (Rikai) o;
        return text.equals(rikai.text) && type == rikai.type && (rikaiText != null ? rikaiText.equals(rikai.rikaiText) : rikai.rikaiText == null);
    }

    @Override
    public int hashCode() {
        int result = text.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + (rikaiText != null ? rikaiText.hashCode() : 0);
        return result;
    }
}
