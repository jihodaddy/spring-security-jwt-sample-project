package com.saas.starter.common.exception;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public ApplicationException(HttpStatus status, String message) {
      this.status = status;
      this.message = message;
    }

    public ApplicationException(String message, HttpStatus status, String message1) {
      super(message);
      this.status = status;
      this.message = message1;
    }
}
