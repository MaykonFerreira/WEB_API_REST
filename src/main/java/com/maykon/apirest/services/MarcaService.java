package com.maykon.apirest.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maykon.apirest.domain.Marca;
import com.maykon.apirest.dto.MarcaDTO;
import com.maykon.apirest.repositories.MarcaRepository;
import com.maykon.apirest.services.exceptions.DataIntegrityException;

@Service
public class MarcaService {

	
	@Autowired
	private MarcaRepository repo;

	public Marca buscar(Integer id) {
		

		Optional<Marca> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Marca.class.getName(), null));		

	}	
	

	public Marca findByNome(String nome){
		Marca obj = repo.findByNome(nome);
		return obj;
	}	

	@Transactional
	public Marca inserir(Marca obj) {
		
		obj = repo.save(obj);
		return obj;
	}
	@Transactional
	public Marca update(Marca obj) {
		//obj.setId(null);
		Marca newObj = buscar(obj.getMarcaId());
		updateData(newObj,obj);
		return repo.save(newObj);
	}

	@Transactional
	private void  updateData(Marca newObj,Marca obj) {
		newObj.setNome(obj.getNome());
	}	
	@Transactional
	public void delete(Integer id) {
		buscar(id);
		try {
			repo.deleteById(id);
		}
		
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque a há pedidos relacionados");
		}
		
	}	
	public Marca fromDTO(MarcaDTO objDto) {
		System.out.println(objDto);
		return new Marca(objDto.getMarcaId(),objDto.getNome());
		//throw new UnsupportedAddressTypeException();
		
	}			
	public List<Marca> findAll(){
		return repo.findAll();
	}
}
