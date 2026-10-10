import java.util.ArrayList;

public class Lote {
	private String estado;
	private Nodo nodo;
	private ArrayList<Trabajo> trabajos;
	private ArrayList<Trabajo> resultados;

	public Lote(Nodo Nodo) {
		this.estado = "Pendiente";
		this.nodo = Nodo;
		this.trabajos = new ArrayList<Trabajo>();
		this.resultados = new ArrayList<Trabajo>();
		if (nodo != null && nodo.disponibilidad()){
			nodo.asignarLote(this);
		}
	}

	public boolean agregarTrabajo(Trabajo Trabajo) {
		if (Trabajo == null || nodo == null) {
        	return false;
    	}
		if (!estado.equals("Pendiente")) {
			return false;
		}
		if (!Trabajo.getEstado().equals("En cola")) {
			return false;
		}
		if (!nodo.puedeEjecutar(Trabajo)) {
			return false;
		}
		if (trabajos.contains(Trabajo)) {
			return false;
		}
		if (calcularHorasAsignadas() + Trabajo.getHorasEstimadas() > nodo.getCapacidadhoras()) {
			return false;
		}
		trabajos.add(Trabajo);
		Trabajo.asignar();
		return true;
	}
	
	public void iniciar() {
		if(estado.equals("Pendiente") && !trabajos.isEmpty()){
			estado = "En proceso";
			for(Trabajo Trabajo : trabajos){
				Trabajo.iniciar();
			}
		}
	}

	public void registrarResultado(Trabajo Trabajo, boolean completado) {
		if(!estado.equals("En proceso")){
			return;
		}
		if (!trabajos.contains(Trabajo)) {
            return;
        }

		if(!Trabajo.getEstado().equals("En proceso")){
			return;
		}

        if (completado) {
            Trabajo.completar();
        } 
		else {
            Trabajo.registrarFallo();
        }
        resultados.add(Trabajo);
        if (todosTienenResultado()) {
            cerrado();
        }
	}

	public boolean todosTienenResultado(){
		for(Trabajo Trabajo : trabajos){
			if(Trabajo.getEstado().equals("En proceso")){
				return false;
			}
		}
		return true;
	}

	public void cerrado() {
		estado = "Cerrado";
		if(nodo != null){
			nodo.liberar();
		}
	}

	public double calcularHorasAsignadas() {
		double horas = 0;
		for(Trabajo Trabajo : trabajos){
			horas += Trabajo.getHorasEstimadas();
		}
		return horas;
	}

	public double calcularCapacidadDisponible() {
		return nodo.getCapacidadhoras() - calcularHorasAsignadas();
	}
	
	public String getEstado(){
		return estado;
	}

	public Nodo getNodo(){
		return nodo;
	}

	public ArrayList<Trabajo> getTrabajos(){
		return trabajos;
	}
}
