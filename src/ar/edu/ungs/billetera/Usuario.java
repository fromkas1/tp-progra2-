package ar.edu.ungs.billetera;

import java.util.HashMap;
import java.util.Map;

public class Usuario {
	protected String DNI;
	protected String nombreYApellido;
	protected String telefono;
	protected String email;
	protected double totalInvertido;
	protected Map<String, Cuenta> misCuentas;

	public Usuario(String DNI, String nombreYApellido, String telefono, String email) {

		if (DNI == null || DNI.isEmpty() || DNI.length() > 8) {

			throw new IllegalArgumentException("DNI no puede ser null , ni estar vacio , ni ser mayor a 8 digitos ");
		}

		if (nombreYApellido == null || nombreYApellido.isEmpty()) {

			throw new IllegalArgumentException(
					"nombreYApellido no puede ser null ni estar vacio");
		}

		if (telefono == null || telefono.isEmpty()) {

			throw new IllegalArgumentException("telefono no puede ser null ni estar vacio");
		}

		if (email == null || email.isEmpty()) {

			throw new IllegalArgumentException("email no puede ser null ni estar vacio");
		}

		this.DNI = DNI;
		this.nombreYApellido = nombreYApellido;
		this.telefono = telefono;
		this.email = email;
		this.totalInvertido = 0.0;
		this.misCuentas = new HashMap<>();
	}

	public void agregarCuenta(Cuenta nueva) {
		if (nueva == null) {
            throw new IllegalArgumentException("La cuenta no puede ser nula");
        }
        if (misCuentas.containsKey(nueva.alias)) {
            throw new IllegalArgumentException("El alias ya esta registrado para el usuario");
        }
        misCuentas.put(nueva.alias, nueva);

	}

	public double obtenerTotalInvertido() {

		return totalInvertido;
	}

	public void actualizarTotalInvertido(double monto) {
		this.totalInvertido += monto;
        if (this.totalInvertido < 0) {
            this.totalInvertido = 0;
        }

	}
	
	public double consultarTotalinvertido() {
		return this.totalInvertido;
	}
	

	public void consultarMovimientos() {
		if (misCuentas == null || misCuentas.isEmpty()) {
			System.out.println("El usuario no tiene cuentas registradas.");
			return;
		}

		for (Cuenta cuenta : misCuentas.values()) {
			System.out.println("Cuenta: " + cuenta.getAlias() + " (" + cuenta.getCVU() + ")");
			if (cuenta.getHistorial().isEmpty()) {
				System.out.println("  No hay movimientos registrados para esta cuenta.");
				continue;
			}
			for (Actividad actividad : cuenta.getHistorial()) {
				System.out.println("  " + actividad.toString());
			}
		}

	}
	
	public Map<String, Cuenta> getMisCuentas() {
		return misCuentas;
	}

	public String toString() {

		 return "Usuario{" + "DNI='" + DNI + '\'' + ", nombreYApellido='" + nombreYApellido + '\'' + ", telefono='"
                + telefono + '\'' + ", email='" + email + '\'' + ", totalInvertido=" + totalInvertido + '}';
	}
}
