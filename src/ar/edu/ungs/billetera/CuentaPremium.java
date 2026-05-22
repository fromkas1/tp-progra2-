package ar.edu.ungs.billetera;

public class CuentaPremium extends Cuenta{
	private final double MONTO_MINIMO = 500000;
	
	public CuentaPremium(String CVU, String alias, String DNIPropietario) {
		super(CVU, alias, DNIPropietario);	
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
