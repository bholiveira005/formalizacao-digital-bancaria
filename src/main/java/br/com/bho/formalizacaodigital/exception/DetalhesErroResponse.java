package br.com.bho.formalizacaodigital.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetalhesErroResponse implements Serializable {

	private String titulo;
	private Integer status;
	private String nomeObjeto;
	private List<DetalhesErroObjeto> erroCampos;
}
