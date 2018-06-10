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

	public Punto getPunto() {
		return this.PuntoActual;
	}

}
