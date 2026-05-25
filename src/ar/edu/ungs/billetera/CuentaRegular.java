package ar.edu.ungs.billetera;

public class CuentaRegular extends Cuenta{
	private final double LIMITE_SALDO = 5000000;

	public CuentaRegular(String CVU, String alias, String DNIPropietario) {
		super(CVU, alias, DNIPropietario);
	}
	
	@Override
	public boolean puedeAcreditar(double monto) {
		return (this.saldo + monto) <= LIMITE_SALDO;
	}
	
	@Override
	public boolean puedeDebitar(double monto) {
		return this.saldo >= monto;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Regular: ").append(this.alias).append("(").append(this.CVU).append(")");
		
		return sb.toString();
	}


}