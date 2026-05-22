package ar.edu.ungs.billetera;

public class FondoLiquidezEmpresarial extends Inversion{
	private final double TASAFLE = 0.08;
	private final double MONTO_MINIMO = 20000000;

	 public FondoLiquidezEmpresarial(int ID, int plazoDias, double montoInvertido){
		 super(ID, plazoDias, montoInvertido);
	}
	 
	 public double calcularResultado(Cuenta cuenta) {
		 
		 return 0;
	 }

	 @Override
	 public String toString() {
		 
		return null;
	 }
}
