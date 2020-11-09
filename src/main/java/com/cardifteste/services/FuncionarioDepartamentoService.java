package com.cardifteste.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cardifteste.domain.Departamento;
import com.cardifteste.domain.Funcionario;
import com.cardifteste.domain.FuncionarioDepartamento;
import com.cardifteste.repositories.FuncionarioDepartamentoRepository;
import com.cardifteste.services.exceptions.DataIntegrityException;
import com.cardifteste.services.exceptions.ObjectNotFoundException;

@Service
public class FuncionarioDepartamentoService {

	@Autowired
	private FuncionarioDepartamentoRepository repository;

	public FuncionarioDepartamento find(Integer id) {
		Optional<FuncionarioDepartamento> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Funcionário não encontrado! Id: " + id + ", Tipo: " + FuncionarioDepartamento.class.getName()));
	}

	public void delete(Integer id) {
		find(id);
		repository.deleteById(id);
		try {
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir um funcionárop que tenha funcionários");
		}
	}

	public List<FuncionarioDepartamento> findAll() {
		return repository.findAll();
	}

	public FuncionarioDepartamento insert(Funcionario funcionario, Departamento departamento) {
		FuncionarioDepartamento funcionarioDepartamento = new FuncionarioDepartamento(funcionario, departamento,
				LocalDate.now());
		return repository.save(funcionarioDepartamento);
	}

	public Page<FuncionarioDepartamento> findByFuncionario(Funcionario obj) {
		Pageable pageRequest = Pageable.unpaged();
		return repository.findByFuncionario(obj, pageRequest);
	}

	public List<FuncionarioDepartamento> findHistoricoByIdFuncionario(Integer idFuncionario) {
		List<FuncionarioDepartamento> historico = new ArrayList<>();
		for (FuncionarioDepartamento funcionarioDepartamento : findAll()) {
			funcionarioDepartamento.getFuncionario().getId().compareTo(idFuncionario);
			if (funcionarioDepartamento.getFuncionario().getId() == (idFuncionario)) {
				historico.add(funcionarioDepartamento);
			}
		}
		return historico;
	}

	public void deleteByIdFuncionario(Integer idFuncionario) {
		repository.deleteAll(findHistoricoByIdFuncionario(idFuncionario));
	}
}
