
public abstract class Trabajo {
	protected String codigo;
	protected String nombrestudio;
	protected String nombreproyecto;
	protected double horasestimadas;
	protected String horario;
	protected String estado;
	protected int intentos;

	public Trabajo(String codigo, String nombrestudio, String nombreproyecto, double horasestimadas, String horario) {
		this.codigo = codigo;
		this.nombrestudio = nombrestudio;
		this.nombreproyecto = nombreproyecto;
		this.horasestimadas = horasestimadas;
		this.horario = horario;
		this.estado = "En cola";
		this.intentos = 0;
	}

	public abstract double calcularCosto();

	public abstract int calcularTiempoEntrega();

	public abstract int getMaxIntentos();

	public abstract boolean GPU();

	public abstract String getCategoria();

	public String getCodigo(){
		return codigo;
	}

	public double getHorasEstimadas() {
		return horasestimadas;
	}

	public String getEstado() {
		return estado;
	}

	public void asignar() {
		if(estado.equals("En cola")){
			estado = "Asignado";
		}
	}

	public void retirar() {
		if (estado.equals("Asignado")){
			estado = "En cola";
		}
	}

	public void iniciar() {
		if(estado.equals("Asignado") && intentos < getMaxIntentos()){
			estado = "En proceso";
			intentos++;
		}
	}

	public void registrarFallo() {
		if(estado.equals("En proceso")){
			if (intentos >= getMaxIntentos()){
				estado = "Cancelado";
			}
			else {
				estado = "En cola";
			}
		}
	}

	public void completar() {
		if(estado.equals("En proceso")){
			estado = "Completado";
		}
	}

	public int getIntentos() {
		return intentos;
	}
}
