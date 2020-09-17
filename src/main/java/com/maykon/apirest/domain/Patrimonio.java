package com.maykon.apirest.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Patrimonio implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	//@ManyToOne
	//@JoinColumn(name="marca_id")
	private Integer marcaId;
	private String descricao;
	
	public Patrimonio() {
		
	}


	public Patrimonio(Integer id, String nome, Integer marcaId, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.marcaId = marcaId;
		this.descricao = descricao;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Patrimonio other = (Patrimonio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Patrimonio [Id=" + id + ", Nome=" + nome + ", MarcaId=" + marcaId + ", Descricao=" + descricao + "]";
	}

}
