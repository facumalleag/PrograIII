package cmc;
/**
 * Subclase de CmcSC que implementa a CmcTDA
 * El método run es invocado por el botón iniciar.
 * Hereda de CmcSC:
 * 		Objeto MapaInfo
 * 		Método dibujarCamino(List<Punto> camino)
 * Debe implementar:
 * 		Método run(MapaInfo mapa)
 */

import mapa.MapaInfo;
import tda.CmcSC;

public class CmcImple extends CmcSC {
	
	public void run(MapaInfo mapa) {
		this.mapa = mapa;
		new CmcDemo(mapa, this);
	}
}
