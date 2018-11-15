package com.spring.boot.common.utils;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  httpclient 的配置文件
 * @author pmxin
 * @date 2018-0206 13:58:00
 */
@Configuration
public class HttpClient {

  private Integer maxTotal = 200;

  private Integer defaultMaxPerRoute = 100;

  private Integer connectTimeout = 5000;

  private Integer connectionRequestTimeout = 3000;

  private Integer socketTimeout = 60000;

  private boolean staleConnectionCheckEnabled = true;

  @Bean(name = "httpClientConnectionManager")
  public PoolingHttpClientConnectionManager getHttpClientConnectionManager() {
    PoolingHttpClientConnectionManager httpClientConnectionManager =
        new PoolingHttpClientConnectionManager();
    httpClientConnectionManager.setMaxTotal(maxTotal);
    httpClientConnectionManager.setDefaultMaxPerRoute(defaultMaxPerRoute);
    return httpClientConnectionManager;
  }

  @Bean(name = "httpClientBuilder")
  public HttpClientBuilder getHttpClientBuilder(
      @Qualifier("httpClientConnectionManager") PoolingHttpClientConnectionManager httpClientConnectionManager) {

    HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

    httpClientBuilder.setConnectionManager(httpClientConnectionManager);

    return httpClientBuilder;
  }

  @Bean
  public CloseableHttpClient getCloseableHttpClient(
      @Qualifier("httpClientBuilder") HttpClientBuilder httpClientBuilder) {
    return httpClientBuilder.build();
  }


  @Bean(name = "builder")
  public RequestConfig.Builder getBuilder() {
    RequestConfig.Builder builder = RequestConfig.custom();
    return builder.setConnectTimeout(connectTimeout)
        .setConnectionRequestTimeout(connectionRequestTimeout).setSocketTimeout(socketTimeout)
        .setStaleConnectionCheckEnabled(staleConnectionCheckEnabled);
  }

  @Bean
  public RequestConfig getRequestConfig(@Qualifier("builder") RequestConfig.Builder builder) {
    return builder.build();
  }

}
