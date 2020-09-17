package com.maykon.apirest.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maykon.apirest.domain.Patrimonio;
import com.maykon.apirest.repositories.PatrimonioRepository;
import com.maykon.apirest.services.exceptions.DataIntegrityException;

@Service
public class PatrimonioService {

	@Autowired
	private PatrimonioRepository repo;

	public Patrimonio buscar(Integer id) {
		

		Optional<Patrimonio> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Patrimonio.class.getName(), null));		

	}	
	
	public Patrimonio findByNome(String Nome){
		
	
		Patrimonio obj = repo.findByNome(Nome);
		if (obj == null) {
			//throw new ObjectNotFoundException("Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Patrimonio.class.getName());
			throw new ObjectNotFoundException(obj, Nome);
		}
		return obj;
	}

	@Transactional
	public Patrimonio inserir(Patrimonio obj) {
		obj = repo.save(obj);
		return obj;
	}
	
	public Patrimonio update(Patrimonio obj) {
		//obj.setId(null);
		Patrimonio newObj = buscar(obj.getId());
		updateData(newObj,obj);
		return repo.save(newObj);
	}

	private void  updateData(Patrimonio newObj,Patrimonio obj) {
		newObj.setNome(obj.getNome());
	}	
	public void delete(Integer id) {
		buscar(id);
		try {
			repo.deleteById(id);
		}
		
		catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque a há pedidos relacionados");
		}
		
	}	
	public List<Patrimonio> findAll(){
		return repo.findAll();
	}
}
