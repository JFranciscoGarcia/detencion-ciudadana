package cl.toki.dc.util;

import cl.toki.dc.actor.objetoscolisionables.Cuchillo;
import cl.toki.dc.actor.objetoscolisionables.Perro;
import cl.toki.dc.actor.objetoscolisionables.Tarro;
import cl.toki.dc.interfaces.JuegoCore;
import cl.toki.dc.interfaces.Objeto;

import com.badlogic.gdx.math.MathUtils;

public class ObjetoUtil {

	public static Objeto getObjetoSimpleRnd(JuegoCore escenario) {
		Objeto objeto;
		if (MathUtils.randomBoolean()) {
			objeto = new Tarro(escenario);
		} else {
			objeto = new Perro(escenario);
		}
		return objeto;

	}

	public static Objeto getObjetoMedioRnd(JuegoCore escenario) {
		Objeto objeto;
		if (MathUtils.randomBoolean()) {
			objeto = new Cuchillo(escenario);
		} else {
			objeto = new Cuchillo(escenario);
		}
		return objeto;

	}

	public static Objeto getObjetoAltoRnd(JuegoCore escenario) {
		Objeto objeto;
		if (MathUtils.randomBoolean()) {
			objeto = new Cuchillo(escenario);
		} else {
			objeto = new Cuchillo(escenario);
		}
		return objeto;

	}

}
