package ar.edu.ungs.billetera;

import java.util.*;

public class Empresa {
	public Empresa(String cuit, List<String> dniautorizados) {
		super();
		this.cuit = cuit;
		this.dniautorizados = dniautorizados;
	}
	private String cuit;
	private List<String>dniautorizados;

}
