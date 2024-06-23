package com.saas.starter.common.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class MessageResponse {
    private String description;
    private String code;
    private List<Detail> details;

    @Builder
    @Getter
    @Setter
    public static class Detail {
        String key;
        String value;
    }
}
