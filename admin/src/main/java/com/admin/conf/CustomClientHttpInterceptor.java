package com.admin.conf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class CustomClientHttpInterceptor implements ClientHttpRequestInterceptor {

  private static Logger LOGGER = LoggerFactory.getLogger(CustomClientHttpInterceptor.class);
  @Override
  public ClientHttpResponse intercept(HttpRequest httpRequest, byte[] body, ClientHttpRequestExecution clientHttpRequestExecution) throws IOException {
    LOGGER.info("Body: {}", new String(body, StandardCharsets.UTF_8));
    LOGGER.info("Request Method: {}", httpRequest.getMethod());
    LOGGER.info("Request URL: {}", httpRequest.getURI());
    return clientHttpRequestExecution.execute(httpRequest,body);
  }
}
