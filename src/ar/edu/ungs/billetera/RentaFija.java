package ar.edu.ungs.billetera;

public class RentaFija extends Inversion implements Precancelable{
	private double tasaInteres;
	
	public RentaFija(int ID, int plazoDias, double montoInvertido, double tasaInteres) {
		super(ID, plazoDias, montoInvertido);
		this.tasaInteres = tasaInteres;
	}
	
	public double precancelar() {
		
		
	}
	
	public double calcularResultado(Cuenta cuenta) {
		
		return montoInvertido + montoInvertido * tasaInteres * plazoDias / 365.0;
	}

	@Override
	public String toString() {
		
		return "RentaFija{id=" + ID + ", monto=" + montoInvertido + ", plazo=" + plazoDias + ", tasa=" + tasaInteres + "}";
	}
}
