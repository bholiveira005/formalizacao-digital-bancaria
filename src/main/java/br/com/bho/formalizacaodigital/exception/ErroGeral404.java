package br.com.bho.formalizacaodigital.exception;

public class ErroGeral404 extends RuntimeException{

    public ErroGeral404(String message) {
        super(message);
    }

    public ErroGeral404(String message, Throwable cause) {
        super(message, cause);
    }
}
