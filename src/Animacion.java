
public class Animacion extends Trabajo {
	protected int fotogramas;

	public Animacion(String codigo, String nombrestudio, String nombreproyecto, double horasestimadas, String horario) {
		super(codigo, nombrestudio, nombreproyecto, horasestimadas, horario);
		this.fotogramas = 1;
	}

	public Animacion(String codigo, String nombrestudio, String nombreproyecto, double horasestimadas, String horario, int fotogramas) {
		super(codigo, nombrestudio, nombreproyecto, horasestimadas, horario);
		this.fotogramas = fotogramas;
	}

	public void setFotogramas(int fotogramas){
		if(fotogramas > 0){
			this.fotogramas = fotogramas;
		}
	}
	
	public double calcularHorasFotogramas() {
		return fotogramas * 0.02;
	}

	public int calcularHorasFactura() {
		double horas = horasestimadas;
		double horasfotogramas = calcularHorasFotogramas();
		if(horasfotogramas > horas){
			horas = horasfotogramas;
		}
		int horasfactura = (int) horas;
		if(horas > horasfactura){
			horasfactura++;
		}
		return horasfactura;
	}

	public double calcularCosto() {
		double tarifa;
		if(horario.equals("Diurno")){
			tarifa = 60;
		}
		else{
			tarifa = 40;
		}
		return tarifa + 15 * calcularHorasFactura();
	}

	public int calcularTiempoEntrega() {
		if(horario.equals("Diurno")){
			return 24;
		}
		else{
			return 36;
		}
	}

	public int getMaxIntentos() {
		return 3;
	}

	public boolean GPU() {
		return false;
	}

	public String getCategoria(){
		return "Animación";
	}
}
