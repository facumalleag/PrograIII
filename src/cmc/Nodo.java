package cmc;

import java.util.Comparator;

import graficos.Punto;

public class Nodo {

	private Punto PuntoActual;
	private Punto PuntoPredecesor;
	private int CostoAcumulado;
	private boolean FueVisitado;
	//El costo infinito sirve para diferenciar a aquellos que tienen costo 0 porque no fueron 
	//explorados o porque su costo realmente es 0
	private boolean CostoInfinito;

	public Nodo(Punto puntoActual, Punto puntoPredecesor, int costoAcumulado, boolean fueVisitado) {
		this.PuntoActual = puntoActual;
		this.PuntoPredecesor = puntoPredecesor;
		this.CostoAcumulado = costoAcumulado;
		this.FueVisitado = fueVisitado;
		this.CostoInfinito = false;
	}

	public Nodo(Punto punto) {
		this.PuntoActual = punto;
		this.CostoAcumulado = 0;
		this.CostoInfinito = true;
		this.FueVisitado = false;
	}

	public Punto getPunto() {
		return this.PuntoActual;
	}
	
	public void SetPredecesor(Punto predecesor){
		this.PuntoPredecesor = predecesor;
	}
	
	public void SetCostoAcumulado(int costo){
		this.CostoAcumulado = costo;
		this.CostoInfinito = false;
	}
	
	public boolean fueVisitado() {
		return this.FueVisitado;
	}

	public int getCostoAcumulado() {
		return this.CostoAcumulado;
	}

	public Punto getPredecesor() {
		return this.PuntoPredecesor;
	}

	public void Visitar() {
		this.FueVisitado = true;		
	}

	public boolean tieneCostoInfinito() {
		return this.CostoInfinito;
	}
	public int getDistancia(int x, int y) {
		int distancia= Math.abs(this.PuntoActual.x-x)+Math.abs(this.PuntoActual.y-y);
		
		return distancia;
		
	}

}

class cmpNodos implements Comparator<Nodo> {
    @Override
    public int compare(Nodo n1, Nodo n2) {
        if (n1.getCostoAcumulado() < n2.getCostoAcumulado())
        	return -1;
        else
        	return 1;
    }
}
