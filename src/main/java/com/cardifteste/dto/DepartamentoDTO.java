package com.cardifteste.dto;

import java.io.Serializable;

import com.cardifteste.domain.Departamento;

public class DepartamentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	public DepartamentoDTO() {
	}
	
	public DepartamentoDTO(Departamento departamento) {
		id = departamento.getId();
		name = departamento.getName();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
