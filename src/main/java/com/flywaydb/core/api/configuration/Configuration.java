package com.flywaydb.core.api.configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fireflyhoo
 */
public class Configuration {

    String placeholderPrefix = "${";
    String placeholderSuffix = "}";
    String scriptPlaceholderPrefix = "FP__";
    String scriptPlaceholderSuffix = "__";

    public boolean isPlaceholderReplacement() {
        return true;
    }

    public boolean isExecuteInTransaction() {
        return false;
    }

    public Map<String, String> getPlaceholders() {
       return new HashMap<>();
    }

    public String getPlaceholderPrefix() {
        return placeholderPrefix;
    }

    public String getPlaceholderSuffix() {
        return placeholderSuffix;
    }

    public String getScriptPlaceholderPrefix() {
        return scriptPlaceholderPrefix;
    }

    public String getScriptPlaceholderSuffix() {
        return scriptPlaceholderSuffix;
    }
}
