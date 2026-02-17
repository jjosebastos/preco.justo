package com.br.preco.justo.exception;

import com.br.preco.justo.dto.StandardError;
import com.br.preco.justo.model.Pato;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler(CpfAlreadyRegistered.class)
    public ResponseEntity<StandardError> cpfAlreadyRegistered(CpfAlreadyRegistered e,
                                                              HttpServletRequest request){

        HttpStatus status = HttpStatus.CONFLICT;
        String error = "Conflito de dados";

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<StandardError> clienteNotFoundException(ClienteNotFoundException e,
                                                                  HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        String error = "Dados não encontrados";

        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(PatoNotFoundException.class)
    public ResponseEntity<StandardError> patoNotFoundException(PatoNotFoundException e,
                                                                HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        String error = "Dados não encontrados";
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }
}
