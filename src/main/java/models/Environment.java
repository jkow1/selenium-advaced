package models;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Environment {

    public Map<String, EnvironmentModel> environmentModelMap = new HashMap<>();

    @JsonAnyGetter
    public Map<String, EnvironmentModel> getEnvironmentModelMap() {
        return environmentModelMap;
    }

    @JsonAnySetter
    public void setEnvironmentModelMap(String key, EnvironmentModel value) {
        environmentModelMap.put(key, value);
    }

    public List<EnvironmentModel> getListOfEnvironments() {
        List<EnvironmentModel> listOfEnvironments = new ArrayList<>();
        environmentModelMap.forEach((key, value) -> listOfEnvironments.add(value));
        return listOfEnvironments;
    }
}
