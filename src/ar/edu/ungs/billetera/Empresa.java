package ar.edu.ungs.billetera;

import java.util.*;

public class Empresa {
	private String CUIT;
	private String nombre;
	private String telefono;
	private String email;
	private String nombreContacto;
	
	private Set<String> DNIAutorizados;
	
	public Empresa(String CUIT, String nombre, String telefono, String email, String nombreContacto) {
		// IREP
		if (CUIT == null || nombre == null || telefono == null || email == null || nombreContacto == null) {
	        throw new IllegalArgumentException("Ningun dato de la empresa puede ser nulo.");
	    }
		
		if(CUIT.length() != 11 || nombre.length() < 3 || telefono.length() != 10 || !email.contains("@") || nombreContacto.length() < 3) {
			throw new IllegalArgumentException("Para crear una empresa: "
					+ "\n - El CUIT debe tener 11 digitos."
					+ "\n - El nombre debe tener mas de 2 letras."
					+ "\n - El email debe ser valido."
					+ "\n - El nombre de contacto debe tener mas de 2 letras.");
		}
		
		this.CUIT = CUIT;
		this.nombre = nombre;
		this.telefono = telefono;
		this.email = email;
		this.nombreContacto = nombreContacto;
		this.DNIAutorizados = new HashSet<>();
	}
	
	public void agregarAutorizado(String DNI) {
		// VALIDACIONES
		if(DNI == null || DNI.isEmpty() || DNI.length() > 8 || DNI.length() < 7){
			throw new IllegalArgumentException("El DNI no puede ser nulo, vacio ni ser menor a 7 y mayor a 8");
		}
		
		if(estaAutorizado(DNI)) {
			throw new IllegalArgumentException("La persona con DNI:" + DNI +" no esta autorizado");
		}
		DNIAutorizados.add(DNI);
	}
	
	public boolean estaAutorizado(String DNI) {
		// VALIDACIONES
		if(DNI == null || DNI.isEmpty() || DNI.length() > 8 || DNI.length() < 7){
			throw new IllegalArgumentException("El DNI no puede ser nulo, vacio ni ser menor a 7 y mayor a 8");
		}
		
		return DNIAutorizados.contains(DNI);
	}
}
