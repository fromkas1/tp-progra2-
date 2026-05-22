package tpProg2;

import java.time.LocalDate;

public abstract class Actividad {
	private String numeroOperacion;
	private LocalDate fecha;
	private double monto;
	
	public void constructor() {
		this.fecha=fecha;
		this.numeroOperacion=numeroOperacion;
		this.monto=monto;
	}
public String mostrarDetalleOperacion() {}
}
