package com.saas.starter.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserDetailsCustom extends UserDetails{
  String getEmail();
  String getUsername();
  String getRole();
}
