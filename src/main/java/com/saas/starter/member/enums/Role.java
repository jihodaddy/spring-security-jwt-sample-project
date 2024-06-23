package com.saas.starter.member.enums;

public enum Role {
  GUEST("guest"),USER("user");

  private String roleType;

  Role(String role) {
      this.roleType = role;
  }
  public String getValue() {
      return roleType;
  }
}
