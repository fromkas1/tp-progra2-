package tpProg2;

import java.time.LocalDate;

public abstract class Inversion {
	private LocalDate fechainicio;
	private int plazoDias;
	private double montoInvertido;
	private boolean estadoActivo;
	
	private constructor() {
		this.fechainicio=fechainicio;
		this.plazoDias=plazoDias;
		this.montoInvertido=montoInvertido;
		this.estadoActivo=estadoActivo;
	} 

	public float calcularResultado(Cuenta cuentaAsociada) {}
}
