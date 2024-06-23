package com.saas.starter.common.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommonResponse<T> {
    private MessageResponse message;
    private T data;
    private boolean isSuccess;

    public static <T>CommonResponse<T> createSuccessData(T data) {
        return CommonResponse.<T>builder().data(data).isSuccess(true).build();
    }
}
