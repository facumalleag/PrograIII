package cmc;
/**
 * Subclase de CmcSC que implementa a CmcTDA
 * El m�todo run es invocado por el bot�n iniciar.
 * Hereda de CmcSC:
 * 		Objeto MapaInfo
 * 		M�todo dibujarCamino(List<Punto> camino)
 * Debe implementar:
 * 		M�todo run(MapaInfo mapa)
 */

import java.awt.Color;
import java.util.List;

import graficos.Punto;
import mapa.MapaInfo;
import tda.CmcSC;

public class CmcImple extends CmcSC {
	
	private MapaInfo mapa;

	public void run(MapaInfo mapa) {
		this.mapa = mapa;
		new CmcDemo(mapa, this);
	}

	public void dibujarCamino(List<Punto> listaPuntos, Color red) {
		// TODO Auto-generated method stub
		
	}
}
