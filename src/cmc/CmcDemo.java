package cmc;
import java.awt.Color;
/**
 * Obtiene la lista de los puntos marcados en la matriz (mapa)
 * Itera los mismos de la siguiente forma:
 * Obtiene los 2 primeros y expande los contiguos entre ambos.
 * Primero expande eje x, segundo expande el eje y.
 * El recorrido es secuencial (conforme al orden de marcado de los puntos en el mapa)
 * Invoca al método dibujar para cerrar el ciclo.
 * No contempla las densidades definidas en la matriz (mapa)
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import graficos.Punto;
import mapa.MapaInfo;

public class CmcDemo {
	private MapaInfo mapa;
	private CmcImple cmc;
	
	public CmcDemo(MapaInfo mapa, CmcImple cmc) {
		this.mapa = mapa;
		this.cmc = cmc;
		demoObtenerCamino();
	}
	
	private void demoObtenerCamino() {
		Punto a = null, b = null;	
		Iterator<Punto> iter = mapa.getPuntos().iterator();
		List<Punto> listaPuntos = null;
		if (iter.hasNext()) {
			a = iter.next();
			if(iter.hasNext()) b = iter.next();
			else return;
			List<Punto> aux = expandirPuntosContiguos(a, b);
			listaPuntos = aux;
			cmc.dibujarCamino(listaPuntos,Color.red);
			mapa.enviarMensaje("Camino minimo: " + listaPuntos.size() + " puntos");
		}
	}
	
	private List<Punto> expandirPuntosContiguos(Punto a, Punto b) {
		List<Punto> listaPuntos = new ArrayList<Punto>();
		if (a.x < b.x) {
			for(int x = a.x ; x < b.x; x++) {
				listaPuntos.add(new Punto(x, a.y));
			}
		} else {
			for(int x = a.x ; x > b.x; x--) {
				listaPuntos.add(new Punto(x, a.y));
			}
		}
		if (a.y < b.y) {
			for(int y = a.y ; y < b.y; y++) {
				listaPuntos.add(new Punto(b.x, y));
			}
		} else {
			for(int y = a.y ; y > b.y; y--) {
				listaPuntos.add(new Punto(b.x, y));
			}
		}
		return listaPuntos;
	}
}
