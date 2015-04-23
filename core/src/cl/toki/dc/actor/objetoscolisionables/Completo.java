package cl.toki.dc.actor.objetoscolisionables;

import java.util.Random;

import cl.toki.dc.interfaces.JuegoCore;
import cl.toki.dc.interfaces.Objeto;
import cl.toki.dc.util.Constantes;
import cl.toki.dc.util.FrameUtil;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;

/**
 *
 * @author Francisco Garcia
 */
public class Completo extends Objeto {

	public Completo(JuegoCore escenario) {
		super(escenario, Constantes.ANCHO_MAXIMO, Constantes.ALTO_MAXIMO / 4);
		verificarAsignarVelocidad();
		esObjetodePoder = true;
	}

	@Override
	public Animation getAnimacion() {

//		SequenceAction seq = new SequenceAction();
//
//		MoveToAction moveAction = new MoveToAction();
//		moveAction.setY(getY()-10);
//		moveAction.setDuration(1f);
//		
//		MoveToAction moveAction1 = new MoveToAction();
//		moveAction1.setY(getY()+10);
//		moveAction1.setDuration(1f);
//		
//		seq.addAction(moveAction);
//		seq.addAction(moveAction1);
		
//		RepeatAction rep = new RepeatAction();
//		rep.setCount(RepeatAction.FOREVER);
//		rep.setAction(seq);
//		
//		addAction(rep);
		
		Random random = new Random();
		float animacionR = (random.nextFloat() / 0.3f > 0.3f) ? 0.3f : random
				.nextFloat() / 0.3f;
		Animation anima= new Animation(animacionR, FrameUtil.cortarTextura(
				"completo.png", 1, 1, false));
		
		
		return anima;
	}


	@Override
	public void realizarAnimacionColision() {

		ParallelAction seq = new ParallelAction();

		//MoveToAction moveAction = new MoveToAction();
		RotateToAction rotateAction = new RotateToAction();
		//ScaleToAction scaleAction = new ScaleToAction();

		setOrigin(getWidth() / 2, getHeight() / 2);

		rotateAction.setRotation(180f);
		rotateAction.setDuration(0.2f);

		// scaleAction.setScale(0.8f);
		// scaleAction.setDuration(0.5f);
		seq.addAction(rotateAction);
		// seq.addAction(scaleAction);

		addAction(seq);
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
