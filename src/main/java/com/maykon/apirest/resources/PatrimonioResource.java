package com.maykon.apirest.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.maykon.apirest.domain.Patrimonio;
import com.maykon.apirest.dto.PatrimonioDTO;
import com.maykon.apirest.services.PatrimonioService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/patrimonio")
public class PatrimonioResource {

	
	@Autowired
	private PatrimonioService service;
	
	
	@RequestMapping(value ="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Patrimonio> find(@PathVariable Integer id) {
		
		Patrimonio obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);

	}
	@ApiOperation(value="Listar Todos Patrimonios")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<PatrimonioDTO>> Listar() {

		List<Patrimonio> list = service.findAll();
		List<PatrimonioDTO> listDTO = list.stream().map(obj -> new PatrimonioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Patrimonio obj) {
		obj = service.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Patrimonio obj, @PathVariable Integer id) {
		
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		service.delete(id);
		return ResponseEntity.noContent().build();
	}



}
