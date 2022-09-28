package configuration;

import lombok.extern.slf4j.Slf4j;
import models.Browser;
import models.Environment;
import models.EnvironmentModel;

import java.util.List;
import java.util.Map;

@Slf4j
public class AppProperties {

    YamlConfigReader yamlConfigReader = new YamlConfigReader();
    private Browser browser;
    private Environment environment;

    public AppProperties() {
        setBrowserProperties();
        setEnvironmentProperties();
    }

    public static AppProperties getInstance() {
        return AppProperties.AppPropertiesSingleton.INSTANCE;
    }

    public static class AppPropertiesSingleton {
        private static final AppProperties INSTANCE = new AppProperties();
    }

    private void setEnvironmentProperties() {
        List<EnvironmentModel> listOfEnvironments = findActiveEnvironments();

        if (listOfEnvironments.size() > 1) {
            log.warn("There is more than one [{}] active environment!", listOfEnvironments.size());
            addEnvPropertyToSystem(listOfEnvironments);
        } else if (listOfEnvironments.size() == 1) {
            addEnvPropertyToSystem(listOfEnvironments);
        } else {
            log.error("There is no active environment!");
        }
    }

    private List<EnvironmentModel> findActiveEnvironments() {
        environment = yamlConfigReader.getConfig().getEnvironment();
        return environment.getListOfEnvironments().stream().filter(EnvironmentModel::isActive).toList();
    }

    private void addEnvPropertyToSystem(List<EnvironmentModel> list) {
        setProperties(list.get(0).getEnvironmentConfig(), "environment");
    }

    private void setBrowserProperties() {
        browser = yamlConfigReader.getConfig().getBrowser();
        setProperties(browser.getBrowserConfig(), "browser");
    }

    private void setProperties(Map<String, Object> map, String string) {
        map.forEach((key, value) -> {
            System.setProperty(key, value.toString());
            log.info("Loaded {} property: {}, {}", string, key, value);
        });
    }
}




