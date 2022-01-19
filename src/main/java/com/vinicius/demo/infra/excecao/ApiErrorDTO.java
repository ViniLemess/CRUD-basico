package com.vinicius.demo.infra.excecao;

import java.time.LocalDateTime;
import java.util.Set;

public class ApiErrorDTO {
    private LocalDateTime timestamp;
    private Integer status;
    private String code;
    private Set<ErrorDTO> errors;

    public ApiErrorDTO(LocalDateTime timestamp, Integer status, String code, Set<ErrorDTO> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.code = code;
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public Set<ErrorDTO> getErrors() {
        return errors;
    }
}
