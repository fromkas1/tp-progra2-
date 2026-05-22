package ar.edu.ungs.billetera;

public class OperacionInversion extends Inversion {
	private Inversion inversion;
	public OperacionInversion(Inversion inversion) {
		super();
		this.inversion = inversion;
	}
	@Override
	public String toString() {
		return "OperacionInversion [inversion=" + inversion + "]";
	}

	
	

}
