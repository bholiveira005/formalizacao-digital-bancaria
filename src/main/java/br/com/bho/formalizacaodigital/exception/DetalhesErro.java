package br.com.bho.formalizacaodigital.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DetalhesErro {
	
	private String titulo;
	
	private Integer status;
	
	private LocalDateTime timestamp;

}
