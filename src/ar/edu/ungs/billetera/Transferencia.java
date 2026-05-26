package ar.edu.ungs.billetera;

public class Transferencia extends Actividad{
	private Cuenta origen;
	private Cuenta destino;
	
	public Transferencia(String numeroOperacion, double monto, Cuenta origen, Cuenta destino) {
		super(numeroOperacion, monto);
		this.origen = origen;
		this.destino = destino;
	}

	@Override
	public String toString() {
		//usamos el formato que nos pide en la segunda parte
		StringBuilder sb = new StringBuilder();
		
		sb.append("Transferencia:\n");
		sb.append("\t fecha: [").append(this.fecha).append("]\n");
		sb.append("\t origen: [").append(this.origen.getDNIPropietario()).append("] ([").append(this.origen.getCVU()).append("])\n");
		sb.append("\t destino: [").append(this.destino.getDNIPropietario()).append("] ([").append(this.destino.getCVU()).append("])\n");
		sb.append("\t monto: [").append(this.monto);
		sb.append("\t Aprobado");
		
		return sb.toString();
	}
}
