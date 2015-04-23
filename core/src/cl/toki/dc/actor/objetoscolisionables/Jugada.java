package cl.toki.dc.actor.objetoscolisionables;

import java.util.List;

import cl.toki.dc.interfaces.IVelocidad;
import cl.toki.dc.interfaces.Objeto;

import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Jugada implements IVelocidad {
	
	public int puntaje=0;
	private List<Objeto> objetosColisionables;

	public void dibujarJugada(Batch batch, float parentAlpha) {
		objetosColisionables = getObjetosColisionables();

		for (Objeto objeto : objetosColisionables) {
			objeto.draw(batch, parentAlpha);
		}
		
				//TODO
		if(puntaje==0){
			for(Objeto objeto: objetosColisionables){
				puntaje+= objeto.puntaje;
			}
		}	
	}
	
	
	@Override
	public void incrementarVel() {
		for(Objeto objeto: objetosColisionables){
			objeto.incrementarVel();
		}
	}

	@Override
	public void decrementarVel() {
		for(Objeto objeto: objetosColisionables){
			objeto.decrementarVel();
		}
	}

	@Override
	public void resetearVel() {
		for(Objeto objeto: objetosColisionables){
			objeto.resetearVel();
		}
	}

	
	abstract public List<Objeto> getObjetosColisionables();

}
