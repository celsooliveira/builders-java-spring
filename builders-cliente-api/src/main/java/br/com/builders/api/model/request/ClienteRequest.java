package br.com.builders.api.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClienteRequest {

	@NotBlank
	@Size(max = 55)
	private String nome;
	
	@NotBlank
	@Email
	@Size(max = 100)
	private String email;
	
	@NotBlank
	@Size(max = 20)
	private String telefone;
	
	@NotBlank
	private String dataNascimento;
	
}
