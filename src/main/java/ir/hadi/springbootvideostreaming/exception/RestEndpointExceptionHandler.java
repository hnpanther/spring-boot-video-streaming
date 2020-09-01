package ir.hadi.springbootvideostreaming.exception;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class RestEndpointExceptionHandler {

    @ExceptionHandler(value = ClientAbortException.class)
    public ResponseEntity<CustomErrorResponse> handleGenericIOException(ClientAbortException e) {
        CustomErrorResponse error = new CustomErrorResponse("ClientAbortException", e.getMessage());
        error.setTimestamp(LocalDateTime.now());
        error.setStatus((HttpStatus.INTERNAL_SERVER_ERROR.value()));
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
