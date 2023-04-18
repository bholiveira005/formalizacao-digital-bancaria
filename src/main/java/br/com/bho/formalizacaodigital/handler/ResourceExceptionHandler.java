package br.com.bho.formalizacaodigital.handler;

import br.com.bho.formalizacaodigital.exception.DetalhesErro;
import br.com.bho.formalizacaodigital.exception.ErroGeral403;
import br.com.bho.formalizacaodigital.exception.ErroGeral404;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ErroGeral403.class)
    public ResponseEntity<DetalhesErro> handlerErro403Exception(ErroGeral403 e) {
        DetalhesErro erro = new DetalhesErro(e.getMessage(), 403, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(ErroGeral404.class)
    public ResponseEntity<DetalhesErro> handlerErro404Exception(ErroGeral404 e) {
        DetalhesErro erro = new DetalhesErro(e.getMessage(), 404, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}
