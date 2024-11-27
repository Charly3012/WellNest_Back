package com.wellnest.wellnest.Config;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entidadNoEncontrada(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        var errors = e.getFieldErrors().stream()
                .map(DatosErrorValidacion::new)
                .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("Errors", errors);

        return ResponseEntity.badRequest().body(response);
    }

    private record DatosErrorValidacion(String Field,
                                        String Error
    ){

        public DatosErrorValidacion(FieldError fieldError){
            this(fieldError.getField(), fieldError.getDefaultMessage());
        }
    }



    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> errorIntegridadDatosSql(DataIntegrityViolationException e) {
        String error = "Error de integridad de datos desconocido";
        String errorType = "DATA INTEGRITY VIOLATION";

        Throwable rootCause = e.getRootCause();
        if (rootCause instanceof SQLException) {
            SQLException sqlException = (SQLException) rootCause;

            int codigoError = sqlException.getErrorCode();

            if (codigoError == 1062) {
                error = "Datos duplicados";
                if (sqlException.getMessage().contains("Duplicate entry") && sqlException.getMessage().contains("users.nickname")) {
                    error = "There is already a user with the same nickname";
                } else if (sqlException.getMessage().contains("Duplicate entry") && sqlException.getMessage().contains("users.email")) {
                    error = "There is already a user with the same email address";
                }
                errorType = "UNIQUE CONSTRAINT VIOLATION";
            } else if (codigoError == 1452) { // MySQL foreign key violation error code
                error = "Dato no encontrado";
                errorType = "FOREIGN KEY VIOLATION";
            }

        }

        Map<String, String> errorResponseDetail = new HashMap<>();
        errorResponseDetail.put("Error", error);
        errorResponseDetail.put("Error type", errorType);

        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("Errors", errorResponseDetail);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("Error", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<Map<String, Object>> handleSecurityException(SecurityException e) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("Error", e.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }




}
