package ir.hadi.springbootvideostreaming.exception;

import org.apache.catalina.connector.ClientAbortException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ClientAbortException.class)
    public void handleClientAbortException(ClientAbortException e) {
        System.out.println("=========================================================================");
        System.out.println("Exception Here - ClientAbortException");
        System.out.println("=========================================================================");
//        CustomErrorResponse error = new CustomErrorResponse("ClientAbortException", e.getMessage());
//        error.setTimestamp(LocalDateTime.now());
//        error.setStatus((HttpStatus.INTERNAL_SERVER_ERROR.value()));
//        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
