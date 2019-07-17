package demo.conf;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.web.client.RestTemplate;

public class CustomRestRemplateCustomize implements RestTemplateCustomizer {
  @Override
  public void customize(RestTemplate restTemplate) {
    restTemplate.getInterceptors().add(new CustomClientHttpInterceptor());
  }
}
