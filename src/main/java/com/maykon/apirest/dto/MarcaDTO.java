package com.maykon.apirest.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.maykon.apirest.domain.Marca;
import com.maykon.apirest.services.validation.MarcaInsert;

@MarcaInsert
public class MarcaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer marcaId;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=3, max=50, message="O tamanho deve ser entre 3 e 50 caracteres")
	private String nome;
	

	public MarcaDTO() {
		
	}
	public MarcaDTO(Marca obj) {
		marcaId = obj.getMarcaId();
		nome = obj.getNome();
	}

	public Integer getMarcaId() {
		return marcaId;
	}

	public void setId(Integer marcaId) {
		this.marcaId = marcaId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
