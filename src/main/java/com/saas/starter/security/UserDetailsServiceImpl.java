package com.saas.starter.security;

import com.saas.starter.member.entity.Member;
import com.saas.starter.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService{
  private final MemberRepository memberRepository;
  
  @Override
  public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
    Member member = memberRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
        .orElseThrow(() ->
          new UsernameNotFoundException("User not found with username or email" + usernameOrEmail));

    return new UserDetailsImpl(member);
  }
  
}
