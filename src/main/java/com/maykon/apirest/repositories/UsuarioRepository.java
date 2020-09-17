package com.maykon.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.maykon.apirest.domain.Usuario;
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Integer> {

	// Otimiza a consulta para retorno rapido
	//@Transactional(readOnly=true)
	//Usuario findByUsuario(String nome);

	Usuario findByEmail(String email);

}
