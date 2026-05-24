package ar.edu.ungs.billetera;

import java.util.HashMap;
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
		// TODO Auto-generated method stub

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
		
		String CVU = generarCVU();
		
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
		// TODO Auto-generated method stub
		return null;
	}

	// AGREGA LA CUENTA A TODASLASCUENTAS DE UN USUARIO EXISTENTE Y RETORNA EL CVU
	// DE LA CUENTA CREADA - Lanza error si el usuario no existe o el alias ya esta
	// registrado o la empresa no existe
	@Override
	public String crearCuentaCorporativa(String dniUsuario, String alias, String cuitEmpresa) {
		// TODO Auto-generated method stub
		return null;
	}

	// DEVUELVE TODAS LAS CUENTAS DEL USUARIO EXISTENTE CON LOS DATOS
	// REPRESENTATIVOS "[Tipo]: [Alias] ([CVU])" - Lanza error si el usuario no
	// existe
	@Override
	public List<String> obtenerCuentas(String dniUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	// DEVUELVE EL SALDO DE UNA CUENTA - Lanza error si la cuenta no existe
	@Override
	public double obtenerSaldoDisponible(String cvu) {
		// TODO Auto-generated method stub
		return 0;
	}

	// REALIZA LA TRANSFERENCIA DE DINERO DE UNA CUENTA A LA OTRA - Lanza error si
	// alguna de las cuentas no existe
	@Override
	public void realizarTransferencia(String cvuOrigen, String cvuDestino, double monto) {
		// TODO Auto-generated method stub

	}

	// HACE UNA INVERSION DE TIPO RENTAFIJA Y RETORNA EL ID - Lanza error si el
	// usuario o la cuenta no existe, o si algun dato es invalido
	@Override
	public int realizarInversionRentaFija(String dni, String cvu, double monto, int plazoDias) {
		// TODO Auto-generated method stub
		return 0;
	}

	// HACE UNA INVERSION DE TIPO RENTAFIJA Y RETORNA EL ID - Lanza error si el
	// usuario o la cuenta no existe, o si algun dato es invalido
	@Override
	public int realizarInversionDivisa(String dni, String cvu, double monto, int plazoDias, String divisa,
			double tasa) {
		// TODO Auto-generated method stub
		return 0;
	}

	// HACE UNA INVERSION DE TIPO RENTAFIJA Y RETORNA EL ID - Lanza error si el
	// usuario o la cuenta no existe, o si algun dato es invalido
	@Override
	public int realizarInversionLiquidez(String dni, String cvu, double monto, int plazoDias) {
		// TODO Auto-generated method stub
		return 0;
	}

	// PRECANCELA UNA INVERSION EN ESTADO ACTIVA - Lanza error si algun dato es
	// invalido, la inversion no existe o no esta activa
	@Override
	public void precancelarInversion(String dni, String cvu, int idInversion) {
		// TODO Auto-generated method stub

	}

	// DEVUELVE EL CVU DEL ALIAS PASADO POR PARAMETRO - Lanza error si el alias no
	// esta registrado
	@Override
	public String consultarCvu(String alias) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	// DEVUELVE LA LIST DE ACTIVIDADES DE HISTORIAL DE CUENTA CON EL MISMO FORMATO
	// ANTERIOR - Lanza error si la cuenta no existe
	@Override
	public List<String> consultarHistorialCuenta(String cvu) {
		// TODO Auto-generated method stub
		return null;
	}

	// DEVUELVE EL HISTORIAL DE TODAS LAS ACTIVIDADES DE TODAS LAS CUENTAS DEL
	// USUARIO CON EL MISMO FORMATO ANTERIOR - Lanza error si el usuario no existe
	@Override
	public List<String> consultarHistorialUsuario(String dniUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	// DEVUELVE EL TOTAL DE TODAS LAS INVERSIONES QUE TIENE UN USUARIO EN SUS
	// CUENTAS - Lanza error si el usuario no existe
	@Override
	public double obtenerTotalInvertido(String dniUsuario) {
		// TODO Auto-generated method stub
		return 0;
	}

	// DEVUELVE LAS CUENTAS CON MAYOR CANTIDAD DE ACTIVIDADES REGISTRADAS "[Tipo]:
	// [Alias] ([CVU])" - Lanza error si cantidadTop no es positiva
	@Override
	public List<String> cuentasConMayorVolumen(int cantidadTop) {
		// TODO Auto-generated method stub
		return null;
	}

	private String generarCVU() {
		
		Random random = new Random();
		
		StringBuilder sb = new StringBuilder(22);
		
		// Primeros 6 digitos 000000
		sb.append("000000");
		
		// Resto numeros aletorios
		for (int i=0; i<16 ;i++) {
			
			sb.append(random.nextInt(10));
		}
		
		return sb.toString();
	}

}
