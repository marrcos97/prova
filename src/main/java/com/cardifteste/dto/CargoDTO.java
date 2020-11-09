package com.cardifteste.dto;

import java.io.Serializable;

import com.cardifteste.domain.Cargo;

public class CargoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	public CargoDTO() {
	}
	
	public CargoDTO(Cargo cargo) {
		id = cargo.getId();
		name = cargo.getName();
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
