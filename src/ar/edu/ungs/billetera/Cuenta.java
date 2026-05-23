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
		return saldo;
		
	}
 
	public void transferir(Cuenta destino, double monto) {
		if (destino == null) {
			throw new IllegalArgumentException("Cuenta destino no puede ser nula");
		}
		if (monto <= 0) {
			throw new IllegalArgumentException("Monto debe ser positivo");
		}
		if (this.saldo < monto) {
			throw new IllegalStateException("Saldo insuficiente");
		}
		this.saldo -= monto;
		destino.saldo += monto;
	}
		
	

	public void invertir(Inversion nuevaInversion, double monto) {
		if (nuevaInversion == null) {
			throw new IllegalArgumentException("Inversión no puede ser nula");
		}
		if (monto <= 0) {
			throw new IllegalArgumentException("Monto debe ser positivo");
		}
		if (saldo < monto) {
			throw new IllegalStateException("Saldo insuficiente");
		}
		saldo -= monto;
		
	}
 
	public void consultarMovimientos() {
		
		
	}
	
	public abstract String toString();
}
