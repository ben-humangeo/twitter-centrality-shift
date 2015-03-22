package com.thehumangeo.twitter.centrality;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by bparrish on 3/21/15.
 */
public class TwitterProperties {
    private final Properties configuration;

    private static final String TWITTER_API_KEY = "twitter.api.key";
    private static final String TWITTER_API_SECRET = "twitter.api.secret";
    private static final String TWITTER_APPLICATION_ONLY_AUTHENTICATION = "twitter.api.app-only.authentication.url";

    public TwitterProperties() throws IOException {
        InputStream propertiesInputStream = TwitterProperties.class.getClassLoader().getResourceAsStream("config.properties");

        configuration = new Properties();

        configuration.load(propertiesInputStream);
    }

    public String getAPIKey() {
        return configuration.getProperty(TWITTER_API_KEY);
    }

    public String getAPISecret() {
        return configuration.getProperty(TWITTER_API_SECRET);
    }

    public String getApplicationOnlyAuthentication() {
        return configuration.getProperty(TWITTER_APPLICATION_ONLY_AUTHENTICATION);
    }
}
