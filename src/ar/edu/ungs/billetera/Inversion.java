package ar.edu.ungs.billetera;

import java.time.LocalDate;

public abstract class Inversion {
	protected int ID;
	protected LocalDate fechainicio;
	protected int plazoDias;
	protected double montoInvertido;
	protected boolean estadoActivo;
	
	public Inversion(int ID, int plazoDias, double montoInvertido) {
		this.ID = ID;
		this.fechainicio = Utilitarios.hoy();
		this.plazoDias = plazoDias;
		this.montoInvertido = montoInvertido;
		this.estadoActivo = true;
		
		if (montoInvertido <= 0) {
			throw new IllegalArgumentException("Monto debe ser positivo");
		}
		
		if (plazoDias <= 0) {
			throw new IllegalArgumentException("Plazo debe ser mayor a 0 días");
		}
		
		
	} 
	
	public abstract double calcularResultado(Cuenta cuentaAsociada);
	
	public abstract String toString();
	
	public boolean estaActivo() {
		return estadoActivo;
	}

	public int getID() {
		return ID;
	}

	public int getPlazoDias() { 
		return this.plazoDias;
	}
	
	public double getMontoInvertido() {
		return montoInvertido;
	}
}
