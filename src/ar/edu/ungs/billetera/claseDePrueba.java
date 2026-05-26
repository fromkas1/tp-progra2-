package ar.edu.ungs.billetera;

public class claseDePrueba {

	public static void main(String[] args) {
		Billetera billetera = new Billetera();
		
		
		System.out.println("[1] Registrando usuarios y empresas...");
		billetera.registrarUsuario("11111111", "Ana Garcia", "(011) 4444-6666", "aGarcia@mockMail.com");
        billetera.registrarUsuario("22222222", "Carlos Lopez", "(011) 4444-5555", "cLopes@otroMock.com");
		
        System.out.println("Usuarios y empresas registrados con éxito.\n");
		
        
        
        String cvuAnaRegular = billetera.crearCuentaRegular("11111111", "ana.regular");
        String cvuCarlosRegular = billetera.crearCuentaRegular("22222222", "carlos.regular");
		billetera.crearCuentaRegular("22222222", "carlos.regular2" );
		
		
		
        System.out.println("Obtener cuentas de usuario por DNI: ");
        System.out.println(billetera.obtenerCuentas("22222222"));
        System.out.println("Obtener saldo disponible en billetera: "+billetera.obtenerSaldoDisponible(cvuCarlosRegular));
        
        Cuenta cvu=billetera.consultarCvu("ana.regular");
        
	}
}
