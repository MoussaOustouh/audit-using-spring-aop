package mo.spring.auditusingspringaop.exceptions;

import mo.spring.auditusingspringaop.exceptions.responses.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionsHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> HandleUserException(NotFoundException ex, WebRequest request){
        ErrorResponse errorResponse = new ErrorResponse(LocalDateTime.now(), ex.getMessage(), HttpStatus.NOT_FOUND.value());

        return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
