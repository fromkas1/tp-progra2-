package ar.edu.ungs.billetera;

public class VinculadaaDivisa extends Inversion implements Precancelable{
	private String divisaRefencia;
	private double tasaInteresDivisa;
	private double cotizacionInicial;
	
	public VinculadaaDivisa(int ID, int plazoDias, double montoInvertido, String divisaRefencia, double tasaInteresDivisa) {
		super(ID, plazoDias, montoInvertido);
		this.divisaRefencia = divisaRefencia;
		this.tasaInteresDivisa = tasaInteresDivisa;
	}
	
	public double precancelar() {
		
		return 0;
	}
	
	public double calcularResultado(Cuenta cuenta) {
		double divisasEquivalente = montoInvertido / cotizacionInicial;
		return (divisasEquivalente + divisasEquivalente * tasaInteresDivisa * plazoDias / 365.0)
			* Utilitarios.consultarCotizacion(divisaRefencia);
		
	}

	@Override
	public String toString() {
		return "VinculadaaDivisa{id=" + ID + ", monto=" + montoInvertido + ", plazo=" + plazoDias + ", divisa='"
			+ divisaRefencia + "', tasa=" + tasaInteresDivisa + "}";
		
		
	}	
}
