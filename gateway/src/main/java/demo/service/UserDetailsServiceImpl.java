package demo.service;

import demo.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private AppUserRestSevice appUserRestSevice;

  public UserDetails loadUserByUsername(String userName) {
    AppUser appUser = this.appUserRestSevice.getUserByLogin(userName);
    if (appUser == null) {
      throw new UsernameNotFoundException("User " + userName + " was not found in the database");
    }

     List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

        // ROLE_USER, ROLE_ADMIN,..
        GrantedAuthority authority = new SimpleGrantedAuthority(appUser.getRole());
        grantList.add(authority);


    UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), //
      appUser.getEncrytedPassword(), grantList);

    return userDetails;
  }
}
