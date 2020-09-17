package com.maykon.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.maykon.apirest.domain.Patrimonio;
@Repository
public interface PatrimonioRepository extends JpaRepository<Patrimonio,Integer> {

	// Otimiza a consulta para retorno rapido
	@Transactional(readOnly=true)
	Patrimonio findByNome(String Nome);

}
