package com.vinicius.demo.infra.excecao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    private static final String UNKNOWN_ERROR_KEY = "unknown.error";

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);
    private final MessageSource messageSource;

    public ExceptionHandlerAdvice(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorDTO> handlerMethodArgumentNotValid(
            MethodArgumentNotValidException exception
    ) {
        logger.error("Exception {}, Message: {}", exception.getClass().getName(), exception.getMessage());
        Set<ErrorDTO> errors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> buildError(error.getCode(), error.getDefaultMessage()))
                .collect(Collectors.toSet());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(baseErrorBuilder(HttpStatus.BAD_REQUEST, errors));
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ApiErrorDTO> handlerMethodThrowable(Throwable exception) {
        logger.error("Exception {}, Message: {}", exception.getClass().getName(), exception.getMessage());
        Set<ErrorDTO> errors = Set.of(buildError(UNKNOWN_ERROR_KEY, exception.getMessage()));
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(baseErrorBuilder(HttpStatus.INTERNAL_SERVER_ERROR, errors));
    }

    private ErrorDTO buildError(String code, String message) {
        return new ErrorDTO(code, message);
    }

    private ApiErrorDTO baseErrorBuilder(HttpStatus httpStatus, Set<ErrorDTO> errorList) {
        return new ApiErrorDTO(
                LocalDateTime.now(),
                httpStatus.value(),
                httpStatus.name(),
                errorList);
    }

    private HttpStatus getResponseStatus(Throwable exception) {
        ResponseStatus responseStatus = exception.getClass().getAnnotation(ResponseStatus.class);
        if (exception.getClass().getAnnotation(ResponseStatus.class) == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return responseStatus.value();
    }

    @ExceptionHandler(ExcecaoGenericaDeNegocio.class)
    public ResponseEntity<ApiErrorDTO> handlerExcecaoGenericaDeNegocio(ExcecaoGenericaDeNegocio exception) {
        logger.error("Exception {}, Message: {}", exception.getClass().getName(), exception.getMessage());
        Set<ErrorDTO> errors = Set.of(buildError(UNKNOWN_ERROR_KEY, exception.getMessage()));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(baseErrorBuilder(HttpStatus.BAD_REQUEST, errors));
    }
}
