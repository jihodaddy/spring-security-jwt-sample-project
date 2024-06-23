package com.saas.starter.common.exception;

import lombok.*;

import java.util.Date;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ErrorDetail {
    private Date timestamp;
    private String message;
    private String details;
}
