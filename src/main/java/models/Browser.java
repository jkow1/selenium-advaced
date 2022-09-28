package models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;


public class Browser {
    public Map<String, Object> browserConfig = new LinkedHashMap<>();

    @JsonAnySetter
    public void setBrowserConfig(String key, Object value) {
        browserConfig.put(key, value);
    }

    @JsonAnyGetter
    public Map<String, Object> getBrowserConfig() {
        return browserConfig;
    }


}
