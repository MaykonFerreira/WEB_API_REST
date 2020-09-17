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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.maykon.apirest.domain.Usuario;
import com.maykon.apirest.dto.UsuarioDTO;
import com.maykon.apirest.services.UsuarioService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/usuario")
public class UsuarioResource {

	
	@Autowired
	private UsuarioService service;
	
	
	@RequestMapping(value ="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Usuario> find(@PathVariable Integer id) {
		
		Usuario obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);

	}

	@RequestMapping(value="/Email", method=RequestMethod.GET)
	public ResponseEntity<Usuario> find(@RequestParam(value="value") String Email) {
		Usuario obj = service.findByEmail(Email);
		return ResponseEntity.ok().body(obj);
	}
	@ApiOperation(value="Listar Todas as Marcas")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> Listar() {

		List<Usuario> list = service.findAll();
		List<UsuarioDTO> listDTO = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody UsuarioDTO objDto) {
		Usuario obj = service.fromDTO(objDto);
		obj = service.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Usuario obj, @PathVariable Integer id) {
		
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
