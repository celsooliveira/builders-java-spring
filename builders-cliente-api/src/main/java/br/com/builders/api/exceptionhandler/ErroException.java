package br.com.builders.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class ErroException {

	private Integer status;
	private OffsetDateTime dataHora;
	private String titulo;
	private List<Atributo> atributos;
	
	@AllArgsConstructor
	@Getter
	public static class Atributo {
		
		private String nome;
		private String mensagem;
		
	}
}
