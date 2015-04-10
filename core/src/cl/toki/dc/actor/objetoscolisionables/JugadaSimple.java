package cl.toki.dc.actor.objetoscolisionables;

import java.util.ArrayList;
import java.util.List;

import cl.toki.dc.interfaces.Escenario;
import cl.toki.dc.interfaces.Objeto;
import cl.toki.dc.util.ObjetoUtil;

/**
 * La clase contiene objetos colisionables simples separados por una distancia
 * de 500px. Los objetos simples solo se saltan
 * 
 * @author Francisco García
 *
 */

public class JugadaSimple extends Jugada {

	private int distanciaObjetos = 500;
	private List<Objeto> objetos = new ArrayList<Objeto>();

	/**
	 * @param escenario
	 *            Corresponde al Escenario donde los objetos que componen la
	 *            jugada serán dibujados
	 * @param cantidadObjetos
	 *            Corresponde al número de objetos que componen la la jugada
	 */
	public JugadaSimple(Escenario escenario, int cantidadObjetos) {
		
		for (int i = 0; i < cantidadObjetos; i++) {
			objetos.add(ObjetoUtil.getObjetoSimpleRnd(escenario));
		}

		for (int i = 1; i < objetos.size(); i++) {
			float anteriorX = objetos.get(i - 1).getX();
			objetos.get(i).setX(anteriorX + distanciaObjetos);
		}

	}


	@Override
	public List<Objeto> getObjetosColisionables() {
		return objetos;
	}
}
