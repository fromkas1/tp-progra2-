package ar.edu.ungs.billetera;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Billetera implements IBilletera {
	private Map<String, Usuario> todosLosUsuarios;
	private Map<String, Cuenta> todasLasCuentas;
	private Map<String, Empresa> todasLasEmpresas;
	private List<Actividad> historialGlobal;
	
	public Billetera() {

		this.todosLosUsuarios = new HashMap<>();
		this.todasLasCuentas = new HashMap<>();
		this.todasLasEmpresas = new HashMap<>();
		this.historialGlobal = new LinkedList<>();
	}

	
	@Override
	public void registrarEmpresa(String cuit, String nombreFantasia, String telefono, String email,
			String nombreContacto) {
		
		if (this.todasLasEmpresas.containsKey(cuit)) {
			
			throw new IllegalArgumentException("La empresa con el CUIT " + cuit + "ya existe");
		}

		Empresa nuevaEmpresa = new Empresa(cuit, nombreFantasia, telefono, email, nombreContacto);
		
		todasLasEmpresas.put(cuit, nuevaEmpresa);

	}

	
	@Override
	public void agregarPersonaAutorizada(String cuitEmpresa, String dniAutorizado) {
		
		Empresa empresa = todasLasEmpresas.get(cuitEmpresa);
		
		if(empresa == null) {
			
			throw new IllegalArgumentException("Empresa no existe");
		}
		
		if (empresa.estaAutorizado(dniAutorizado)) {
			
			throw new IllegalArgumentException("Dni ya esta autorizado");
		}
		
		empresa.agregarAutorizado(dniAutorizado);

	}

	
	@Override
	public void registrarUsuario(String dni, String nombre, String telefono, String email) {

		Usuario usuario = new Usuario(dni, nombre, telefono, email);

		if (this.todosLosUsuarios.containsKey(dni)) {
			
			throw new IllegalArgumentException("Usuario" + usuario + "ya esta registrado en el sistema");
		}

		todosLosUsuarios.put(dni, usuario);

	}

	
	@Override
	public String crearCuentaRegular(String dniUsuario, String alias) {
		
		Usuario usu = todosLosUsuarios.get(dniUsuario);
		
		if (usu == null) {
			
			throw new IllegalArgumentException("No existe usuario con DNI: "+ dniUsuario);
		}
		
		
		for (Cuenta cuentaActual : todasLasCuentas.values() ) {
			
			if(cuentaActual.getAlias().equals(alias)) {
				
				throw new IllegalArgumentException("Alias ya esta registrado : "+alias);
			}
			
		}
		
		String CVU = Utilitarios.generarSiguienteCvu();
		
		Cuenta cuentaRegular = new CuentaRegular(CVU, alias, dniUsuario);
		
		usu.agregarCuenta(cuentaRegular);
		
		todasLasCuentas.put(CVU, cuentaRegular);
				
		return CVU;
	}

	
	@Override
	public String crearCuentaPremium(String dniUsuario, String alias, double depositoInicial) {
		Usuario usu = todosLosUsuarios.get(dniUsuario);
		
		if (usu == null) {
			
			throw new IllegalArgumentException("No existe usuario con DNI: "+ dniUsuario);
		}
		
		
		for (Cuenta cuentaActual : todasLasCuentas.values() ) {
			
			if(cuentaActual.getAlias().equals(alias)) {
				
				throw new IllegalArgumentException("Alias ya esta registrado : "+alias);
			}
			
		}
		
		String CVU = Utilitarios.generarSiguienteCvu();
		
		Cuenta cuentaPremium = new CuentaPremium(depositoInicial,CVU, alias, dniUsuario);
		
		
		usu.agregarCuenta(cuentaPremium);
		
		todasLasCuentas.put(CVU, cuentaPremium);
				
		return CVU;
	}

	@Override
	public String crearCuentaCorporativa(String dniUsuario, String alias, String cuitEmpresa) {
		
		Usuario usu = todosLosUsuarios.get(dniUsuario);
		
		if (usu == null) {
			
			throw new IllegalArgumentException("No existe usuario con DNI: "+ dniUsuario);
		}
		
		
		for (Cuenta cuentaActual : todasLasCuentas.values() ) {
			
			if(cuentaActual.getAlias().equals(alias)) {
				
				throw new IllegalArgumentException("Alias ya esta registrado : "+alias);
			}
			
		}
		
		Empresa empresa = todasLasEmpresas.get(cuitEmpresa);
		
		if (empresa == null) {
			
			throw new IllegalArgumentException("No existe empresa con este Cuit: "+ cuitEmpresa);
		}
		
		if(!empresa.estaAutorizado(dniUsuario)) {
			
			throw new IllegalArgumentException("Dni no autorizado para operar por la empresa");
		}
		
		String CVU = Utilitarios.generarSiguienteCvu();
		
		Cuenta cuentaCorporativa = new CuentaCorporativa(CVU, alias, dniUsuario);
		
		usu.agregarCuenta(cuentaCorporativa);
		
		todasLasCuentas.put(CVU, cuentaCorporativa);
				
		return CVU;
	}

	@Override
	public List<String> obtenerCuentas(String dniUsuario) {
		
		List <String> listaDeDatos = new LinkedList<>();
		
		Usuario usu = todosLosUsuarios.get(dniUsuario);
		
		if (usu == null) {
			
			throw new IllegalArgumentException("No existe usuario con DNI: "+ dniUsuario);
		}
		
		for (Cuenta cuentaActual : usu.getMisCuentas().values() ) {
			
			listaDeDatos.add(cuentaActual.toString());
			
		}
		return listaDeDatos;
	}

	@Override
	public double obtenerSaldoDisponible(String cvu) {
		
		Cuenta cuenta = todasLasCuentas.get(cvu);
		
		if (cuenta == null) {
			
			throw new IllegalArgumentException("La cuenta no existe");
		}
		
		double saldoDisponible = cuenta.obtenerSaldo();
			
		return saldoDisponible;
	}

	@Override
	public void realizarTransferencia(String cvuOrigen, String cvuDestino, double monto) {
		
		if(cvuOrigen == null || cvuDestino ==null) {
			
			throw new IllegalArgumentException();
		}
		
		Cuenta cuentaCVUOrigen = todasLasCuentas.get(cvuOrigen);
		
		Cuenta cuentaCVUDestino = todasLasCuentas.get(cvuDestino);
		
		cuentaCVUOrigen.transferir(cuentaCVUDestino, monto);
		
		Transferencia comprobante = new Transferencia(monto, cuentaCVUOrigen, cuentaCVUDestino);
		
		cuentaCVUOrigen.agregarMovimiento(comprobante);
		
		cuentaCVUDestino.agregarMovimiento(comprobante);
		
		historialGlobal.add(comprobante);
	}

	
	@Override
	public int realizarInversionRentaFija(String dni, String cvu, double monto, int plazoDias) {
		
		Usuario usuario = todosLosUsuarios.get(dni);
		
		if (usuario == null) {
			
			throw new IllegalArgumentException("Usuario no existe");
		}
		
		Cuenta cuenta = todasLasCuentas.get(cvu);
		
		if (cuenta == null) {
			
			throw new IllegalArgumentException("Cuenta no existe");
		}
		
		int idInversion = generarIDInversion();
		
		double tasaInteres = 0.2;
		
		Inversion rentaFija = new RentaFija(idInversion, plazoDias, monto, tasaInteres);
		
		cuenta.invertir(rentaFija, monto);
		
		OperacionInversion comprobante = new OperacionInversion(monto,rentaFija,cuenta);
		
		cuenta.agregarMovimiento(comprobante);
		
		usuario.actualizarTotalInvertido(monto);
		
		historialGlobal.add(comprobante);
		
		return idInversion;
		
	}

	@Override
	public int realizarInversionDivisa(String dni, String cvu, double monto, int plazoDias, String divisa,
			double tasa) {
		
		Usuario usuario = todosLosUsuarios.get(dni);
		
		Cuenta cuenta = todasLasCuentas.get(cvu);
		
		if (usuario == null) {
			
			throw new IllegalArgumentException("Usuario no existe");
		}
		
		if (cuenta == null) {
			
			throw new IllegalArgumentException("Cuenta no existe");
		}
		
		int idInversion = generarIDInversion();
		
		Inversion vinculadaDivisa = new VinculadaaDivisa(idInversion, plazoDias, monto, divisa, tasa);
		
		cuenta.invertir(vinculadaDivisa, monto);
		
		OperacionInversion comprobante = new OperacionInversion(monto,vinculadaDivisa,cuenta);
		
		cuenta.agregarMovimiento(comprobante);
		
		usuario.actualizarTotalInvertido(monto);
		
		historialGlobal.add(comprobante);
		
		return idInversion;
	}

	
	@Override
	public int realizarInversionLiquidez(String dni, String cvu, double monto, int plazoDias) {
		
		Usuario usuario = todosLosUsuarios.get(dni);
		
		Cuenta cuenta = todasLasCuentas.get(cvu);

		if (usuario == null) {
			throw new IllegalArgumentException("Usuario no existe");
		}
	
		if (cuenta == null) {
			throw new IllegalArgumentException("Cuenta no existe");
		}

		int idInversion = generarIDInversion();
		
		Inversion vinculadaLiquidez = new FondoLiquidezEmpresarial(idInversion, plazoDias, monto);

		cuenta.invertir(vinculadaLiquidez, monto);
		
		OperacionInversion comprobante = new OperacionInversion(monto,vinculadaLiquidez,cuenta);
		
		cuenta.agregarMovimiento(comprobante);
		
		usuario.actualizarTotalInvertido(monto);
		
		historialGlobal.add(comprobante);

		return idInversion;
	}

	@Override
	public void precancelarInversion(String dni, String cvu, int idInversion) {
		
		Usuario usuario = todosLosUsuarios.get(dni);
		
		Cuenta cuenta = todasLasCuentas.get(cvu);

		if (usuario == null) {
			
			throw new IllegalArgumentException("Usuario no existe");
		}
	

		if (cuenta == null) {
			
			throw new IllegalArgumentException("Cuenta no existe");
		}
		
		double montoAInvertir=cuenta.precancerlarInversion(idInversion);
		
		usuario.actualizarTotalInvertido(-montoAInvertir);

	}

	@Override
	public String consultarCvu(String alias) {
		
		Iterator <Cuenta> iterador = todasLasCuentas.values().iterator();
		
		while (iterador.hasNext()) {
			
			Cuenta cuentaActual = iterador.next();
			
			if(cuentaActual.getAlias().equals(alias)) {
				
				return cuentaActual.getCVU();
			}
		}
		
		throw new IllegalArgumentException("Alias no se encuentra registrado");
	}

	@Override
	public List<String> consultarHistorialGlobal() {
		
		List <String> historial = new LinkedList<>();
		
		for (Actividad actividadGlobal : historialGlobal) {
			
			historial.add(actividadGlobal.toString());
		}
		
		return historial;
	}

	@Override
	public List<String> consultarHistorialCuenta(String cvu) {
		
		List<String> historialCuenta = new LinkedList<>();
		
		Cuenta cuenta = todasLasCuentas.get(cvu);
		
		if(cuenta == null) {
			
			throw new IllegalArgumentException("Cuenta no existe");
		}
		
		List <Actividad> actividadCuenta = cuenta.getHistorial();
		
		for(Actividad actividad : actividadCuenta ) {
			
			historialCuenta.add(actividad.toString());
		}
		
		return historialCuenta;
	}

	@Override
	public List<String> consultarHistorialUsuario(String dniUsuario) {
		
		List <String> historialUsuario = new LinkedList<>();

        Usuario usuario = todosLosUsuarios.get(dniUsuario);

        for (Cuenta cuentasUsuario : usuario.getMisCuentas().values() ) {

            for (Actividad cuentas : cuentasUsuario.getHistorial() ) {

                historialUsuario.add(cuentas.toString());

            }
        }

        return historialUsuario;
	}

	@Override
	public double obtenerTotalInvertido(String dniUsuario) {
		
		Usuario usuario = todosLosUsuarios.get(dniUsuario);
		
		if(usuario == null) {
			
			throw new IllegalArgumentException("usuario no existe");
		}
		
		double totalInvertido=usuario.obtenerTotalInvertido();
		
		return totalInvertido;
	}

	
	@Override
	public List<String> cuentasConMayorVolumen(int cantidadTop) {
		
		if (cantidadTop <= 0) {
			throw new IllegalArgumentException("cantidadTop debe ser positiva");
		}

		List<Cuenta> cuentas = new ArrayList<>(todasLasCuentas.values());
		cuentas.sort((c1, c2) -> Integer.compare(c2.getHistorial().size(), c1.getHistorial().size()));

		List<String> topCuentas = new LinkedList<>();
		int limite = Math.min(cantidadTop, cuentas.size());

		for (int i = 0; i < limite; i++) {
			topCuentas.add(cuentas.get(i).toString());
		}

		return topCuentas;
	}

	private int generarIDInversion() {
		
		Random random = new Random();
		
		return random.nextInt(100000) + 1; // Genera un ID entre 1 y 100000
	}


	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(todosLosUsuarios).append("\n");
		
		sb.append(todasLasCuentas).append("\n");
		
		sb.append(todasLasEmpresas).append("\n");
		
		sb.append(historialGlobal).append("\n");
		
		
		return sb.toString();
	
	}
}
