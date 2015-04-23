package cl.toki.dc.interfaces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cl.toki.dc.actor.ContadorActor;
import cl.toki.dc.actor.IndicadorPoderActor;
import cl.toki.dc.actor.Pinera;
import cl.toki.dc.actor.objetoscolisionables.Jugada;
import cl.toki.dc.actor.objetoscolisionables.JugadaCompleja;
import cl.toki.dc.actor.objetoscolisionables.JugadaMedia;
import cl.toki.dc.actor.objetoscolisionables.JugadaSimple;
import cl.toki.dc.interfaces.Personaje.Estado;
import cl.toki.dc.interfaces.Personaje.NivelesVelocidad;
import cl.toki.dc.pantallas.JuegoPantalla;
import cl.toki.dc.stage.Fondo;
import cl.toki.dc.stage.Textura;
import cl.toki.dc.stage.Textura.Nivel;
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
public class JuegoCore extends Stage {

	private final List<Jugada> jugadaList = new ArrayList<Jugada>();
	public final CtrlTiempoJuegoTask ctlTiempoJuegoTask;
	public Personaje pinera;
	// TODO
	private final ContadorActor contador;
	private final IndicadorPoderActor indicadorVelocidad;

	public int puntajeFinal;
	private int puntajePerdido;
	private Fondo fondo;
	private JuegoPantalla juegoPantalla;

	public JuegoCore(JuegoPantalla juegoPantalla) {
		super(new ScalingViewport(Scaling.stretch, Constantes.ANCHO_MAXIMO,
				Constantes.ALTO_MAXIMO, new OrthographicCamera(
						Constantes.ANCHO_MAXIMO, Constantes.ALTO_MAXIMO)));

		this.pinera = new Pinera(Estado.corriendo);
		this.juegoPantalla = juegoPantalla;

		ctlTiempoJuegoTask = new CtrlTiempoJuegoTask(this, 120);
		indicadorVelocidad = new IndicadorPoderActor(this);
		contador = new ContadorActor(this);

		addListener(new Control(pinera));
		addActor(pinera);
		addActor(contador);
		addActor(indicadorVelocidad);

		List<Textura> texturas = new ArrayList<Textura>();

		texturas.add(new Textura("fondo1.png", Nivel.bajo));
		texturas.add(new Textura("fondo2.png", Nivel.medio));
		texturas.add(new Textura("fondo3.png", Nivel.alto));
		Fondo fondo = new Fondo(texturas);

		setFondo(fondo);

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

				Rectangle buenoActorRect = pinera.getContorno();
				Rectangle objRect = objetoColisionabe.getContorno();

				if (!buenoActorRect.overlaps(objRect)) {
					continue;
				}

				objetoColisionabe.esColisionable = false;
				objetoColisionabe.realizarAnimacionColision();

				if (objetoColisionabe.esObjetodePoder) {
					incrementarVelocidadJuego();

				} else {
					pinera.caer();
					puntajePerdido += objetoColisionabe.puntaje;
					objetoColisionabe.realizarAnimacionColision();
					decrementarVelocidadJuego();
				}

			}
		}
	}

	private void incrementarVelocidadJuego() {

		if (pinera.nivelActualVel != NivelesVelocidad.tres) {
			fondo.incrementarVel();
		}
		pinera.incrementarVel();

		for (Jugada jugada : jugadaList) {
			jugada.incrementarVel();
		}
	}

	private void decrementarVelocidadJuego() {

		if (pinera.nivelActualVel != NivelesVelocidad.uno) {
			fondo.decrementarVel();
		}
		pinera.decrementarVel();

		for (Jugada jugada : jugadaList) {
			jugada.decrementarVel();
		}
	}

	private void verificarObjetosFuera() {

		for (Iterator<Jugada> itrJugada = jugadaList.iterator(); itrJugada
				.hasNext();) {
			Jugada jugada = itrJugada.next();

			List<Objeto> objetosColisionables = jugada
					.getObjetosColisionables();

			for (Iterator<Objeto> itrObjeto = objetosColisionables.iterator(); itrObjeto
					.hasNext();) {
				Objeto objeto = itrObjeto.next();
				if (!objeto.esVisible()) {
					objeto.remove();
					itrObjeto.remove();
				}
			}
		}
	}

	public void setFondo(Fondo fondo) {
		this.fondo = fondo;
	}

	private void moverFondos() {
		fondo.moverFondos();
	}

	public void agregarJugada(Jugada jugada) {
		jugadaList.add(jugada);
	}

	public void finalizarJuego() {
		Timer.instance().clear();
		fondo.dispose();

		int puntajeEsperado = 0;
		for (Jugada jugada : jugadaList) {
			puntajeEsperado += jugada.puntaje;
		}

		puntajeFinal = puntajeEsperado - puntajePerdido;
		juegoPantalla.siguientePantalla(puntajeFinal);
	}

	@Override
	public void draw() {

		getBatch().begin();
		fondo.draw(getBatch(), 0);

		for (Jugada jugada : jugadaList) {
			jugada.dibujarJugada(getBatch(), 0);
		}

		pinera.draw(getBatch(), 0);
		contador.draw(getBatch(), 0);
		indicadorVelocidad.draw(getBatch(), 0);

		getBatch().end();

		moverFondos();
		verificarColisiones();
		verificarObjetosFuera();
	}

	public void ejectuarLogica() {
		int tmpTranscurrido = ctlTiempoJuegoTask.getTiempoTranscurridos();

		switch (tmpTranscurrido) {
		case 5:case 10:
			agregarJugada(new JugadaSimple(this, 1));
			break;
		case 15:case 20:
			agregarJugada(new JugadaSimple(this, 2));
			break;
		case 25:
			agregarJugada(new JugadaSimple(this, 3));
			break;
		case 30:case 40:case 50:
			agregarJugada(new JugadaMedia(this, 3));
			break;
		case 55:
			agregarJugada(new JugadaCompleja(this, 3));
			break;
		}
	}
}
