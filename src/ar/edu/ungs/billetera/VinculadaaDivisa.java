package ar.edu.ungs.billetera;

public class VinculadaaDivisa extends Inversion implements Precancelable{
	private String divisaRefencia;
	private double tasaInteresDivisa;
	
	public VinculadaaDivisa(int ID, int plazoDias, double montoInvertido, String divisaRefencia, double tasaInteresDivisa) {
		super(ID, plazoDias, montoInvertido);
		this.divisaRefencia = divisaRefencia;
		this.tasaInteresDivisa = tasaInteresDivisa;
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
