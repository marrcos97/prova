package com.cardifteste.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.cardifteste.domain.Funcionario;
import com.cardifteste.domain.FuncionarioDepartamento;

public interface FuncionarioDepartamentoRepository extends JpaRepository<FuncionarioDepartamento, Integer>{

	
	@Transactional(readOnly=true)
	Page<FuncionarioDepartamento> findByFuncionario(Funcionario funcionario, Pageable pageRequest);
}
