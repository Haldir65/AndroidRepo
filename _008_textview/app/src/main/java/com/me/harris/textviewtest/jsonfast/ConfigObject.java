package com.me.harris.textviewtest.jsonfast;
import java.util.List;

public class ConfigObject {
    /**
     * args : {}
     * goto_external_browser : ["iOS","Android"]
     * type : const
     * value : http://share.telebox88.com/XiaocongGroup
     */

    public String type;
    public String value;
    public List<String> goto_external_browser;

    public ConfigObject() {
    }

    @Override
    public String toString() {
        return "ConfigObject{" +
                "type='" + type + '\'' +
                ", value='" + value + '\'' +
                ", goto_external_browser=" + goto_external_browser +
                '}';
    }
}
