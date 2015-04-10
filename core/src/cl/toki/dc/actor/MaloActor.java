package cl.toki.dc.actor;

import java.util.List;

import cl.toki.dc.actor.objetoscolisionables.Jugada;
import cl.toki.dc.interfaces.Escenario;
import cl.toki.dc.interfaces.Objeto;
import cl.toki.dc.interfaces.Personaje;
import cl.toki.dc.util.Constantes;
import cl.toki.dc.util.FrameUtil;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 *
 * @author Francisco
 */
public class MaloActor extends Personaje {

	private final float correAnimacionVel = 0.03f;
	private final float agachaAnimcionVel = 0.08f;
	private Escenario escenario;

	// TODO
	public MaloActor(Estado estadoInicial, Escenario escenario) {
		super(estadoInicial);
		this.escenario = escenario;
		setX(Constantes.ANCHO_MAXIMO - 200);
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
				FrameUtil.cortarTextura("flaite.png", 10, 1, false));

		saltoDerAnimacion = new Animation(0.05f, FrameUtil.cortarTextura(
				"flaite.png", 10, 1, false));

		caidaDerAnimacion = new Animation(0.05f, FrameUtil.cortarTextura(
				"flaite.png", 10, 1, false));

		agachaAnimacion = new Animation(agachaAnimcionVel,
				FrameUtil.cortarTextura("flaite.png", 10, 1, false));

		paradoDerAnimacion = new Animation(0.1f, FrameUtil.cortarTextura(
				"flaite.png", 10, 1, false));
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		stateTime += Gdx.graphics.getDeltaTime();

		List<Jugada> jugadas = escenario.getJugadaList();

		// TODO


		int altoContorno = 0;
		int anchoContorno = 0;

		if (estadoActual == Estado.saltando && getY() == Constantes.ALTURA_PISO) {
			estadoActual = Estado.corriendo;
		}

		if (estadoActual == Estado.cayendo
				&& caidaDerAnimacion.isAnimationFinished(stateTime)) {
			estadoActual = Estado.corriendo;
		}

		if (estadoActual == Estado.agachandose
				&& agachaAnimacion.isAnimationFinished(stateTime)) {
			estadoActual = Estado.corriendo;
		}

		switch (estadoActual) {
		case cayendo:

			currentFrame = caidaDerAnimacion.getKeyFrame(stateTime, true);
			altoContorno = 100;
			anchoContorno = 80;
			break;

		case corriendo:

			currentFrame = correAnimacion.getKeyFrame(stateTime, true);
			altoContorno = 100;
			anchoContorno = 80;
			break;

		case saltando:

			currentFrame = saltoDerAnimacion.getKeyFrame(stateTime, true);
			altoContorno = 100;
			anchoContorno = 80;
			break;

		case agachandose:

			currentFrame = agachaAnimacion.getKeyFrame(stateTime, true);
			altoContorno = 40;
			anchoContorno = 80;
			break;

		case presentandose:

			currentFrame = paradoDerAnimacion.getKeyFrame(stateTime, true);
			altoContorno = 100;
			anchoContorno = 80;
			break;
		}

		contorno.set(this.getX(), this.getY(), anchoContorno, altoContorno);
		batch.draw(currentFrame, this.getX(), this.getY(), this.getWidth(),
				this.getHeight());
		
		for (Jugada jugada : jugadas) {

			List<Objeto> objetos = jugada.getObjetosColisionables();

			for (Objeto objeto : objetos) {

				if (!objeto.esColisionable) {
					continue;
				}
				
				if(getX()+100==objeto.getX()){
					saltar();
					return;
				}

			}

		}
	}

	// TODO
	public String getDescripcion() {
		return null;
	}

	@Override
	public Image getImagenPresentacion() {
		return null;
	}
}
