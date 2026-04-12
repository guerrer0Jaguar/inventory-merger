package org.guerrer0jaguar.inventory.merger.controller;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionHandling {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<CustomHTTPmessage> responseWithBadRequest(
            IllegalArgumentException ex) {
        logError(ex);
        CustomHTTPmessage message = new CustomHTTPmessage(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ RuntimeException.class, Exception.class })
    public ResponseEntity<CustomHTTPmessage> handleExceptions(
            Exception ex,
            WebRequest request) {
        logError(ex);
        CustomHTTPmessage message = new CustomHTTPmessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An Internal error ocurred", LocalDateTime.now());

        return new ResponseEntity<CustomHTTPmessage>(message,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logError(
            Throwable ex) {
        log.error(ex.getMessage(), ex);
    }
}
