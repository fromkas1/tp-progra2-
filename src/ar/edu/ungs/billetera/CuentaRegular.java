package ar.edu.ungs.billetera;

public class CuentaRegular extends Cuenta{
	private final double LIMITE_SALDO = 5000000;

	public CuentaRegular(String CVU, String alias, String DNIPropietario) {
		super(CVU, alias, DNIPropietario);
	}
	
	public boolean puedeAcreditar(float monto) {
		return monto < LIMITE_SALDO;
	}
	
	public void debitar(float monto) {
		
		
	}

	@Override
	public String toString() {
		
		return null;
	}
}
