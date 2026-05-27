package ar.edu.ungs.billetera;

import java.time.temporal.ChronoUnit;

public class RentaFija extends Inversion {
	private double tasaInteres;
	
	public RentaFija(int ID, int plazoDias, double montoInvertido, double tasaInteres) {
		super(ID, plazoDias, montoInvertido);
		this.tasaInteres = tasaInteres;
	}
	
	public double precancelar() {
		long cantDiasTomados = ChronoUnit.DAYS.between(this.fechainicio, Utilitarios.hoy()); //para poder restar fechas
		
		double intereses = this.montoInvertido * this.tasaInteres * cantDiasTomados / 365.0; //calcula los intereses hasta los dias que pasaron
		
		this.estadoActivo = false;
		
		return this.montoInvertido + intereses / 2.0;
	}
	
	public double calcularResultado(Cuenta cuenta) {
		this.estadoActivo = false;
		
		return montoInvertido + montoInvertido * tasaInteres * plazoDias / 365.0;
	}

	@Override
	public String toString() {
		
		return "RentaFija{id=" + ID + ", monto=" + montoInvertido + ", plazo=" + plazoDias + ", tasa=" + tasaInteres + "}";
	}
}
