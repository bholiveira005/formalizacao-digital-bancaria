package br.com.bho.formalizacaodigital.handler;

import br.com.bho.formalizacaodigital.exception.DetalhesErroObjeto;
import br.com.bho.formalizacaodigital.exception.DetalhesErroResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        List<DetalhesErroObjeto> erros = getErros(ex);
        DetalhesErroResponse erroResponse = getErroResponse(ex, status, erros);
        return ResponseEntity.status(erroResponse.getStatus()).body(erroResponse);
    }

    private DetalhesErroResponse getErroResponse(MethodArgumentNotValidException ex, HttpStatus status, List<DetalhesErroObjeto> erros) {
        return new DetalhesErroResponse("A requisição possui os seguintes campos inválidos",
                status.value(), ex.getBindingResult().getObjectName(), erros);
    }

    private List<DetalhesErroObjeto> getErros(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(erro -> new DetalhesErroObjeto(erro.getField(), erro.getRejectedValue(), iniciarMsg(erro.getDefaultMessage())))
                .collect(Collectors.toList());
    }

    private String iniciarMsg(String msgErro) {
        String msgCampo = "O campo ".concat(msgErro);
        return !msgErro.contains("Quantidade") ? msgCampo : msgErro;
    }
}
