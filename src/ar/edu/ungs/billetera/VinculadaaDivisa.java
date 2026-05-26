package ar.edu.ungs.billetera;

import java.time.temporal.ChronoUnit;

public class VinculadaaDivisa extends Inversion implements Precancelable{
	private String divisaRefencia;
	private double tasaInteresDivisa;
	private double cotizacionInicial;
	
	public VinculadaaDivisa(int ID, int plazoDias, double montoInvertido, String divisaRefencia, double tasaInteresDivisa) {
		super(ID, plazoDias, montoInvertido);
		this.divisaRefencia = divisaRefencia;
		this.tasaInteresDivisa = tasaInteresDivisa;
		
		this.cotizacionInicial = Utilitarios.consultarCotizacion(divisaRefencia); //saber cuanto estaba la moneda cuando se creo la inversion
	}
	
	public double precancelar() {
		long cantDiasTomados = ChronoUnit.DAYS.between(this.fechainicio, Utilitarios.hoy()); //para poder restar fechas
		
		double divisasCompradas = this.montoInvertido / this.cotizacionInicial; //saber cuanto se invirtio cuando se creo
		
		double intereses = divisasCompradas * this.tasaInteresDivisa * cantDiasTomados / 365.0;
		
		double divisaHoy = Utilitarios.consultarCotizacion(this.divisaRefencia); //saber cuanto esta ahora
		
		return (divisasCompradas + (intereses / 2.0)) * divisaHoy; //calcula con los intereses y lo multiplica por la divisa para saber en pesos cuanto es
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
