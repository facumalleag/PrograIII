package cmc;

import graficos.Punto;

public class Nodo {

	private Punto PuntoActual;
	private Punto PuntoPredecesor;
	private int CostoAcumulado;
	private boolean FueVisitado;

	public Nodo(Punto puntoActual, Punto puntoPredecesor, int costoAcumulado, boolean fueVisitado) {
		this.PuntoActual = puntoActual;
		this.PuntoPredecesor = puntoPredecesor;
		this.CostoAcumulado = costoAcumulado;
		this.FueVisitado = fueVisitado;
	}

	public Nodo(Punto punto) {
		this.PuntoActual = punto;
	}

	public Punto getPunto() {
		return this.PuntoActual;
	}
	
	public void SetPredecesor(Punto predecesor){
		this.PuntoPredecesor = predecesor;
	}
	
	public void SetCostoAcumulado(int costo){
		this.CostoAcumulado = costo;
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

}
