package com.cardifteste.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cardifteste.domain.Funcionario;
import com.cardifteste.services.CargoService;
import com.cardifteste.services.DepartamentoService;
import com.cardifteste.services.FuncionarioDepartamentoService;
import com.cardifteste.services.FuncionarioService;

@Controller
public class FuncionarioController {

	@Autowired
	FuncionarioService funcionarioService;

	@Autowired
	CargoService cargoService;

	@Autowired
	DepartamentoService departamentoService;

	@Autowired
	FuncionarioDepartamentoService funcionarioDepartamentoService;

	@GetMapping("/signup")
	public String showFuncionarioForm(Funcionario funcionario, Model model) {
		model.addAttribute("cargos", cargoService.findAll());
		model.addAttribute("departamentos", departamentoService.findAll());
		return "add-funcionario";
	}

	@PostMapping("/addFuncionario")
	public String addFuncionario(@RequestParam(value = "selectDepart") Integer idDepartamento, @Valid Funcionario obj, 
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "add-funcionario";
		}

		Funcionario funcionario = funcionarioService.insert(obj);
		funcionarioDepartamentoService.insert(funcionario,departamentoService.find(idDepartamento)); 
		model.addAttribute("funcionarios", funcionarioService.findAll());
		return "index";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Funcionario obj = funcionarioService.find(id);

		model.addAttribute("funcionario", obj);
		model.addAttribute("cargos", cargoService.findAll());
		model.addAttribute("departamentos", departamentoService.findAll());
		return "update-funcionario";
	}

	@PostMapping("/update/{id}")
	public String updateFuncionario(@RequestParam(value = "selectDepart") Integer idDepartamento, @PathVariable("id") Integer id, @Valid Funcionario obj, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			obj.setId(id);
			model.addAttribute("cargos", cargoService.findAll());
			model.addAttribute("departamentos", departamentoService.findAll());
			return "update-funcionario";
		}

		Funcionario funcionario = funcionarioService.update(obj);
		funcionarioDepartamentoService.insert(funcionario,departamentoService.find(idDepartamento)); 
		model.addAttribute("funcionarios", funcionarioService.findAll());
		return "index";
	}
 
	@GetMapping("/delete/{id}")
	public String deleteFuncionario(@PathVariable("id") Integer id, Model model) {
		funcionarioDepartamentoService.deleteByIdFuncionario(id);
		funcionarioService.delete(id);
		model.addAttribute("funcionarios", funcionarioService.findAll());
		return "index";
	}
}
