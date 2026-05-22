package ar.edu.ungs.billetera;

import java.util.List;

public abstract class Cuenta {

 private String CVU;
 private String alias;
 private double saldo;
 List<Actividad> historial;
 
 public Cuenta(String cVU, String alias, double saldo, List<Actividad> historial) {
		super();
		CVU = cVU;
		this.alias = alias;
		this.saldo = saldo;
		this.historial = historial;
	}
 public void obtenerSaldo() { }
 
 public void transferir(Cuenta destino,double monto) {}
 
 public void invertir(Inversion nuevaInversion,double monto) {}
 
 public void consultarMovimientos() {}
}
