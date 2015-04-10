package cl.toki.dc.actor.objetoscolisionables;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.MathUtils;

import cl.toki.dc.interfaces.Escenario;
import cl.toki.dc.interfaces.Objeto;
import cl.toki.dc.util.ObjetoUtil;

/**
 * La clase contiene objetos colisionables simples y medios separados por una
 * distancia de 300px. Los objetos se saltan o se esquivan agachandose
 * 
 * @author Francisco García
 *
 */
public class JugadaMedia extends Jugada {
	private int distanciaObjetos = 300;
	private List<Objeto> objetos = new ArrayList<Objeto>();

	/**
	 * @param escenario
	 *            Corresponde al Escenario donde los objetos que componen la
	 *            jugada serán dibujados
	 * @param cantidadObjetos
	 *            Corresponde al número de objetos que componen la la jugada
	 */
	public JugadaMedia(Escenario escenario, int cantidadObjetos) {

		for (int i = 0; i < cantidadObjetos; i++) {
			if (MathUtils.randomBoolean()) {
				objetos.add(ObjetoUtil.getObjetoMedioRnd(escenario));
			} else {
				objetos.add(ObjetoUtil.getObjetoSimpleRnd(escenario));
			}
		}
		
		objetos.add(new Completo(escenario));

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
