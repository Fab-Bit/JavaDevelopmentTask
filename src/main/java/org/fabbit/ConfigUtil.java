package org.fabbit;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {
    private static Properties properties = new Properties();

    static {
        try (InputStream input = new FileInputStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String getToken(Platform platform) {
        if (platform == Platform.GITHUB) {
            return properties.getProperty("github.token");
        } else if (platform == Platform.GITLAB) {
            return properties.getProperty("gitlab.token");
        }
        return null;
    }
    public static String getApiUrl(Platform platform) {
        if (platform == Platform.GITHUB) {
            return properties.getProperty("github.api.url");
        } else if (platform == Platform.GITLAB) {
            return properties.getProperty("gitlab.api.url");
        }
        return null;
    }

    public static String getUsername(Platform platform) {
        if (platform == Platform.GITHUB) {
            return properties.getProperty("github.username");
        } else if (platform == Platform.GITLAB) {
            return properties.getProperty("gitlab.username");
        }
        return null;
    }
}