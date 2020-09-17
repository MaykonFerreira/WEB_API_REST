package com.maykon.apirest.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Marca implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer marcaId;
	private String nome;
	
	public Marca() {
		
	}

	public Marca(Integer marcaId, String nome) {
		super();
		this.marcaId = marcaId;
		this.nome = nome;
	}

	public Integer getMarcaId() {
		return marcaId;
	}

	public void setMarcaId(Integer marcaId) {
		this.marcaId = marcaId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((marcaId == null) ? 0 : marcaId.hashCode());
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
		Marca other = (Marca) obj;
		if (marcaId == null) {
			if (other.marcaId != null)
				return false;
		} else if (!marcaId.equals(other.marcaId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Marca [marcaId=" + marcaId + ", nome=" + nome + "]";
	}

	
	
}
