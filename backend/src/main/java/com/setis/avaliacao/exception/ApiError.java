package com.setis.avaliacao.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ApiError {
    private LocalDateTime timestamp;
    private int status;
    private String message;
    private List<String> errors;
}
