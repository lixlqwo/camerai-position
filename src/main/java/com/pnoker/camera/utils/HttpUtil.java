package com.pnoker.camera.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String get(String url) {

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(6000).setConnectionRequestTimeout(6000).setSocketTimeout(6000).build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        try (CloseableHttpClient httpclient = HttpClients.createDefault(); CloseableHttpResponse response = httpclient.execute(httpGet)) {
            logger.info("start get request {} ", url);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                logger.info("response {} when get {} with {}", statusCode, url);
                return null;
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String responseContent = EntityUtils.toString(entity, "UTF8");
                logger.info("get {} result={}", url, responseContent);
                return responseContent;
            }
        } catch (Exception e) {
            logger.info("", e);
        }
        return null;
    }

}
