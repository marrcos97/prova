package com.cardifteste.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.cardifteste.domain.Funcionario;

public class FuncionarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private Integer age;

	private LocalDate birthday;

	private String document;

	public FuncionarioDTO() {
	}

	public FuncionarioDTO(Funcionario funcionario) {
		id = funcionario.getId();
		name = funcionario.getName();
		age = funcionario.getAge();
		birthday = funcionario.getBirthday();
		document = funcionario.getDocument();
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

}
