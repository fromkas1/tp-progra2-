package ar.edu.ungs.billetera;

import java.time.LocalDate;

public abstract class Actividad {
	protected String numeroOperacion;
	protected LocalDate fecha;
	protected double monto;
	
	public Actividad(String numeroOperacion, double monto) {
		this.fecha = Utilitarios.hoy();
		this.numeroOperacion = numeroOperacion;
		this.monto = monto;
	}
	
	public abstract String toString();

}