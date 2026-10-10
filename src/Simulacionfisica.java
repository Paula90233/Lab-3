
public class Simulacionfisica extends Trabajo {
	private int mallaresol;

	public Simulacionfisica(String codigo, String nombrestudio, String nombreproyecto, double horasestimadas, String horario) {
		super(codigo, nombrestudio, nombreproyecto, horasestimadas, horario);
		this.mallaresol = 1;
	}

	public void setMallaresol(int mallaresol){
		if(mallaresol >= 1 && mallaresol <=50){
			this.mallaresol = mallaresol;
		}
	}

	public boolean horarioValido(){
		return horario.equals("Nocturno");
	}

	public double calcularCosto() {
		return 40 + 22 * horasestimadas + 65;
	}

	public int calcularTiempoEntrega() {
		return 18;
	}

	public int getMaxIntentos() {
		return 1;
	}

	public boolean GPU() {
		return true;
	}

	public String getCategoria(){
		return "Simulación física";
	}
}
