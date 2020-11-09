package com.cardifteste.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cardifteste.domain.Departamento;
import com.cardifteste.repositories.DepartamentoRepository;
import com.cardifteste.services.exceptions.DataIntegrityException;
import com.cardifteste.services.exceptions.ObjectNotFoundException;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentoRepository repository;
	
	public Departamento find(Integer id) {
		Optional<Departamento> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Departamento não encontrado! Id: "+ id +", Tipo: "+ Departamento.class.getName()));
	}
	
	public Departamento insert(Departamento obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Departamento update(Departamento obj) {
		Departamento newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um departamento que tem funcionários");
		}
	}

	public List<Departamento> findAll() {
		return repository.findAll();
	}
	
	private void updateData(Departamento newObj, Departamento obj) {
		newObj.setName(obj.getName());
	}

	public Departamento updateChefe(Integer idFuncionario, Integer idDepartamento) {
		Departamento departamento = find(idDepartamento);
		departamento.setIdChefe(idFuncionario);
		return update(departamento);
	}

}
