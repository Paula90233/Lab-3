
public class Main {
	public static void main(String[] args) {
		RenderGT sistema = new RenderGT();
		Imagenfija imagen = new Imagenfija("I001", "EstudioA", "Proyecto1", 2, "Diurno");
        Animacion animacion = new Animacion("A001", "EstudioB", "Proyecto2", 9.5, "Diurno", 610);
        Postprodu postprodu = new Postprodu("P001", "EstudioC", "Proyecto3", 9.5, "Diurno", 610, 1800);
        Simulacionfisica simulacion = new Simulacionfisica("S001", "EstudioD", "Proyecto4", 5, "Nocturno");
        simulacion.setMallaresol(20);
        System.out.println("Cotizacion imagen: Q" + sistema.cotizar(imagen));
        System.out.println("Cotizacion animacion: Q" + sistema.cotizar(animacion));
        System.out.println("Cotizacion postproduccion: Q" + sistema.cotizar(postprodu));
        System.out.println("Cotizacion simulacion: Q" + sistema.cotizar(simulacion));
        sistema.registrarTrabajo(imagen);
        sistema.registrarTrabajo(animacion);
        sistema.registrarTrabajo(postprodu);
        sistema.registrarTrabajo(simulacion);
        Nodo nodo1 = new Nodo("N001", 30, false);
        Nodo nodo2 = new Nodo("N002", 20, true);
        sistema.registrarNodo(nodo1);
        sistema.registrarNodo(nodo2);
        sistema.crearLote(nodo1);
        sistema.crearLote(nodo2);
        Lote lote1 = sistema.getLotes().get(0);
        Lote lote2 = sistema.getLotes().get(1);
        sistema.asignarTrabajo(imagen, lote1);
        sistema.asignarTrabajo(animacion, lote1);
        sistema.asignarTrabajo(postprodu, lote2);
        sistema.asignarTrabajo(simulacion, lote2);
        sistema.iniciarLote(lote1);
        sistema.iniciarLote(lote2);
        sistema.registrarResultado(lote1, imagen, true);
        sistema.registrarResultado(lote1, animacion, false);
        sistema.registrarResultado(lote2, postprodu, true);
        sistema.registrarResultado(lote2, simulacion, true);
        System.out.println(sistema.mostrarReporte());
    }
}
