package com.nttdata.bootcamp.webfluxmicro.controllers;

import java.time.Duration;
import java.util.ArrayList;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nttdata.bootcamp.webfluxmicro.clases.Persona;

import reactor.core.publisher.Flux;

@RestController
public class PersonListController {
	
	private ArrayList<Persona> personaList = new ArrayList<Persona>();
	
	
	public PersonListController() {
		personaList.add(new Persona("Juan", "jmd18","123456"));
		personaList.add(new Persona("Ana", "amd18","123456"));
		personaList.add(new Persona("Pepe", "pmd18","123456"));
	}
	
	@GetMapping("/login")
	public String login(@RequestParam(value = "user") String username, @RequestParam(value = "password") String password) {
		String retorno = "";
		try {
			var resultado = personaList.stream().filter(
			u -> u.getUsername().equals(username) && u.getPassword().equals(password)
			).collect(Collectors.toList()).get(0);
			retorno = "Hola " + resultado.getNombre();
		}catch(Exception e){
			retorno = "Usuario no existe";	
		}
		return retorno;
	}
	
	@GetMapping("/add")
	public String add(@RequestParam(value = "nombre") String nombre, @RequestParam(value = "user") String username, @RequestParam(value = "password") String password) {
		String retorno = "";
		try {
		var resultado = personaList.stream().filter(
		u -> u.getUsername().equals(username) && u.getPassword().equals(password)
		).collect(Collectors.toList()).get(0);
		retorno = "El usuario ya existe.";
		}catch(Exception e){
			personaList.add(new Persona(nombre,username,password));
			retorno = "Usuario "+username+" añadido.";
			
		}
		

		return retorno;	
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam(value = "user") String username) {
		String retorno = "";
		var resultado = personaList.stream().filter(
		u -> u.getUsername().equals(username)
		).collect(Collectors.toList()).get(0);
		
		if(resultado == null) {
			retorno = "El usuario no existe";
		}else {
			personaList.remove(resultado);
			retorno = "Usuario eliminado.";
		}
		
		return retorno;
		
	}
	
	@GetMapping(path = "/personlist") 
	public Flux<Persona> numbers() { 
		
		Flux<Persona> flux = Flux.fromIterable(personaList).delayElements(Duration.ofSeconds(1));
		
		flux.subscribe(p -> p.print(p)); // Suscriptor 1

		return flux; // Suscriptor 3

	}
	
	
}
