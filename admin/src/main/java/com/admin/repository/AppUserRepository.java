package com.admin.repository;

import com.admin.model.AppUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<AppUser, Long> {

  AppUser findFirstByUserName(String userName);
}
