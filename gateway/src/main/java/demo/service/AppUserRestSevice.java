package demo.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import demo.model.AppUser;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class AppUserRestSevice extends RestService {

  public AppUser getUserByLogin(String login){
    ObjectMapper mapper = new ObjectMapper();

    try {
      return mapper.readValue(get("resource" + login).getBody(), AppUser.class);
    } catch (IOException e) {
      e.printStackTrace();
    }
  return null;
  }

}
