package ar.edu.ungs.billetera;

public class FondoLiquidezEmpresarial extends Inversion{
	private final double TASAFLE = 0.08;
	private final double MONTO_MINIMO = 20000000;

	 public FondoLiquidezEmpresarial(int ID, int plazoDias, double montoInvertido){
		 super(ID, plazoDias, montoInvertido);
		 
		 
		 if (montoInvertido < MONTO_MINIMO) {
				throw new IllegalArgumentException("El monto minimo para el Fondo de Liquidez es de $" + MONTO_MINIMO);
			}
	}
	 
	 @Override
	 public double calcularResultado(Cuenta cuenta) {
		 if (cuenta == null) {
			 
			 throw new IllegalArgumentException("Cuenta asociada no puede ser nula");
		 }
		 if (!(cuenta instanceof CuentaCorporativa)) {
			 
			 throw new IllegalArgumentException("Solo cuentas corporativas pueden invertir en fondos de liquidez");
		 }
		 this.estadoActivo = false;
		 
		 return montoInvertido + (montoInvertido * TASAFLE * plazoDias) / 365.0;
	 }

	 @Override
	 public String toString() {
		 
		 return "Fondo Liquidez Empresarial {id=" + ID + ", monto=" + montoInvertido + ", plazo=" + plazoDias + ", tasa FLE=" + TASAFLE + "}";
	 }
}
