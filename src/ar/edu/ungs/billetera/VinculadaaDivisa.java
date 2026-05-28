package ar.edu.ungs.billetera;

import java.time.temporal.ChronoUnit;

public class VinculadaaDivisa extends Inversion implements Precancelable{
	private String divisaReferencia;
	private double tasaInteresDivisa;
	private double cotizacionInicial;
	
	public VinculadaaDivisa(int ID, int plazoDias, double montoInvertido, String divisaReferencia, double tasaInteresDivisa) {
		super(ID, plazoDias, montoInvertido);
		
		if (montoInvertido <= 0) {
			throw new IllegalArgumentException("Monto debe ser positivo");
		}
		
		if (plazoDias <= 0) {
			throw new IllegalArgumentException("Plazo debe ser mayor a 0 días");
		}
		
		if (divisaReferencia == null || divisaReferencia.isEmpty()) {
			throw new IllegalArgumentException("Divisa no puede ser nula ni vacía");
		}
		
		if (tasaInteresDivisa <= 0) {
			throw new IllegalArgumentException("Tasa debe ser positiva");
		}
		
		this.divisaReferencia = divisaReferencia;
		
		this.tasaInteresDivisa = tasaInteresDivisa;
		
		this.cotizacionInicial = Utilitarios.consultarCotizacion(divisaReferencia); //saber cuanto estaba la moneda cuando se creo la inversion
		
	}
	
	public double precancelar() {
		
		long cantDiasTomados = ChronoUnit.DAYS.between(this.fechainicio, Utilitarios.hoy()); //para poder restar fechas
		
		double divisasCompradas = this.montoInvertido / this.cotizacionInicial; //saber cuanto se invirtio cuando se creo
		
		double intereses = divisasCompradas * this.tasaInteresDivisa * cantDiasTomados / 365.0;
		
		double divisaHoy = Utilitarios.consultarCotizacion(this.divisaReferencia); //saber cuanto esta ahora
		
		return (divisasCompradas + (intereses / 2.0)) * divisaHoy; //calcula con los intereses y lo multiplica por la divisa para saber en pesos cuanto es
	}
	
	@Override
	public double calcularResultado(Cuenta cuenta) {
		if (cuenta == null) {
			
			throw new IllegalArgumentException("Cuenta asociada no puede ser nula");
		}
		this.estadoActivo = false;
		
		double divisasEquivalente = montoInvertido / cotizacionInicial;
		
		return (divisasEquivalente + divisasEquivalente * tasaInteresDivisa * plazoDias / 365.0)
			* Utilitarios.consultarCotizacion(divisaReferencia);
	}

	@Override
	public String toString() {
		return "VinculadaaDivisa{id=" + ID + ", monto=" + montoInvertido + ", plazo=" + plazoDias + ", divisa='"
			+ divisaReferencia + "', tasa=" + tasaInteresDivisa + "}";
		
	}	
}
