package com.maykon.apirest.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.maykon.apirest.domain.Marca;
import com.maykon.apirest.dto.MarcaDTO;
import com.maykon.apirest.repositories.MarcaRepository;

public class MarcaInsertValidator implements ConstraintValidator<MarcaInsert, MarcaDTO> {
	
	
	@Autowired
	private MarcaRepository repo;
	
	@Override
	public void initialize(MarcaInsert ann) {
	}

	@Override
	public boolean isValid(MarcaDTO objDto, ConstraintValidatorContext context) {
		System.out.println("Teste");
		List<FieldMessage> list = new ArrayList<>();
		Marca aux = repo.findByNome(objDto.getNome());
		
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