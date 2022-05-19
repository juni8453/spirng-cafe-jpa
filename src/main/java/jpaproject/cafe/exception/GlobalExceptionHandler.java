package jpaproject.cafe.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<RestResponse> IllegalArgumentExceptionMethod(IllegalArgumentException exception) {

        String errorMessage = exception.getMessage();
        log.error("errorMessage = {}", errorMessage);

        RestResponse restResponse = RestResponse.of(errorMessage);

        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<RestResponse> AuthorizationExceptionMethod(AuthorizationException exception) {

        String errorMessage = exception.getMessage();
        log.error("errorMessage = {}", errorMessage);

        RestResponse restResponse = RestResponse.of(errorMessage);

        return new ResponseEntity<>(restResponse, HttpStatus.BAD_REQUEST);
    }
}
