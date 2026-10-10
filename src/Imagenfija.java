
public class Imagenfija extends Trabajo {
	public Imagenfija(String codigo, String nombrestudio, String nombreproyecto, double horasestimadas, String horario) {
		super(codigo, nombrestudio, nombreproyecto, horasestimadas, horario);
	}

	public double calcularCosto() {
		if(horario.equals("Diurno")){
			return 45;
		}
		else{
			return 30;
		}
	}

	public int calcularTiempoEntrega() {
		if(horario.equals("Diurno")){
			return 4;
		}
		else{
			return 10;
		}
	}

	public int getMaxIntentos() {
		return 3;
	}

	public boolean GPU() {
		return false;
	}

	public String getCategoria(){
		return "Imagen fija";
	}
}
