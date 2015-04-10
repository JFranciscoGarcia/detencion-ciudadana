package cl.toki.dc.actor.objetoscolisionables;

import java.util.List;

import cl.toki.dc.interfaces.Objeto;

import com.badlogic.gdx.graphics.g2d.Batch;

public abstract class Jugada {
	
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
	
	abstract public List<Objeto> getObjetosColisionables();

}
