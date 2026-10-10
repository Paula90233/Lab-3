
public class Nodo {
	private String codigo;
	private double capacidadhoras;
	private boolean GPU;
	private Lote loteactual;

	public Nodo(String codigo, double capacidadhoras, boolean GPU, Lote loteactual) {
		this.codigo = codigo;
		this.capacidadhoras = capacidadhoras;
		this.GPU = GPU;
		this.loteactual = loteactual;
	}

	public Nodo(String codigo, double capacidadhoras, boolean GPU){
		this.codigo = codigo;
		this.capacidadhoras = capacidadhoras;
		this.GPU = GPU;
		this.loteactual = null;
	}

	public boolean disponibilidad() {
		return loteactual == null;
	}

	public boolean puedeEjecutar(Trabajo Trabajo) {
		if(Trabajo == null){
			return false;
		}
		if(Trabajo.GPU() && !GPU){
			return false;
		}
		if(!Trabajo.getEstado().equals("En cola")){
			return false;
		}
		return false;
	}

	public void asignarLote(Lote Lote) {
		if(disponibilidad() && Lote != null){
			loteactual = Lote;
		}
	}

	public void liberar() {
		loteactual = null;
	}

	public String getCodigo(){
		return codigo;
	}

	public double getCapacidadhoras(){
		return capacidadhoras;
	}

	public boolean getGPU(){
		return GPU;
	}
}
