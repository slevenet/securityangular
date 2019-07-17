package demo.conf;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfBeans {

  @Bean
  @Qualifier("customRestRemplateCustomize")
  public CustomRestRemplateCustomize customRestRemplateCustomize(){
    return new CustomRestRemplateCustomize();
  }

  @Bean
  @DependsOn(value = {"customRestRemplateCustomize"})
  public RestTemplateBuilder restTemplateBuilder(){
    return new RestTemplateBuilder();
  }

  @Bean
  @DependsOn(value = {"restTemplateBuilder"})
  public RestTemplate restTemplate(){
    return restTemplateBuilder().build();
  }

  @Bean
  public HttpHeaders httpHeaders(){
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    return httpHeaders;
  }
}
