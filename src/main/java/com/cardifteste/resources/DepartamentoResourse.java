package com.cardifteste.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cardifteste.domain.Departamento;
import com.cardifteste.dto.DepartamentoDTO;
import com.cardifteste.services.DepartamentoService;

@RestController
@RequestMapping(value = "/departamentos")
public class DepartamentoResourse {

	@Autowired
	private DepartamentoService departamentoService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Departamento> find(@PathVariable Integer id) {
		Departamento obj = departamentoService.find(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<DepartamentoDTO>> findAll() {
		List<Departamento> list = departamentoService.findAll();
		List<DepartamentoDTO> listDto = list.stream().map(obj -> new DepartamentoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Departamento obj) {
		obj = departamentoService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Departamento obj, @PathVariable Integer id) {
		obj.setId(id);
		obj = departamentoService.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		departamentoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
