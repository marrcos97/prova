package com.cardifteste.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cardifteste.domain.Departamento;
import com.cardifteste.domain.Funcionario;
import com.cardifteste.domain.FuncionarioDepartamento;
import com.cardifteste.services.CargoService;
import com.cardifteste.services.DepartamentoService;
import com.cardifteste.services.FuncionarioDepartamentoService;
import com.cardifteste.services.FuncionarioService;

@Controller
public class DepartamentoController {

	@Autowired
	FuncionarioService funcionarioService;

	@Autowired
	CargoService cargoService;

	@Autowired
	DepartamentoService departamentoService;

	@Autowired
	FuncionarioDepartamentoService funcionarioDepartamentoService;

	@GetMapping("/departamento")
	public String showDepartamentosForm(Model model) {
		model.addAttribute("departamentos", departamentoService.findAll());

		return "departamentos";
	}

	@GetMapping("/funcionariosDepartamento/{id}")
	public String funcionariosDepartamento(@PathVariable("id") Integer id, Model model) {
		Departamento obj = departamentoService.find(id);

		List<Funcionario> funcionarios = new ArrayList<>();
		for (FuncionarioDepartamento funcionarioDepartamento : obj.getFuncionarios()) {
			funcionarios.add(funcionarioService.find(funcionarioDepartamento.getFuncionario().getId()));
		}
		model.addAttribute("departamento", obj);
		model.addAttribute("funcionarios", funcionarios);
		return "funcionario-departamento";
	}

	@GetMapping("/setarChefe/{idFunc}/{idDepart}")
	public String setarChefe(@PathVariable("idFunc") Integer idFuncionario,
			@PathVariable("idDepart") Integer idDepartamento, Model model) {
		departamentoService.updateChefe(idFuncionario, idDepartamento);
		model.addAttribute("departamentos", departamentoService.findAll());
		return "departamentos";
	}

	@GetMapping("/alterarDepartamento/{idFunc}")
	public String alterarDepartamento(@PathVariable("idFunc") Integer id, Model model) {
		Funcionario obj = funcionarioService.find(id);

		model.addAttribute("funcionario", obj);
		model.addAttribute("cargos", cargoService.findAll());
		model.addAttribute("departamentos", departamentoService.findAll());
		return "update-funcionario";
	}

	@GetMapping("/historicoDepartamentos/{idFunc}")
	public String historicoDepartamentos(@PathVariable("idFunc") Integer id, Model model) {
		List<FuncionarioDepartamento> historico = funcionarioDepartamentoService.findHistoricoByIdFuncionario(id);
		
		model.addAttribute("historico", historico);
		return "historico-funcionario-departamento";
	}

}
