package com.cardifteste;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cardifteste.domain.Cargo;
import com.cardifteste.domain.Departamento;
import com.cardifteste.domain.Funcionario;
import com.cardifteste.domain.FuncionarioDepartamento;
import com.cardifteste.repositories.CargoRepository;
import com.cardifteste.repositories.DepartamentoRepository;
import com.cardifteste.repositories.FuncionarioDepartamentoRepository;
import com.cardifteste.repositories.FuncionarioRepository;

@SpringBootApplication
public class TesteCardifApplication implements CommandLineRunner {

	@Autowired
	private CargoRepository cargoRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	@Autowired
	private FuncionarioDepartamentoRepository funcionarioDepartamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TesteCardifApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cargo cargo1 = new Cargo(null, "Analista de Sistemas Sênior");
		Cargo cargo2 = new Cargo(null, "Programador Plêno");
		
		
		Funcionario func1 = new Funcionario(null, "Marcos Vinicius Gomes", 23, LocalDate.now(), "38.000.016-7", cargo1);
		
		cargo1.getFuncionarios().addAll(Arrays.asList(func1));
		
		cargoRepository.saveAll(Arrays.asList(cargo1,cargo2));
		funcionarioRepository.saveAll(Arrays.asList(func1));
		
		Departamento depart = new Departamento(null, "TI");
		Departamento depart1 = new Departamento(null, "RH");
		
		departamentoRepository.saveAll(Arrays.asList(depart));
		departamentoRepository.saveAll(Arrays.asList(depart1));
		
		FuncionarioDepartamento funcDepart = new FuncionarioDepartamento(func1,depart,LocalDate.now());
		//FuncionarioDepartamento funcDepart1 = new FuncionarioDepartamento(func1,depart1,LocalDate.now());
		
		funcionarioDepartamentoRepository.saveAll(Arrays.asList(funcDepart));
		//funcionarioDepartamentoRepository.saveAll(Arrays.asList(funcDepart1));				
	}
}