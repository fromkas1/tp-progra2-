package ar.edu.ungs.billetera;

public class CuentaPremium extends Cuenta{
	private final double MONTO_MINIMO = 500000;
	
	public CuentaPremium(double montoInicial,String CVU, String alias, String DNIPropietario) {
		super(CVU, alias, DNIPropietario);	
		
	if (montoInicial<MONTO_MINIMO) {
		throw new IllegalArgumentException("Monto inicial debe ser superior al monto minimo");
	}
	
	establecerSaldoInicial(montoInicial);
	}

	public void debitar(boolean monto) {
		
	}
	
	public void validarReglas() {
		
	}


	@Override
	public String toString() {
		
		
		return null;
	}
}
