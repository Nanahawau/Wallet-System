package com.fgt.walletsystem.utility.http;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fgt.walletsystem.utility.UtilityService;
import com.fgt.walletsystem.utility.http.HttpUtility;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RestClient {


    @Value("${paystack_secret_key}")
    private String clientSecret;
    ObjectMapper objectMapper = new ObjectMapper();


    public <T> Optional<T> get(String url, Class<T> elementClass) {
        CloseableHttpClient httpClient = null;
        T res = null;
        try {
            httpClient = HttpUtility.getHttpClient(url);
            HttpGet getRequest = new HttpGet(url);
            getRequest.setHeader("Authorization", "Bearer " + clientSecret);
            CloseableHttpResponse response = httpClient.execute(getRequest);
            try {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
                    log.info("RestClient response {}: ", UtilityService.convertInputStreamToString(response.getEntity().getContent()));
                    res = objectMapper.readValue(response.getEntity().getContent(), elementClass);
                } else {
                    String error = printError(response);
                    log.error("Unsuccessful Request : {}", error);
                }
            } catch (Exception e) {
                log.error(e.toString());
            } finally {
                response.close();
            }

        } catch (Exception e) {
            log.error(e.toString());
        } finally {
            try {
                httpClient.close();
            } catch (Exception e) {
                log.error(e.toString());
            }
        }
        return Optional.ofNullable(res);
    }

    protected String printError(CloseableHttpResponse response) {
        StringBuilder sb = new StringBuilder();
        String line = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    protected String fromListToString(List<String> withoutLabels) {
        return withoutLabels.stream().collect(Collectors.joining(","));
    }


    public <T> Optional<T> post(String url, Object requestDTO, Class<T> elementClass) {
        CloseableHttpClient httpclient = null;
        T res = null;
        try {
            httpclient = HttpUtility.getHttpClient(url);
            HttpPost postRequest = new HttpPost(url);

            postRequest.setHeader("Content-Type", "application/json");
            postRequest.setHeader("Authorization", "Bearer " + clientSecret);

            log.debug("URL: {}", url);
            log.debug("Request: {}", requestDTO);

            String req = objectMapper.writeValueAsString(requestDTO);
            StringEntity postingString = new StringEntity(req);

            postRequest.setEntity(postingString);
            CloseableHttpResponse response = httpclient.execute(postRequest);
            try {
                if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                    log.info("RestClient response {}: ", UtilityService.convertInputStreamToString(response.getEntity().getContent()));

                    objectMapper.configure(JsonParser.Feature.AUTO_CLOSE_SOURCE, true);
                    res = objectMapper.readValue(response.getEntity().getContent(), elementClass);
                } else {
                    String error = printError(response);
                    log.error("Error: {}", error);
                }
            } finally {
                response.close();
            }

        } catch (Exception e) {
            log.error(e.toString());
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                log.error(e.toString());
            }
        }
        return Optional.ofNullable(res);
    }


}
