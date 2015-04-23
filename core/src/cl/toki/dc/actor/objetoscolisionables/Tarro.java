package cl.toki.dc.actor.objetoscolisionables;

import java.util.Random;

import cl.toki.dc.interfaces.JuegoCore;
import cl.toki.dc.interfaces.Objeto;
import cl.toki.dc.util.Constantes;
import cl.toki.dc.util.FrameUtil;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;

/**
 *
 * @author Francisco Garcia
 */
public class Tarro extends Objeto {

	public Tarro(JuegoCore escenario) {
		super(escenario, Constantes.ANCHO_MAXIMO, Constantes.ALTURA_PISO - 5,50);
		verificarAsignarVelocidad();
	}

	@Override
	public Animation getAnimacion() {
		Random random = new Random();
		float animacionR = (random.nextFloat() / 0.3f > 0.3f) ? 0.3f : random
				.nextFloat() / 0.3f;
		return new Animation(animacionR, FrameUtil.cortarTextura("tarro.png",
				4, 1, false));
	}

	@Override
	public void realizarAnimacionColision() {

		ParallelAction paraleloActions = new ParallelAction();
		RotateToAction rotateAction = new RotateToAction();
		MoveToAction movida = new MoveToAction();
		movida.setInterpolation(Interpolation.bounceOut);

		setOrigin(getWidth() / 2, (getHeight() / 2) - 5);
		rotateAction.setRotation(-90);
		rotateAction.setDuration(0.2f);
		movida.setPosition(this.getX() + (50 * 1), getY());
		movida.setDuration(0.8f);

		paraleloActions.addAction(rotateAction);
		//paraleloActions.addAction(movida);

		addAction(paraleloActions);
	}

	@Override
	public void incrementarVel() {
		verificarAsignarVelocidad();
	}

	@Override
	public void decrementarVel() {
		verificarAsignarVelocidad();
	}

	@Override
	public void resetearVel() {
		verificarAsignarVelocidad();
	}

	private void verificarAsignarVelocidad() {
		switch (escenario.pinera.nivelActualVel) {
		case uno:
			velocidad = 5;
			break;
		case dos:
			velocidad = 10;
			break;
		case tres:
			velocidad = 15;
			break;
		}
	}

}
