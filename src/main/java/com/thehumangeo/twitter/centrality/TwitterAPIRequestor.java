package com.thehumangeo.twitter.centrality;

import org.apache.commons.codec.binary.Base64;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by bparrish on 3/21/15.
 */
public class TwitterAPIRequestor {


    private static final Logger logger = LoggerFactory.getLogger(TwitterAPIRequestor.class);
    private static final String SEARCH_URL = "https://api.twitter.com/1.1/search/tweets.json?q=%40twitterapi";

    private final String key;
    private final String secret;
    private final String bearerToken;

    public TwitterAPIRequestor() throws IOException {
        TwitterProperties properties = new TwitterProperties();

        key = properties.getAPIKey();
        secret = properties.getAPISecret();

        bearerToken = requestBearerToken(properties.getApplicationOnlyAuthentication());

        fetchTweets(SEARCH_URL);
    }

    private String encodeKeys(String consumerKey, String consumerSecret) {
        try {
            String encodedConsumerKey = URLEncoder.encode(consumerKey, "UTF-8");
            String encodedConsumerSecret = URLEncoder.encode(consumerSecret, "UTF-8");

            String fullKey = encodedConsumerKey + ":" + encodedConsumerSecret;
            byte[] encodedBytes = Base64.encodeBase64(fullKey.getBytes());
            return new String(encodedBytes);
        }
        catch (UnsupportedEncodingException e) {
            return new String();
        }
    }

    private String requestBearerToken(String endPointUrl) throws IOException {
        HttpsURLConnection connection = null;

        String encodedCredentials = encodeKeys(key,secret);

        try {
            URL url = new URL(endPointUrl);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Host", "api.twitter.com");
            connection.setRequestProperty("User-Agent", "Your Program Name");
            connection.setRequestProperty("Authorization", "Basic " + encodedCredentials);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            connection.setRequestProperty("Content-Length", "29");
            connection.setUseCaches(false);

            writeRequest(connection, "grant_type=client_credentials");

            // Parse the JSON response into a JSON mapped object to fetch fields from.
            JSONObject obj = (JSONObject) JSONValue.parse(readResponse(connection));

            if (obj != null) {
                String tokenType = (String)obj.get("token_type");
                String token = (String)obj.get("access_token");

                return ((tokenType.equals("bearer")) && (token != null)) ? token : "";
            }
            return new String();
        }
        catch (MalformedURLException e) {
            throw new IOException("Invalid endpoint URL specified.", e);
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private boolean writeRequest(HttpsURLConnection connection, String textBody) {
        try {
            BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
            wr.write(textBody);
            wr.flush();
            wr.close();

            return true;
        }
        catch (IOException e) { return false; }
    }

    private String readResponse(HttpsURLConnection connection) {
        try {
            StringBuilder str = new StringBuilder();

            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line = "";
            while((line = br.readLine()) != null) {
                str.append(line + System.getProperty("line.separator"));
            }
            return str.toString();
        }
        catch (IOException e) { return new String(); }
    }

    private String fetchTweets(String endPointUrl) throws IOException {
        HttpsURLConnection connection = null;

        try {
            URL url = new URL(endPointUrl);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Host", "api.twitter.com");
            connection.setRequestProperty("User-Agent", "Your Program Name");
            connection.setRequestProperty("Authorization", "Bearer " + bearerToken);
            connection.setUseCaches(false);

            logger.info(connection.toString());

            String response = readResponse(connection);

            logger.info(response);

            // Parse the JSON response into a JSON mapped object to fetch fields from.
            JSONObject obj = (JSONObject)JSONValue.parse(response);

            if (obj != null) {
                String tweet = obj.get("text").toString();

                return (tweet != null) ? tweet : "";
            }

            return new String();
        }
        catch (MalformedURLException e) {
            throw new IOException("Invalid endpoint URL specified.", e);
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

}
