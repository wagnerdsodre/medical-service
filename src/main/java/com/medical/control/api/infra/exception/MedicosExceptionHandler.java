package com.medical.control.api.infra.exception;


import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MedicosExceptionHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<Object> TrataErro404() {
    return ResponseEntity.notFound().build();
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Object> NotValidMethod(MethodArgumentNotValidException ex) {
    List<FieldError> fields = ex.getFieldErrors();
    return ResponseEntity.badRequest().body(fields.stream().map(DataErrorvalidation::new).toList());
  }

  public record DataErrorvalidation(String colunm, String message) {

    DataErrorvalidation(FieldError erro) {
      this(erro.getField(), erro.getDefaultMessage());
    }

  }


}
