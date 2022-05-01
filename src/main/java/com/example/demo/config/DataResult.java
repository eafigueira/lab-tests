package com.example.demo.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DataResult<T> {
    private boolean success;
    private T data;
    private String error;
}
