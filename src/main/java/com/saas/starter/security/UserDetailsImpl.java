package com.saas.starter.security;

import com.saas.starter.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetailsCustom{
  private final transient Member member;

  /**
   * @param member
   */
  public UserDetailsImpl(Member member) {
    this.member = member;
  }

  @Override
  public String getEmail() {
    return this.member.getEmail();
  }

  @Override
  public String getRole() {
    return this.member.getRole().getValue();
  }

  @Override
  public String getUsername() {
    return this.member.getUsername();
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Collections.singleton(new SimpleGrantedAuthority("USER"));
  }

  @Override
  public String getPassword() {
    return this.member.getPassword();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  
  
}
