package br.com.builders.domain.model;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Cliente {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	@Column(name = "data_nascimento")
	private String dataNascimento;
	
	@Transient
	private int idade;
	
	public int getIdade() {
		return Period.between(LocalDate.parse(this.dataNascimento), LocalDate.now()).getYears();
	}
	
}
