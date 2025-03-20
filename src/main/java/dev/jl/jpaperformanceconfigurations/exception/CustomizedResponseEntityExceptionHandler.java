package dev.jl.jpaperformanceconfigurations.exception;

import jakarta.persistence.OptimisticLockException;
import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<ExceptionDto> handleException(WebRequest webRequest, Exception e){
        return buildResponse(webRequest, e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ExceptionDto> handleResourceNotFoundException(WebRequest webRequest, ResourceNotFoundException e){
        return buildResponse(webRequest, e, HttpStatus.NOT_FOUND);
    }

    ResponseEntity<ExceptionDto> buildResponse(WebRequest webRequest, Exception e, HttpStatus httpStatus){
        ExceptionDto body = new ExceptionDto(
                webRequest.getDescription(false),
                e.getMessage(),
                Instant.now(),
                httpStatus.value(),
                httpStatus.getReasonPhrase());
        return ResponseEntity
                .status(httpStatus)
                .body(body);
    }
}
