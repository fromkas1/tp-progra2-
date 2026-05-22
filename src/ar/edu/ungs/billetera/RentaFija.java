package ar.edu.ungs.billetera;

public class RentaFija extends Inversion implements Precancelable{
	private double tasaInteres;
	
	public RentaFija(int ID, int plazoDias, double montoInvertido, double tasaInteres) {
		super(ID, plazoDias, montoInvertido);
		this.tasaInteres = tasaInteres;
	}
	
	public double precancelar() {
		
		return 0;
	}
	
	public double calcularResultado(Cuenta cuenta) {
		
		return 0;
	}

	@Override
	public String toString() {
		
		return null;
	}
}