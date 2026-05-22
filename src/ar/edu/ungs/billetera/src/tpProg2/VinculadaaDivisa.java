package tpProg2;

import java.lang.reflect.Constructor;

public class VinculadaaDivisa {
	private String divisaRefencia;
	private double tasaInteresDivisa;
	
	public VinculadaaDivisa(String divisaRefencia, double tasaInteresDivisa) {
		
		this.divisaRefencia = divisaRefencia;
		this.tasaInteresDivisa = tasaInteresDivisa;
	}
	
	public float precancelar() {return 0;}
	
	private float calcularResultado(Cuenta cuenta) {}
	
	
}
