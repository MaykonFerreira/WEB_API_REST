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

import com.maykon.apirest.domain.Marca;
import com.maykon.apirest.dto.MarcaDTO;
import com.maykon.apirest.services.MarcaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/marca")
public class MarcaResource {

	
	@Autowired
	private MarcaService service;
	
	
	@RequestMapping(value ="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Marca> find(@PathVariable Integer id) {
		
		Marca obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);

	}

	@ApiOperation(value="Listar Todas as Marcas")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<MarcaDTO>> Listar() {

		List<Marca> list = service.findAll();
		List<MarcaDTO> listDTO = list.stream().map(obj -> new MarcaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

	}

	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody MarcaDTO objDto) {
		Marca obj = service.fromDTO(objDto);
		obj = service.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getMarcaId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Marca obj, @PathVariable Integer id) {
		
		obj.setMarcaId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		service.delete(id);
		return ResponseEntity.noContent().build();
	}



}
