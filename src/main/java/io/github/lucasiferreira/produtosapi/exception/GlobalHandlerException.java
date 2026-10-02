package io.github.lucasiferreira.produtosapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), Instant.now());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleProductAlreadyExists(EntityAlreadyExistsException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage(), Instant.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FieldsErrorResponse> handleValidErrors(MethodArgumentNotValidException e) {
        int status = HttpStatus.BAD_REQUEST.value();
        List<ErrorField> erros = e.getFieldErrors().stream().map(error -> new ErrorField(error.getField(), error.getDefaultMessage())).toList();
        FieldsErrorResponse fieldsErrorResponse = new FieldsErrorResponse(status, "Field Error!", erros);

        return ResponseEntity.badRequest().body(fieldsErrorResponse);
    }
}
