package cl.toki.dc.stage;

import java.util.List;

import cl.toki.dc.interfaces.IVelocidad;
import cl.toki.dc.util.Constantes;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Fondo extends Actor implements IVelocidad{

	private List<Textura> texturas;

	public Fondo(List<Textura> texturas) {
		this.texturas = texturas;
	}

	@Override
	public void draw(Batch batch, float parentAlpha) {

		for (Textura textura : texturas) {
			batch.draw(textura, 0, 0, textura.getVelocidad(), 0,
					Constantes.ANCHO_MAXIMO, Constantes.ALTO_MAXIMO);
		}

	}

	public void moverFondos() {
		for (Textura textura : texturas) {
			textura.moverFondo();
		}
	}

	public void dispose() {
		for (Textura textura : texturas) {
			textura.dispose();
		}
		
	}

	@Override
	public void incrementarVel() {
		for (Textura textura : texturas) {
			textura.incrementarVel();
		}	
	}

	@Override
	public void decrementarVel() {
		for (Textura textura : texturas) {
			textura.decrementarVel();
		}		
	}

	@Override
	public void resetearVel() {
		for (Textura textura : texturas) {
			textura.resetearVel();
		}		
	}

}
