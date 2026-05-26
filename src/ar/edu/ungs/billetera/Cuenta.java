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
	
	public String getAlias() {
		return this.alias;
	}
	
	public double obtenerSaldo() {
		return saldo;
	}
	
	public List<Actividad> getHistorial() {
		return historial;
	}

	public abstract boolean puedeAcreditar(double monto); //depende de las reglas del tipo de cuenta
	
	public abstract boolean puedeDebitar(double monto); //depende de las reglas del tipo de cuenta
	
	public void acreditar(double monto) {
		if(!this.puedeAcreditar(monto)) {
			throw new IllegalStateException("No se puede acreditar.");
		}
		this.saldo += monto;
	}
	
	public void debitar(double monto) {
		if(!this.puedeDebitar(monto)) {
			throw new IllegalStateException("No se puede debitar.");
		}
		this.saldo -= monto;
	}
	
	// Para la creacion de la cuenta Premium , inicializar saldo > Monto minimo
	public double establecerSaldoInicial(double nuevoSaldo) {
		this.saldo = nuevoSaldo;
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
		
		this.debitar(monto);
		destino.acreditar(monto);
	}
		
	

	public void invertir(Inversion nuevaInversion, double monto) {
		if (nuevaInversion == null) {
			throw new IllegalArgumentException("Inversión no puede ser nula");
		}
		if (monto <= 0) {
			throw new IllegalArgumentException("Monto debe ser positivo");
		}
		
		this.debitar(monto);
		
	}
	
	
	public void agregarMovimiento(Transferencia nuevaTransferencia) {
		
		this.historial.add(nuevaTransferencia);
	}
	
	public void consultarMovimientos() {
		
		
	}
	
	
	
	public abstract String toString();
	
}
