package ar.edu.ungs.billetera;

import java.util.*;

public class Empresa {
	private String CUIT;
	private String nombre;
	private Set<String> DNIAutorizados;
	
	public Empresa(String CUIT, String nombre) {
		this.CUIT = CUIT;
		this.nombre = nombre;
		this.DNIAutorizados = new HashSet<>();
	}
	
	public void agregarAutorizado(String DNI) {
		
	}

}
