package br.com.bho.formalizacaodigital.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalhesErroObjeto implements Serializable {

    private String campo;
    private Object valor;
    private String mensagem;
}
