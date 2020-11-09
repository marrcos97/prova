package com.cardifteste.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cardifteste.domain.Funcionario;
import com.cardifteste.repositories.FuncionarioRepository;
import com.cardifteste.services.exceptions.ObjectNotFoundException;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository repository;
	
	public Funcionario find(Integer id) {
		Optional<Funcionario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Funcionário não encontrado! Id: "+ id +", Tipo: "+ Funcionario.class.getName()));
	}
	
	public Funcionario insert(Funcionario obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Funcionario update(Funcionario obj) {
		Funcionario newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		repository.deleteById(id);
//		try {
//		} catch (DataIntegrityViolationException e) {
//			throw new DataIntegrityException("Não é possível excluir um funcionárop que tenha funcionários");
//		}
	}

	public List<Funcionario> findAll() {
		return repository.findAll();
	}
	
	private void updateData(Funcionario newObj, Funcionario obj) {
		newObj.setName(obj.getName());
	}

}
