package ar.edu.ungs.billetera;

public class CuentaPremium extends Cuenta{
	private final double MONTO_MINIMO = 500000;
	
	public CuentaPremium(String CVU, String alias, String DNIPropietario) {
		super(CVU, alias, DNIPropietario);	
	}

	@Override
	public boolean puedeAcreditar(double monto) {
		return true;
	}

	@Override
	public boolean puedeDebitar(double monto) {
		return (this.saldo - monto) >= MONTO_MINIMO;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("Premium: ").append(this.alias).append("(").append(this.CVU).append(")");
		
		return sb.toString();
	}
}
