package ar.edu.ungs.billetera;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Billetera implements IBilletera {
	private Map<String, Usuario> todosLosUsuarios;
	private Map<String, Cuenta> todasLasCuentas;
	private Map<String, Empresa> todasLasEmpresas;
	private List<Actividad> historialGlobal;
	private Transferencia transferencia;
	private Actividad actividad;
	
	
	public Billetera() {

		this.todosLosUsuarios = new HashMap<>();
		this.todasLasCuentas = new HashMap<>();
		this.todasLasEmpresas = new HashMap<>();
		this.historialGlobal = new LinkedList<>();
	}

	// AGREGA LA EMPRESA A MAP TODASLASEMPRESAS - Lanza error si la empresa ya esta
	// registrada o algun campo es invalido.
	@Override
	public void registrarEmpresa(String cuit, String nombreFantasia, String telefono, String email,
			String nombreContacto) {
		// validacion si el CUIT ya existe
		if (this.todasLasEmpresas.containsKey(cuit)) {
			throw new IllegalArgumentException("La empresa con el CUIT " + cuit + "ya existe");
		}

		// la agregamos
		Empresa nuevaEmpresa = new Empresa(cuit, nombreFantasia, telefono, email, nombreContacto);
		todasLasEmpresas.put(cuit, nuevaEmpresa);

	}

	// AGREGA EL USUARIO EXISTENTE A DNIAUTORIZADOS DE EMPRESA - Lanza error si la
	// empresa no existe o la persona ya esta autorizada
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

	// AGREGA AL USUARIO A MAP TODOSLOSUSUARIOS - Lanza error si el usuario ya esta
	// registrado o algun campo es invalido
	@Override
	public void registrarUsuario(String dni, String nombre, String telefono, String email) {

		Usuario usuario = new Usuario(dni, nombre, telefono, email);

		if (this.todosLosUsuarios.containsKey(dni)) {
			throw new IllegalArgumentException("Usuario" + usuario + "ya esta registrado en el sistema");
		}

		todosLosUsuarios.put(dni, usuario);

	}

	// AGREGA LA CUENTA A TODASLASCUENTAS DE UN USUARIO EXISTENTE Y RETORNA EL CVU
	// DE LA CUENTA CREADA - Lanza error si el usuario no existe o el alias ya esta
	// registrado
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
		
		//se agrega en la lista de cuentas en Usuario
		usu.agregarCuenta(cuentaRegular);
		
		todasLasCuentas.put(CVU, cuentaRegular);
				
		return CVU;
	}

	// AGREGA LA CUENTA A TODASLASCUENTAS DE UN USUARIO EXISTENTE Y RETORNA EL CVU
	// DE LA CUENTA CREADA - Lanza error si el usuario no existe o el alias ya esta
	// registrado
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
		
		//se agrega en la lista de cuentas en Usuario
		usu.agregarCuenta(cuentaPremium);
		
		todasLasCuentas.put(CVU, cuentaPremium);
				
		return CVU;
	}

	// AGREGA LA CUENTA A TODASLASCUENTAS DE UN USUARIO EXISTENTE Y RETORNA EL CVU
	// DE LA CUENTA CREADA - Lanza error si el usuario no existe o el alias ya esta
	// registrado o la empresa no existe
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
		
		//se agrega en la lista de cuentas en Usuario
		usu.agregarCuenta(cuentaCorporativa);
		
		todasLasCuentas.put(CVU, cuentaCorporativa);
				
		return CVU;
	}

	// DEVUELVE TODAS LAS CUENTAS DEL USUARIO EXISTENTE CON LOS DATOS
	// REPRESENTATIVOS "[Tipo]: [Alias] ([CVU])" - Lanza error si el usuario no
	// existe
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

	// DEVUELVE EL SALDO DE UNA CUENTA - Lanza error si la cuenta no existe
	@Override
	public double obtenerSaldoDisponible(String cvu) {
		
		Cuenta cuenta = todasLasCuentas.get(cvu);
		
		if (cuenta == null) {
			throw new IllegalArgumentException("La cuenta no existe");
		}
		
		double saldoDisponible = cuenta.obtenerSaldo();
			
			
		
		return saldoDisponible;
	}

	// REALIZA LA TRANSFERENCIA DE DINERO DE UNA CUENTA A LA OTRA - Lanza error si
	// alguna de las cuentas no existe
	@Override
	public void realizarTransferencia(String cvuOrigen, String cvuDestino, double monto) {
		
		if(cvuOrigen == null || cvuDestino ==null) {
			throw new IllegalArgumentException();
		}
		
		Cuenta cuentaCVUOrigen = todasLasCuentas.get(cvuOrigen);
		
		Cuenta cuentaCVUDestino = todasLasCuentas.get(cvuDestino);
		
		cuentaCVUOrigen.transferir(cuentaCVUDestino, monto);
		
		// Ver donde generar el numero de operacion 
		Transferencia comprobante = new Transferencia(monto, cuentaCVUOrigen, cuentaCVUDestino);
		
		cuentaCVUOrigen.agregarMovimiento(comprobante);
		
		cuentaCVUDestino.agregarMovimiento(comprobante);
		
		
		historialGlobal.add(comprobante);
		
		 
		
	}

	// HACE UNA INVERSION DE TIPO RENTAFIJA Y RETORNA EL ID - Lanza error si el
	// usuario o la cuenta no existe, o si algun dato es invalido
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
		double tasaInteres = 0.05;
		
		Inversion rentaFija = new RentaFija(idInversion, plazoDias, monto, tasaInteres);
		cuenta.invertir(rentaFija, monto);
		
		OperacionInversion comprobante = new OperacionInversion(monto,rentaFija,cuenta);
		
		
		cuenta.agregarMovimiento(comprobante);
		
		usuario.actualizarTotalInvertido(monto);
		
		historialGlobal.add(comprobante);
		
		return idInversion;
		
	}

	// HACE UNA INVERSION DE TIPO RENTAFIJA Y RETORNA EL ID - Lanza error si el
	// usuario o la cuenta no existe, o si algun dato es invalido
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

	// HACE UNA INVERSION DE TIPO RENTAFIJA Y RETORNA EL ID - Lanza error si el
	// usuario o la cuenta no existe, o si algun dato es invalido
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

	// PRECANCELA UNA INVERSION EN ESTADO ACTIVA - Lanza error si algun dato es
	// invalido, la inversion no existe o no esta activa
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
		
		
		cuenta.precancerlarInversion(idInversion);

	}

	// DEVUELVE EL CVU DEL ALIAS PASADO POR PARAMETRO - Lanza error si el alias no
	// esta registrado
	@Override
	public String consultarCvu(String alias) {
		
		for (Cuenta cuenta : todasLasCuentas.values()) {
			
			if ((cuenta.getAlias().equals(alias))) {
				return cuenta.getCVU();
			}
		}
		
		throw new IllegalArgumentException("Alias no se encuentra registrado");
	}

	// DEVUELVE LAS ACTIVIDADES DEL MAP HISTORIALGOBAL
	/**
	 * Las actividades se deben mostrar con el siguiente formato: - transferencia:
	 * ``` origen: [dni] ([cvu]) destino: [dni] ([cvu]) monto: [monto]
	 * [Aprovado/Rechazado] ``` - inversion: ``` origen: [dni] ([cvu]) desc: [tipo
	 * inversion] monto: [monto] plazo: [plazo] [Aprovado/Rechazado] ```
	 */
	@Override
	public List<String> consultarHistorialGlobal() {
		
		List <String> historial = new LinkedList<>();
		
		for (Actividad actividadGlobal : historialGlobal) {
			
			historial.add(actividadGlobal.toString());
		}
		
		
		return historial;
	}

	// DEVUELVE LA LIST DE ACTIVIDADES DE HISTORIAL DE CUENTA CON EL MISMO FORMATO
	// ANTERIOR - Lanza error si la cuenta no existe
	@Override
	public List<String> consultarHistorialCuenta(String cvu) {
		
		List<String> historialCuenta = new ArrayList();
		
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

	// DEVUELVE EL HISTORIAL DE TODAS LAS ACTIVIDADES DE TODAS LAS CUENTAS DEL
	// USUARIO CON EL MISMO FORMATO ANTERIOR - Lanza error si el usuario no existe
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

	// DEVUELVE EL TOTAL DE TODAS LAS INVERSIONES QUE TIENE UN USUARIO EN SUS
	// CUENTAS - Lanza error si el usuario no existe
	@Override
	public double obtenerTotalInvertido(String dniUsuario) {
		
		Usuario usuario = todosLosUsuarios.get(dniUsuario);
		
		if(usuario == null) {
			throw new IllegalArgumentException("usuario no existe");
		}
		
		double totalInvertido=usuario.obtenerTotalInvertido();
		
		return totalInvertido;
	}

	// DEVUELVE LAS CUENTAS CON MAYOR CANTIDAD DE ACTIVIDADES REGISTRADAS "[Tipo]:
	// [Alias] ([CVU])" - Lanza error si cantidadTop no es positiva
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

	public String procesarInversionesFinalizanHoy(LocalDate hoy) {
		if (hoy == null) {
			throw new IllegalArgumentException("La fecha no puede ser nula");
		}

		StringBuilder resultado = new StringBuilder();

		for (Cuenta cuenta : todasLasCuentas.values()) {
			for (Actividad actividad : cuenta.getHistorial()) {
				if (!(actividad instanceof OperacionInversion operacion)) {
					continue;
				}

				Inversion inversion = operacion.getInversion();
				if (!inversion.estaActivo()) {
					continue;
				}

				LocalDate fechaVencimiento = inversion.fechainicio.plusDays(inversion.getPlazoDias());
				if (hoy.equals(fechaVencimiento)) {
					double montoDevuelto = inversion.calcularResultado(cuenta);
					cuenta.acreditar(montoDevuelto);
					cuenta.agregarMovimiento(new OperacionInversion(montoDevuelto, inversion, cuenta));

					Usuario usuario = todosLosUsuarios.get(cuenta.getDNIPropietario());
					if (usuario != null) {
						usuario.actualizarTotalInvertido(inversion.montoInvertido);
					}

					resultado.append("Inversión ID ").append(inversion.getID())
						.append(" en cuenta ").append(cuenta.getAlias())
						.append(" (" ).append(cuenta.getCVU()).append(") finalizada. Resultado: $")
						.append(montoDevuelto).append("\n");
				}
			}
		}

		if (resultado.length() == 0) {
			return "No hay inversiones para procesar hoy.";
		}

		return resultado.toString();

	}

	private int generarIDInversion() {
		Random random = new Random();
		return random.nextInt(100000) + 1; // Genera un ID entre 1 y 100000
	}

	

}
