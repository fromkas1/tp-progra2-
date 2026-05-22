package tpProg2;

public class CuentaCorporativa {
	public CuentaCorporativa(String cUITempresa, boolean autorizadoEmpresa) {
		
		this.CUITempresa = CUITempresa;
		this.autorizadoEmpresa = autorizadoEmpresa;
	}


	private String CUITempresa;
	private boolean autorizadoEmpresa;
	
	
	public boolean puedeInvertir(Inversion inverion, float monto) {}
	
	
	public void validarAutorizacion() {}
}


