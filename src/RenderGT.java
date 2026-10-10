
import java.util.ArrayList;
public class RenderGT {
	private ArrayList<Trabajo> trabajos;
	private ArrayList<Nodo> nodos;
	private ArrayList<Lote> lotes;
	private double ingresosregistro;
	private double ingresosreintentos;

	public RenderGT(){
		trabajos = new ArrayList<Trabajo>();
		nodos = new ArrayList<Nodo>();
		lotes = new ArrayList<Lote>();
		ingresosregistro = 0;
		ingresosreintentos = 0;
	}
	
	public void registrarTrabajo(Trabajo Trabajo) {
		if(Trabajo == null){
			return;
		}
		for(Trabajo existente : trabajos){
			if(existente.getCodigo().equals(Trabajo.getCodigo())){
				return;
			}
			trabajos.add(Trabajo);
			ingresosregistro += Trabajo.calcularCosto();
		}
	}

	public double cotizar(Trabajo Trabajo) {
		return Trabajo.calcularCosto();
	}

	public void registrarNodo(Nodo Nodo){
		if(Nodo == null){
			return;
		}
		for(Nodo existente : nodos){
			if(existente.getCodigo().equals(Nodo.getCodigo())){
				return;
			}
		}
		nodos.add(Nodo);
	}

	public void crearLote(Nodo Nodo) {
		if(Nodo!= null && Nodo.disponibilidad()){
			boolean registrado = nodos.contains(Nodo);
			if(!registrado){
				nodos.add(Nodo);
			}
			Lote Lote = new Lote(Nodo);
			lotes.add(Lote);
		}
	}

	public void asignarTrabajo(Trabajo Trabajo, Lote Lote) {
		if(Trabajo != null && Lote != null){
			Lote.agregarTrabajo(Trabajo);
		}
	}

	public void iniciarLote(Lote Lote) {
        if (Lote == null || !lotes.contains(Lote)) {
            return;
        }
        if (!Lote.getEstado().equals("Pendiente") || Lote.getTrabajos().isEmpty()) {
            return;
        }
        for (Trabajo Trabajo : Lote.getTrabajos()) {
            if (Trabajo.getIntentos() > 0) {
                ingresosreintentos += 25;
            }
        }
        Lote.iniciar();
    }

	public void registrarResultado(Lote Lote, Trabajo Trabajo, boolean completado) {
		if(Lote != null && Trabajo != null){
			Lote.registrarResultado(Trabajo, completado);
		}
	}

	public double getIngresosRegistro() {
		return ingresosregistro;
	}

	public double getIngresosReintentos() {
		return ingresosreintentos;
	}

	public ArrayList<Trabajo> getTrabajos(){
		return trabajos;
	}

	public ArrayList<Nodo> getNodos(){
		return nodos;
	}

	public ArrayList<Lote> getLotes(){
		return lotes;
	}

	public String mostrarReporte() {
		String reporte = "Reporte de RenderGT\n";
		String [] categorias = {"Imagen fija", "Animación", "Animación postproducción", "Simulación física"};
		String[] estados = {"En cola", "Asignado", "En proceso", "Completado", "Fallo"};
		reporte += "Trabajos por categoría y estado:\n";
		for(String categoria : categorias){
			reporte += "\n" + categoria + ":\n";
			for(String estado : estados){
				int cantidad = 0;
				for(Trabajo Trabajo : trabajos){
					if(Trabajo.getCategoria().equals(categoria) && Trabajo.getEstado().equals(estado)){
						cantidad++;
					}
				}
				reporte += estado + ": " + cantidad + "\n";
			}
		}
		reporte += "\nINGRESOS:\n";
        reporte += "Registro: Q" + ingresosregistro + "\n";
        reporte += "Reintentos: Q" + ingresosreintentos + "\n";
        reporte += "\nINGRESOS POR CATEGORIA:\n";
        for (String categoria : categorias) {
            double ingreso = 0;
            for (Trabajo Trabajo : trabajos) {
                if (Trabajo.getCategoria().equals(categoria)) {
                    ingreso += Trabajo.calcularCosto();
                }
            }
            reporte += categoria + ": Q" + ingreso + "\n";
        }
        reporte += "\nLOTES ABIERTOS:\n";
        for (Lote Lote : lotes) {
            if (!Lote.getEstado().equals("CERRADO")) {
                reporte += "Nodo: " + Lote.getNodo().getCodigo() + "\n";
                reporte += "Horas asignadas: " + Lote.calcularHorasAsignadas() + "\n";
				reporte += "Capacidad disponible: " + Lote.calcularCapacidadDisponible() + "\n";
            }
        }
        return reporte;
	}
}
