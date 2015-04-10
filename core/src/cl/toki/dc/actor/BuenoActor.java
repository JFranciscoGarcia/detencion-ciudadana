package cl.toki.dc.actor;

import cl.toki.dc.interfaces.Personaje;
import cl.toki.dc.util.Constantes;
import cl.toki.dc.util.FrameUtil;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 *
 * @author Francisco
 */
public class BuenoActor extends Personaje {

	private final float correAnimacionVel = 0.03f;
	private final float agachaAnimcionVel = 0.08f;

	public BuenoActor(Estado estadoInicial) {
		super(estadoInicial);
	}

	@Override
	public void agacharse() {
		estadoActual = Estado.agachandose;
		stateTime = 0;

	}

	@Override
	public void saltar() {

		if (estadoActual == Estado.cayendo || estadoActual == Estado.saltando) {
			return;
		}

		SequenceAction seq = new SequenceAction();
		stateTime = 0;
		estadoActual = Estado.saltando;

		MoveToAction subidaAction;
		MoveToAction bajadaAction;

		subidaAction = new MoveToAction();
		bajadaAction = new MoveToAction();

		subidaAction.setPosition(getX(), this.getY() + 200);
		subidaAction.setDuration(0.2f);

		bajadaAction.setPosition(this.getX(), Constantes.ALTURA_PISO);
		bajadaAction.setDuration(0.3f);

		seq.addAction(subidaAction);
		seq.addAction(bajadaAction);

		addAction(seq);
	}

	@Override
	public void caer() {

		if (estadoActual == Estado.cayendo) {
			return;
		}

		estadoActual = Estado.cayendo;
		stateTime = 0;
	}

	@Override
	public void incrementarVel() {

		switch (nivelActualVel) {
		case uno:
			nivelActualVel = NivelesVelocidad.dos;
			correAnimacion.setFrameDuration(0.02f);
			agachaAnimacion.setFrameDuration(0.07f);
			break;
		case dos:
			nivelActualVel = NivelesVelocidad.tres;
			correAnimacion.setFrameDuration(0.01f);
			agachaAnimacion.setFrameDuration(0.06f);
			break;
		case tres:
			nivelActualVel = NivelesVelocidad.tres;
			correAnimacion.setFrameDuration(0.01f);
			agachaAnimacion.setFrameDuration(0.06f);
			break;

		}

	}

	@Override
	public void decrementarVel() {

		switch (nivelActualVel) {
		case tres:
			nivelActualVel = NivelesVelocidad.dos;
			correAnimacion.setFrameDuration(0.02f);
			agachaAnimacion.setFrameDuration(0.07f);
			break;
		case dos:
			nivelActualVel = NivelesVelocidad.uno;
			correAnimacion.setFrameDuration(0.03f);
			agachaAnimacion.setFrameDuration(0.08f);
			break;
		case uno:
			nivelActualVel = NivelesVelocidad.uno;
			correAnimacion.setFrameDuration(0.03f);
			agachaAnimacion.setFrameDuration(0.08f);
			break;
		}

	}

	@Override
	public void resetearVel() {
		nivelActualVel = NivelesVelocidad.uno;
		correAnimacion.setFrameDuration(correAnimacionVel);
	}

	@Override
	public void cargarAnimaciones() {

		correAnimacion = new Animation(correAnimacionVel,
				FrameUtil.cortarTextura("monocorre.png", 12, 2, false));

		saltoDerAnimacion = new Animation(0.05f, FrameUtil.cortarTextura(
				"salto.png", 11, 1, false));

		caidaDerAnimacion = new Animation(0.05f, FrameUtil.cortarTextura(
				"caida.png", 10, 1, false));

		agachaAnimacion = new Animation(agachaAnimcionVel,
				FrameUtil.cortarTextura("monoagacha.png", 7, 1, false));

		paradoDerAnimacion = new Animation(0.1f, FrameUtil.cortarTextura(
				"monoparado.png", 4, 1, false));
	}

	public String getDescripcion() {
		return "El Jony es un personaje promedio. estudiante que estará pronto a vivir su peor experiencia";
	}

	@Override
	public Image getImagenPresentacion() {
		Image image = new Image();
		image.setDrawable(new TextureRegionDrawable(new TextureRegion(
				paradoDerAnimacion.getKeyFrames()[0])));
		return image;
	}
}
