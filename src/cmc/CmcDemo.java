package cmc;
import java.awt.Color;
/**
 * Obtiene la lista de los puntos marcados en la matriz (mapa)
 * Itera los mismos de la siguiente forma:
 * Obtiene los 2 primeros y expande los contiguos entre ambos.
 * Primero expande eje x, segundo expande el eje y.
 * El recorrido es secuencial (conforme al orden de marcado de los puntos en el mapa)
 * Invoca al metodo dibujar para cerrar el ciclo.
 * No contempla las densidades definidas en la matriz (mapa)
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import graficos.Punto;
import mapa.MapaInfo;
import cmc.Nodo;

public class CmcDemo {
	private MapaInfo mapa;
	private CmcImple cmc;
	private static final int COSTO_BASE = 10;
	private static final int DENSIDAD_INQUEBRANTABLE = 4;
	
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
	
	//Si no existe un camino de a a b, devuelve una lista vacía
	private List<Punto> expandirPuntosContiguos(Punto a, Punto b) {
		List<Punto> listaPuntos = new ArrayList<Punto>();
		List<Nodo> nodosCalculados = new ArrayList<Nodo>();
		
		//Agrego el punto de inicio a la lista de nodos
		int costoInicial = COSTO_BASE * (this.mapa.getDensidad(a) + 1);
		nodosCalculados.add(new Nodo(a,a,costoInicial,true));
		Nodo nodoMenorCosto = nodosCalculados.get(0);

		//List<Punto> listaauxi = new ArrayList<Punto>();
		
		//Mientras no llegué al punto final o mientras no visité todos los posibles
		while (nodoMenorCosto != null && !nodoMenorCosto.getPunto().igual(b))
		{
			//listaauxi.add(nodoMenorCosto.getPunto());
			//System.out.println("(" + nodoMenorCosto.getPunto().x + ","+ nodoMenorCosto.getPunto().y+ ")" 
			//+ " " + this.mapa.getDensidad(nodoMenorCosto.getPunto()));

			List<Punto> adyacentes = this.GetAdyacentes(nodoMenorCosto.getPunto());
			
			for (Punto adyacente : adyacentes)
			{
				if (!PuntoFueVisitado(adyacente,nodosCalculados))
				{
					Nodo nodo = GetNodoByPunto(adyacente, nodosCalculados);
					int costo = COSTO_BASE * (this.mapa.getDensidad(adyacente) + 1) +
							nodoMenorCosto.getCostoAcumulado();
					if (nodo.tieneCostoInfinito() || 
							costo < nodo.getCostoAcumulado())
					{
						nodo.SetPredecesor(nodoMenorCosto.getPunto());
						nodo.SetCostoAcumulado(costo);
					}
				}
			}
			
			nodoMenorCosto = GetNodoMenorCosto(nodosCalculados);
			if (nodoMenorCosto != null)
				nodoMenorCosto.Visitar();
		}
		
		if (nodoMenorCosto != null)
		{
			listaPuntos = this.ObtenerCamino(nodosCalculados,nodoMenorCosto);
		}	

		//cmc.dibujarCamino(listaauxi,Color.magenta);
		return listaPuntos;
	}

	private List<Punto> ObtenerCamino(List<cmc.Nodo> nodos, cmc.Nodo nodoFin) {
		List<Punto> puntos = new ArrayList<Punto>();
		Nodo nodoActual = nodoFin;
		puntos.add(nodoActual.getPunto());
		while (!nodoActual.getPunto().igual(nodoActual.getPredecesor()))
		{
			puntos.add(nodoActual.getPunto());
			nodoActual = this.GetNodoByPunto(nodoActual.getPredecesor(), nodos);
		}
		return puntos;
	}

	private cmc.Nodo GetNodoMenorCosto(List<cmc.Nodo> nodosCalculados) {
		Nodo nodoMenorCosto = null;
		for (Nodo nodo : nodosCalculados)
		{
			if (!nodo.fueVisitado() && 
					(nodoMenorCosto == null || 
					nodo.getCostoAcumulado() < nodoMenorCosto.getCostoAcumulado()))
				nodoMenorCosto = nodo;
		}
		
		return nodoMenorCosto;
	}

	private cmc.Nodo GetNodoByPunto(Punto punto, List<cmc.Nodo> nodos) {
		for (Nodo nodo : nodos)
		{
			if (nodo.getPunto().igual(punto))
				return nodo;
		}
		//No está, lo creo y agrego
		Nodo nodo = new Nodo(punto);
		nodos.add(nodo);
		return nodo;
	}

	private boolean PuntoFueVisitado(Punto punto,List<Nodo> nodos) {
		for(Nodo nodo : nodos)
		{
			if (nodo.getPunto().igual(punto))
				return nodo.fueVisitado();
		}
		return false;
	}

	private List<Punto> GetAdyacentes(Punto a) {
		List<Punto> adyacentes = new ArrayList<Punto>();
		List<Punto> puntosEvaluados = new ArrayList<Punto>();
		int x = a.x;
		int y = a.y;
		
		for (int i = x-1; i < x+2; i++)
		{
			for (int o = y-1; o < y+2; o++)
			{
				Punto punto = new Punto(i,o);
				if (!punto.igual(a))
					puntosEvaluados.add(punto);
			}
		}
		for (Punto puntoEvaluado : puntosEvaluados)
		{
			//Si el punto pertenece al mapa y su densidad no es inquebrantable se agrega a adyacentes
			if (puntoEvaluado.x < this.mapa.LARGO && puntoEvaluado.x > 0 &&
				puntoEvaluado.y < this.mapa.ALTO && puntoEvaluado.y > 0 &&
				this.mapa.getDensidad(puntoEvaluado) != DENSIDAD_INQUEBRANTABLE ) 
				adyacentes.add(puntoEvaluado);
		}

		return adyacentes;
	}
}
