package com.cardifteste.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cardifteste.domain.Cargo;
import com.cardifteste.repositories.CargoRepository;
import com.cardifteste.services.exceptions.DataIntegrityException;
import com.cardifteste.services.exceptions.ObjectNotFoundException;

@Service
public class CargoService {

	@Autowired
	private CargoRepository repository;
	
	public Cargo find(Integer id) {
		Optional<Cargo> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Cargo não encontrado! Id: "+ id +", Tipo: "+ Cargo.class.getName()));
	}
	
	public Cargo insert(Cargo obj) {
		obj.setId(null);
		return repository.save(obj);
	}

	public Cargo update(Cargo obj) {
		Cargo newObj = find(obj.getId());
		updateData(newObj, obj);
		return repository.save(obj);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um cargo que tem funcionários");
		}
	}

	public List<Cargo> findAll() {
		return repository.findAll();
	}
	
	private void updateData(Cargo newObj, Cargo obj) {
		newObj.setName(obj.getName());
	}
}
