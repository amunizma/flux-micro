package com.nttdata.bootcamp.webfluxmicro.clases;

import java.io.Serializable;

public class Persona implements Serializable {
	
	private String nombre;
	private String username;
	private String password;
	
	
	
	public Persona(String nombre, String username, String password) {
		super();
		this.nombre = nombre;
		this.username = username;
		this.password = password;
	}
	public Persona(String username, String password) {
		nombre = "nueva";
		this.username = username;
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void print(Persona p) {
		System.out.println(p.getNombre()+" "+p.getUsername());
	}
	
	
}
