package com.cardifteste.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class FuncionarioDepartamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private FuncionarioDepartamentoPK id = new FuncionarioDepartamentoPK();
	
	private LocalDate dataCadastro;

	public FuncionarioDepartamento() {
		super();
	}

	public FuncionarioDepartamento(Funcionario funcionario, Departamento departamento) {
		super();
		id.setFuncionario(funcionario);
		id.setDepartamento(departamento);
	}
	
	public FuncionarioDepartamento(Funcionario funcionario, Departamento departamento, LocalDate dataCadastro) {
		super();
		id.setFuncionario(funcionario);
		id.setDepartamento(departamento);
		this.dataCadastro = dataCadastro;
	}

	public Funcionario getFuncionario() {
		return id.getFuncionario();
	}
	
	public Departamento getDepartamento() {
		return id.getDepartamento();
	}
	
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadrastro) {
		this.dataCadastro = dataCadrastro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FuncionarioDepartamento other = (FuncionarioDepartamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
