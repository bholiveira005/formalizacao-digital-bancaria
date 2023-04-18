package br.com.bho.formalizacaodigital.exception;

public class ErroGeral403 extends RuntimeException{

    public ErroGeral403(String message) {
        super(message);
    }

    public ErroGeral403(String message, Throwable cause) {
        super(message, cause);
    }
}
