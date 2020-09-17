package com.maykon.apirest.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.maykon.apirest.domain.Usuario;
import com.maykon.apirest.dto.UsuarioDTO;
import com.maykon.apirest.repositories.UsuarioRepository;
import com.maykon.apirest.services.exceptions.DataIntegrityException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repo;
	@Autowired
	private BCryptPasswordEncoder pe;
	

	public Usuario buscar(Integer id) {
		

		Optional<Usuario> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName(), null));		

	}	
	

	public Usuario findByEmail(String Email){
		
		
		Usuario obj = repo.findByEmail(Email);
		if (obj == null) {
			//throw new ObjectNotFoundException("Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Usuario.class.getName());
			throw new ObjectNotFoundException(obj,"Maykon" +  Email);
		}
		return obj;
	}
	
	@Transactional
	public Usuario inserir(Usuario obj) {
		//System.out.println(obj);
		obj = repo.save(obj);
		return obj;
	}
	
	public Usuario update(Usuario obj) {
		//obj.setId(null);
		Usuario newObj = buscar(obj.getId());
		updateData(newObj,obj);
		return repo.save(newObj);
	}

	private void  updateData(Usuario newObj,Usuario obj) {
		newObj.setNome(obj.getNome());
	}	
	public Usuario fromDTO(UsuarioDTO objDto) {
		
		return new Usuario(objDto.getId(),objDto.getNome(),objDto.getEmail(),pe.encode(objDto.getSenha()));
		//throw new UnsupportedAddressTypeException();
		
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
	public List<Usuario> findAll(){
		return repo.findAll();
	}
}
