package ar.edu.ungs.billetera;

public class OperacionInversion extends Actividad{
	private Inversion inversion;
	private Cuenta origen;
	
	public OperacionInversion(double monto, Inversion inversion, Cuenta origen) {
		super(monto);
		this.inversion = inversion;
		this.origen = origen;
	}
	
	
	public Inversion getInversion() {
		return inversion;
	}

	@Override
	public String toString() {
		//usamos el formato que nos pide en la segunda parte
		StringBuilder sb = new StringBuilder();
		
		sb.append("\n Inversion: \n");
		sb.append("\t fecha: [").append(this.fecha).append("] \n");
		sb.append("\t origen: [").append(this.origen.getDNIPropietario()).append("] ([").append(this.origen.getCVU()).append("]) \n");
		sb.append("\t desc: [").append(this.inversion.getClass().getSimpleName()).append("] \n");
		sb.append("\t monto: [").append(this.monto).append("] \n");
		sb.append("\t plazo: [").append(this.inversion.getPlazoDias()).append("] \n");
		sb.append("\t [Aprobado]");
		
		return sb.toString();
	}
}
