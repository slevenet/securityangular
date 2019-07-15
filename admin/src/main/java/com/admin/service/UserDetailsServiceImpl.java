package com.admin.service;

import com.admin.model.AppUser;
import com.admin.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl {

  @Autowired
  private AppUserRepository appUserRepository;

  @Autowired
  private AppRoleDAO appRoleDAO;


  public UserDetails loadUserByUsername(String userName) {
    AppUser appUser = this.appUserRepository.findFirstByUserName(userName);
    if (appUser == null) {
      throw new UsernameNotFoundException("User " + userName + " was not found in the database");
    }

    // [ROLE_USER, ROLE_ADMIN,..]
    List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getUserId());

    List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
    if (roleNames != null) {
      for (String role : roleNames) {
        // ROLE_USER, ROLE_ADMIN,..
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        grantList.add(authority);
      }
    }

    UserDetails userDetails = (UserDetails) new User(appUser.getUserName(), //
      appUser.getEncrytedPassword(), grantList);

    return userDetails;
  }
}
