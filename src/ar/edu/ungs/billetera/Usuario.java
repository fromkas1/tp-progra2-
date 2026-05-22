package ar.edu.ungs.billetera;

import java.util.List;

public class Usuario {
	
	public Usuario(String dni, String nombreyApellido, List<Cuenta> miscuentas, double totalinvertido) {
		
		this.dni = dni;
		this.nombreyApellido = nombreyApellido;
		this.miscuentas = miscuentas;
		this.totalinvertido = totalinvertido;
	}
	private String dni;
	private String nombreyApellido;
	private List<Cuenta> miscuentas;
	private double totalinvertido;
	

	public void agregarCuenta(Cuenta nueva) {}
	
	public float obtenerTotalInvertido() {}
	
	public void ActualizarTotalInvertido(double monto) {}
	
	public void consultarMoviento() {}
	
}
