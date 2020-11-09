package com.cardifteste.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cardifteste.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{

}
