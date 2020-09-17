package com.maykon.apirest.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.maykon.apirest.domain.Patrimonio;
import com.maykon.apirest.services.validation.PatrimonioInsert;

@PatrimonioInsert
public class PatrimonioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message="Preenchimento obrigatório")
	private String nome;
	@NotEmpty(message="Preenchimento obrigatório")
	private Integer marcaId;
	@NotEmpty(message="Preenchimento obrigatório")
	private String descricao;

	
	public PatrimonioDTO() {
		
	}
	public PatrimonioDTO(Patrimonio obj) {
		this.id = obj.getId();
		this.marcaId = obj.getMarcaId();
		this.descricao = obj.getDescricao();
		this.nome = obj.getNome();
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getMarcaId() {
		return marcaId;
	}
	public void setMarcaId(Integer marcaId) {
		this.marcaId = marcaId;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return "PatrimonioDTO [id=" + id + ", nome=" + nome + ", marcaId=" + marcaId + ", descricao=" + descricao + "]";
	}
	
	
}
