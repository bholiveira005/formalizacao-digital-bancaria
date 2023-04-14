package br.com.bho.formalizacaodigital.handler;

import br.com.bho.formalizacaodigital.exception.DetalhesErro;
import br.com.bho.formalizacaodigital.exception.ErroGeral;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ErroGeral.class)
    public ResponseEntity<DetalhesErro> handlerErroGeralException(ErroGeral e) {
        DetalhesErro erro = new DetalhesErro(e.getMessage(), 404, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}
