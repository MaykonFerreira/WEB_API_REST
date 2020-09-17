package com.maykon.apirest.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.maykon.apirest.domain.Patrimonio;
import com.maykon.apirest.dto.PatrimonioDTO;
import com.maykon.apirest.repositories.PatrimonioRepository;

public class PatrimonioInsertValidator implements ConstraintValidator<PatrimonioInsert, PatrimonioDTO> {
	
	
	@Autowired
	private PatrimonioRepository repo;
	
	@Override
	public void initialize(PatrimonioInsert ann) {
	}
	
	

	@Override
	public boolean isValid(PatrimonioDTO objDto, ConstraintValidatorContext context) {
		
		
		
		List<FieldMessage> list = new ArrayList<>();
		
		Patrimonio aux = repo.findByNome(objDto.getNome());
		
		if (aux!= null) {
			list.add(new FieldMessage("Marca","Marca Já existente"));
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
