package ar.edu.ungs.billetera;

import java.util.ArrayList;
import java.util.List;

public abstract class Cuenta {
	protected String CVU;
	protected String alias;
	protected double saldo;
	protected String DNIPropietario;
	protected List <Actividad> historial;
 
	public Cuenta(String CVU, String alias, String DNIPropietario) {
		this.CVU = CVU;
		this.alias = alias;
		this.DNIPropietario = DNIPropietario;
		this.saldo = 0.0;
		this.historial = new ArrayList<>();
	}

	public String getDNIPropietario() { //dato que necesitamos en Transferencia
		return this.DNIPropietario;
	}
	
	public String getCVU() { //dato que necesitamos en Transferencia
		return this.CVU;
	}
	
	public void obtenerSaldo() {
		
		
	}
 
	public void transferir(Cuenta destino, double monto) {
		
		
	}
 
	public void invertir(Inversion nuevaInversion, double monto) {
		
		
	}
 
	public void consultarMovimientos() {
		
		
	}
	
	public abstract String toString();
}