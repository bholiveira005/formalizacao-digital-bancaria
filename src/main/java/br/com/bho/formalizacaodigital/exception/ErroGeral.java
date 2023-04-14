package br.com.bho.formalizacaodigital.exception;

public class ErroGeral extends RuntimeException{

    public ErroGeral(String message) {
        super(message);
    }

    public ErroGeral(String message, Throwable cause) {
        super(message, cause);
    }
}
