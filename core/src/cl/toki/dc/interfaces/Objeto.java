package cl.toki.dc.interfaces;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author Francisco
 */
public abstract class Objeto extends Actor implements IVelocidad {

	private Rectangle contorno;
	private TextureRegion currentFrame;
	private final Animation animacion;
	public JuegoCore escenario;
	public boolean esObjetodePoder = false;
	public boolean esColisionable = true;
	private float stateTime;
	public int velocidad = 5;
	public int puntaje;

	public Objeto(JuegoCore escenario, float x, float y) {
		this.escenario = escenario;
		this.escenario.addActor(this);
		animacion = getAnimacion();

		TextureRegion[] textureRegionTmp = animacion.getKeyFrames();
		int altoObjeto = textureRegionTmp[0].getRegionHeight();
		int anchoObjeto = textureRegionTmp[0].getRegionWidth();

		setBounds(x, y, anchoObjeto, altoObjeto);
		contorno = new Rectangle(getX(), getY(), getWidth() / 2,
				getHeight() / 2);
	}
	
	public Objeto(JuegoCore escenario, float x, float y, int puntaje) {
		this.escenario = escenario;
		this.escenario.addActor(this);
		this.puntaje=puntaje;
		animacion = getAnimacion();

		TextureRegion[] textureRegionTmp = animacion.getKeyFrames();
		int altoObjeto = textureRegionTmp[0].getRegionHeight();
		int anchoObjeto = textureRegionTmp[0].getRegionWidth();

		setBounds(x, y, anchoObjeto, altoObjeto);
		contorno = new Rectangle(getX(), getY(), getWidth() / 2,
				getHeight() / 2);
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = animacion.getKeyFrame(stateTime, true);

		setX(this.getX() - velocidad);
		contorno.set(getX() + 20, this.getY() - 5, getWidth() / 2,
				getHeight() / 2);
		
		batch.draw(currentFrame, getX(), getY(), this.getOriginX(),
				this.getOriginY(), this.getWidth(), this.getHeight(),
				this.getScaleX(), this.getScaleY(), this.getRotation());
	}

	public Rectangle getContorno() {
		return contorno;
	}

	public boolean esVisible() {
		if (getX() < 0) {
			return false;
		}
		return true;
	}

	

	abstract public Animation getAnimacion();

	abstract public void realizarAnimacionColision();
}
