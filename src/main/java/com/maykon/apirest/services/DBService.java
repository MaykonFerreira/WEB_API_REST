package com.maykon.apirest.services;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.maykon.apirest.domain.Marca;
import com.maykon.apirest.domain.Patrimonio;
import com.maykon.apirest.repositories.MarcaRepository;
import com.maykon.apirest.repositories.PatrimonioRepository;
import com.maykon.apirest.repositories.UsuarioRepository;

//@Service
@Service("userDetailsService")
public class DBService {

	//@Autowired
	//private BCryptPasswordEncoder pe;
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	@Autowired
	private PatrimonioRepository patrimonioRepository ;	
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	

	
	
	public void instantiateTestDatabase() throws ParseException {
	
				
		Marca mar1 = new Marca(null,"HP");
		Marca mar2 = new Marca(null,"Epson");
		Marca mar3 = new Marca(null,"IBM");
		
		Patrimonio pat1 = new Patrimonio(null,"Computador",null,"DeskTop Core I5");
		Patrimonio pat2 = new Patrimonio(null,"Impressora",null,"Multifuncional");
		Patrimonio pat3 = new Patrimonio(null,"Mouse",null,"Sem Fio");
		Patrimonio pat4 = new Patrimonio(null,"Teclado",null,"Sem fio");
		Patrimonio pat5 = new Patrimonio(null,"Notebook",null,"Core I7");
		Patrimonio pat6 = new Patrimonio(null,"Servidor",null,"Load Balance");
		
		marcaRepository.saveAll(Arrays.asList(mar1,mar2,mar3));
		pat1.setMarcaId(mar1.getMarcaId());
		pat2.setMarcaId(mar2.getMarcaId());
		pat3.setMarcaId(mar1.getMarcaId());
		pat4.setMarcaId(mar1.getMarcaId());
		pat5.setMarcaId(mar1.getMarcaId());
		pat6.setMarcaId(mar3.getMarcaId());
		
		
		patrimonioRepository.saveAll(Arrays.asList(pat1,pat2,pat3,pat4,pat5,pat6));
		

		//Usuario user1 = new Usuario(null,"Maykon Ferreira","lmaykonf@gmail.com",pe.encode("vitoria"));
		//usuarioRepository.save(user1);
		
		
	}

}
