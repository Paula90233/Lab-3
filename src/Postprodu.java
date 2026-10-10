
public class Postprodu extends Animacion {
	private double recursoslicencia;

	public Postprodu(String codigo, String nombrestudio, String nombreproyecto, double horasestimadas, String horario, int fotogramas, double recursoslicencia) {
		super(codigo, nombrestudio, nombreproyecto, horasestimadas, horario, fotogramas);
		this.recursoslicencia = recursoslicencia;
	}

	public Postprodu(String codigo, String nombrestudio, String nombreproyecto, double horasestimadas, String horario) {
		super(codigo, nombrestudio, nombreproyecto, horasestimadas, horario);
		this.recursoslicencia = 0;
	}

	public double calcularCosto() {
		return super.calcularCosto() + 80 + recursoslicencia * 0.05;
	}

	public int calcularTiempoEntrega() {
		return super.calcularTiempoEntrega() + 12;
	}
	
	public int getMaxIntentos() {
		return 2;
	}

	public boolean GPU() {
		return false;
	}

	public String getCategoria(){
		return "Animación postproducción";
	}
}
