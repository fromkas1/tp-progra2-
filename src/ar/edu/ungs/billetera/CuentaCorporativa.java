package ar.edu.ungs.billetera;

public class CuentaCorporativa extends Cuenta{
	private Empresa empresaPerteneciente;
	private boolean usuarioAutorizadoPorEmpresa;
	
	public CuentaCorporativa(String CVU, String alias, String DNIPropietario, boolean usuarioAutorizadoPorEmpresa) {
		super(CVU, alias, DNIPropietario);
		this.usuarioAutorizadoPorEmpresa = usuarioAutorizadoPorEmpresa;
	}
	
	public boolean puedeInvertir(Inversion inverion, boolean monto) {
		
		
		return monto;
	}
	
	
	public void validarAutorizacion() {
		
		
	}


	@Override
	public String toString() {
		
		
		return null;
	}
}


