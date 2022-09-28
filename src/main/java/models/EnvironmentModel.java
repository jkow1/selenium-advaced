package models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class EnvironmentModel {

    boolean active;
    public Map<String, Object> environmentConfig = new HashMap<>();

    public boolean isActive() {
        return active;
    }

    @JsonAnyGetter
    public Map<String, Object> getEnvironmentConfig() {
        return environmentConfig;
    }

    @JsonAnySetter
    public void setEnvironmentConfig(String key, Object value) {
        environmentConfig.put(key, value);
    }


}
