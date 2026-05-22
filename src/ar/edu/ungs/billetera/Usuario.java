package ar.edu.ungs.billetera;
import java.util.HashMap;
import java.util.Map;

public class Usuario {
	protected String DNI;
	protected String nombreYApellido;
	protected String telefono;
	protected String email;
	protected double totalInvertido;
	protected Map <String, Cuenta> misCuentas;
	
	public Usuario(String DNI, String nombreYApellido, String telefono, String email) {
		this.DNI = DNI;
		this.nombreYApellido = nombreYApellido;
		this.telefono = telefono;
		this.email = email;
		this.totalInvertido = 0.0;
		this.misCuentas = new HashMap<>();
	}

	public void agregarCuenta(Cuenta nueva) {
		
		
	}
	
	public double obtenerTotalInvertido() {
		
		
		return totalInvertido;
	}
	
	public void actualizarTotalInvertido(double monto) {
		
		
	}
	
	public void consultarMovimientos() {
		
		
	}
	
	public String toString() {
		
		
		return "";
	}
}
