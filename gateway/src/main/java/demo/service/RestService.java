package demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public abstract class RestService {
  @Autowired
  private HttpHeaders httpHeaders;
  @Autowired
  private RestTemplate restTemplate;

  protected ResponseEntity<String> post(String url, String object){
    HttpEntity<String> httptEntity = new HttpEntity(object,httpHeaders);
    return restTemplate.exchange(url, HttpMethod.POST, httptEntity, String.class);
  }

  protected ResponseEntity<String> put(String url, String object){
    HttpEntity<String> httptEntity = new HttpEntity(object,httpHeaders);
    return restTemplate.exchange(url, HttpMethod.PUT, httptEntity, String.class);
  }

  protected ResponseEntity<String> get(String url){
    HttpEntity<String> httptEntity = new HttpEntity(httpHeaders);
    return restTemplate.exchange(url, HttpMethod.GET, httptEntity, String.class);
  }
}
