package cmc;
import java.lang.Exception;
/**
 * Subclase de CmcSC que implementa a CmcTDA
 * El m�todo run es invocado por el bot�n iniciar.
 * Hereda de CmcSC:
 * 		Objeto MapaInfo
 * 		M�todo dibujarCamino(List<Punto> camino)
 * Debe implementar:
 * 		M�todo run(MapaInfo mapa)
 */

import mapa.MapaInfo;
import tda.CmcSC;

public class CmcImple extends CmcSC {
	
	private MapaInfo mapa;
	
	public void run(MapaInfo mapa) {
		
		try {this.mapa = mapa;
		new CmcDemo(mapa, this);}
		catch(Exception e) {
			mapa.enviarMensaje("No se encontro camino");
		}
	}
	}

	