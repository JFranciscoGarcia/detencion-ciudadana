package cl.toki.dc.interfaces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cl.toki.dc.actor.ContadorActor;
import cl.toki.dc.actor.IndicadorPoderActor;
import cl.toki.dc.actor.MaloActor;
import cl.toki.dc.actor.objetoscolisionables.Jugada;
import cl.toki.dc.interfaces.Personaje.Estado;
import cl.toki.dc.interfaces.Personaje.NivelesVelocidad;
import cl.toki.dc.util.Constantes;
import cl.toki.dc.util.Control;
import cl.toki.dc.util.CtrlTiempoJuegoTask;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

/**
 *
 * @author Francisco Garcia
 */
public abstract class Escenario extends Stage {

	private List<Fondo> fondoList = new ArrayList<Fondo>();
	private final List<Jugada> jugadaList = new ArrayList<Jugada>();
	public final CtrlTiempoJuegoTask ctlTiempoJuegoTask;
	public Personaje personaje;
	private MaloActor maloActor;
	//TODO
	private final ContadorActor contador;
	private final IndicadorPoderActor indicadorVelocidad;
	
	public int puntajeFinal;
	private int puntajePerdido;

	public Escenario(Personaje personaje, int tiempoJuego) {
		super(new ScalingViewport(Scaling.stretch, Constantes.ANCHO_MAXIMO,
				Constantes.ALTO_MAXIMO, new OrthographicCamera(
						Constantes.ANCHO_MAXIMO, Constantes.ALTO_MAXIMO)));

		ctlTiempoJuegoTask = new CtrlTiempoJuegoTask(this, tiempoJuego);

		indicadorVelocidad = new IndicadorPoderActor(this);
		contador = new ContadorActor(this);
		this.personaje = personaje;
		maloActor = new MaloActor(Estado.presentandose,this);

		addListener(new Control(personaje));
		addActor(maloActor);
		addActor(personaje);
		addActor(contador);
		addActor(indicadorVelocidad);
		
		Timer.schedule(ctlTiempoJuegoTask, 1, 1);
		Timer.schedule(new Timer.Task() {
			@Override
			public void run() {
				ejectuarLogica();
			}
		}, 1, 1);

	}

	private void verificarColisiones() {
		for (Jugada jugada : jugadaList) {
			for (Objeto objetoColisionabe : jugada.getObjetosColisionables()) {

				if (!objetoColisionabe.esColisionable) {
					continue;
				}

				Rectangle buenoActorRect = personaje.getContorno();
				Rectangle objRect = objetoColisionabe.getContorno();

				if (!buenoActorRect.overlaps(objRect)) {
					continue;
				}

				objetoColisionabe.esColisionable = false;
				objetoColisionabe.realizarAnimacionChoque();

				if (objetoColisionabe.esObjetodePoder) {
					incrementarVelocidadJuego();

				} else {
					personaje.caer();
					puntajePerdido+=objetoColisionabe.puntaje;
					objetoColisionabe.realizarAnimacionChoque();
					decrementarVelocidadJuego();
				}

			}
		}
	}

	private void incrementarVelocidadJuego() {

		if (personaje.nivelActualVel != NivelesVelocidad.tres) {
			incremtarVelocidad(10);
		}
		personaje.incrementarVel();

		for (Jugada jugada : jugadaList) {
			for (Objeto objetoColisionabe : jugada.getObjetosColisionables()) {
				objetoColisionabe.incrementarVel();
			}
		}
	}

	private void decrementarVelocidadJuego() {

		if (personaje.nivelActualVel != NivelesVelocidad.uno) {
			incremtarVelocidad(-10);
		}

		personaje.decrementarVel();

		for (Jugada jugada : jugadaList) {
			for (Objeto objetoColisionabe : jugada.getObjetosColisionables()) {
				objetoColisionabe.decrementarVel();
			}
		}
	}

	private void verificarObjetosFuera() {

		for (Iterator<Jugada> itrJugada = jugadaList.iterator(); itrJugada.hasNext();) {
			Jugada jugada = itrJugada.next();
			
			List<Objeto>objetosColisionables= jugada.getObjetosColisionables();
			
			for (Iterator<Objeto> itrObjeto = objetosColisionables.iterator(); itrObjeto.hasNext();) {
				Objeto objeto = itrObjeto.next();
				if (!objeto.esVisible()) {
					objeto.remove();
					itrObjeto.remove();
				}
			}
		}
	}

	private void moverFondos() {
		for (Fondo fondo : fondoList) {
			fondo.moverFondo();
		}
	}

	public void agregarFondos(Fondo fondo) {
		fondoList.add(fondo);
	}

	public void incremtarVelocidad(int velocidad) {
		for (Fondo fondo : fondoList) {
			fondo.setVelocidadIncremento(velocidad);
		}
	}

	public void agregarJugada(Jugada jugada) {
		jugadaList.add(jugada);
	}
		
	public List<Jugada> getJugadaList() {
		return jugadaList;
	}

	public void finalizarNivel() {
		Timer.instance().clear();
		for (Fondo fondo : fondoList) {
			fondo.dispose();
		}

		int puntajeEsperado=0;
		for(Jugada jugada:jugadaList){
			puntajeEsperado+=jugada.puntaje;
		}

		puntajeFinal= puntajeEsperado-puntajePerdido;
	
		terminarEscenario();
	}

	@Override
	public void draw() {
			
		getBatch().begin();

		for (Fondo fondo : fondoList) {
			getBatch().draw(fondo, 0, 0, fondo.getVelocidad(), 0,
					Constantes.ANCHO_MAXIMO, Constantes.ALTO_MAXIMO);
		}

		for (Jugada jugada : jugadaList) {
			jugada.dibujarJugada(getBatch(), 0);
		}
		
		personaje.draw(getBatch(), 0);
		maloActor.draw(getBatch(), 0);
		//
		// getBatch().draw(fondoprim, 0, 0, fondoprim.getVelocidad(), 0,
		// Constantes.ANCHO_MAXIMO, Constantes.ALTO_MAXIMO);
		contador.draw(getBatch(), 0);
		indicadorVelocidad.draw(getBatch(), 0);

		getBatch().end();

		moverFondos();
		verificarColisiones();
		verificarObjetosFuera();
	}

	public abstract void ejectuarLogica();

	public abstract void terminarEscenario();
}
