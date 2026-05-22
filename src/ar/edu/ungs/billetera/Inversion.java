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
	} 

	public int getPlazoDias() { //lo usamos para el toString de OperacionInversion
		return this.plazoDias;
	}
	
	public abstract double calcularResultado(Cuenta cuentaAsociada);
	
	public abstract String toString();
}
