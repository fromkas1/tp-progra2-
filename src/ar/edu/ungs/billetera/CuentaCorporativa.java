package ar.edu.ungs.billetera;

public class CuentaCorporativa extends Cuenta{
	private Empresa empresaPerteneciente;
	
	public CuentaCorporativa(String CVU, String alias,String dniAutorizado) {
		super(CVU, alias,dniAutorizado);
	}
	
	@Override
	public boolean puedeAcreditar(double monto) {
		return monto > 0 ;
	}
	
	@Override
	public boolean puedeDebitar(double monto) {
		return this.saldo >= monto;
	}
	
	public boolean puedeInvertir(Inversion inverion, double monto) {
		return this.saldo >=monto;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		sb.append("Corporativa: ").append(this.alias).append("(").append(this.CVU).append(")");
		
		return sb.toString();
	}
}
