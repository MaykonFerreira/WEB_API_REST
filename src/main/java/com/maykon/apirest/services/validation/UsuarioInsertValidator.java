package com.maykon.apirest.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.maykon.apirest.domain.Usuario;
import com.maykon.apirest.dto.UsuarioDTO;
import com.maykon.apirest.repositories.UsuarioRepository;

public class UsuarioInsertValidator implements ConstraintValidator<UsuarioInsert, UsuarioDTO> {
	
	
	@Autowired
	private UsuarioRepository repo;
	
	@Override
	public void initialize(UsuarioInsert ann) {
	}
	
	

	@Override
	public boolean isValid(UsuarioDTO objDto, ConstraintValidatorContext context) {
		
		
		
		List<FieldMessage> list = new ArrayList<>();
		
		Usuario aux = repo.findByEmail(objDto.getEmail());
		
		if (aux!= null) {
			list.add(new FieldMessage("email","Email Já existente"));
		}
		
		// inclua os testes aqui, inserindo erros na lista
		// Aqui cria uma lista de erro no FrameWork
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
