package cl.toki.dc.interfaces;

import cl.toki.dc.util.Constantes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * @author Francisco García
 *
 */
public abstract class Personaje extends Actor implements IAccion, IVelocidad {

	// TODO
	public float stateTime = 0;

	public Animation agachaAnimacion;
	public Animation correAnimacion;
	public Animation paradoDerAnimacion;
	public Animation saltoDerAnimacion;
	// public Animation paradoIzqAnimacion;
	public Animation caidaDerAnimacion;

	protected Rectangle contorno;

	protected TextureRegion currentFrame;

	public enum Estado {
		corriendo, saltando, cayendo, agachandose, presentandose
	}

	public enum NivelesVelocidad {
		uno, dos, tres
	}

	public NivelesVelocidad nivelActualVel;
	public Estado estadoActual;

	public Personaje(Estado estadoInicial) {
		estadoActual = estadoInicial;
		nivelActualVel = NivelesVelocidad.uno;
		setBounds(300, Constantes.ALTURA_PISO, 100, 120);
		contorno = new Rectangle(getX(), getY(), 80, 100);
		cargarAnimaciones();
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		stateTime += Gdx.graphics.getDeltaTime();
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
	}

	@Override
	public void act(float delta) {
		super.act(delta);
	}

	public void restablecerTamanio(){
		setBounds(300, Constantes.ALTURA_PISO, 100, 120);
		contorno = new Rectangle(getX(), getY(), 80, 100);
	}
	
	public Rectangle getContorno() {
		return contorno;
	}

	public abstract void cargarAnimaciones();

	public abstract String getDescripcion();

	public abstract Image getImagenPresentacion();
}
