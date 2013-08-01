package jobengine.app;

import jobengine.domain.ObjectNotFound;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ObjectNotFound.class})
    protected ResponseEntity<Object> handleObjectNotFound(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "the requested resource is not existent", new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}