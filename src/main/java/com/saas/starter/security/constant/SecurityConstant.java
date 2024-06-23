package com.saas.starter.security.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class SecurityConstant {
  public static final String[] PERMIT_ALL_MATCHERS = {
          "/swagger-ui/**"
          , "/v3/api-docs/**"
          , "/swagger-resources/**"
          , "/error"
          , "/webjars/**"
          , "/generate-token"
          , "/actuator/**"
          , "/index.html"
          , "/api/v1/auth/**"
          , "/management/**"
  };
}
