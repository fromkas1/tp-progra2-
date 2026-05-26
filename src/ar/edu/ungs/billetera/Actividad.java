package ar.edu.ungs.billetera;

import java.time.LocalDate;

public abstract class Actividad {
	private static int contadorActividades = 1; //para que el numeroOperacion se vaya incrementando en cada actividad
	protected String numeroOperacion;
	protected LocalDate fecha;
	protected double monto;
	
	public Actividad(double monto) {
		this.fecha = Utilitarios.hoy();
		this.monto = monto;
		this.numeroOperacion = "Operacion N°" + contadorActividades;

		contadorActividades++;
	}
	
	public abstract String toString();

}
