package cl.toki.dc.stage;

import cl.toki.dc.actor.objetoscolisionables.JugadaCompleja;
import cl.toki.dc.actor.objetoscolisionables.JugadaMedia;
import cl.toki.dc.actor.objetoscolisionables.JugadaSimple;
import cl.toki.dc.interfaces.Escenario;
import cl.toki.dc.interfaces.Fondo;
import cl.toki.dc.interfaces.Personaje.Estado;
import cl.toki.dc.pantallas.JuegoPantalla;

/**
 *
 * @author Francisco
 */
public class ValparaisoEscenario extends Escenario {

	private JuegoPantalla juegoPantalla;

	public ValparaisoEscenario(JuegoPantalla juegoPantalla) {
		super(juegoPantalla.juego.personaje, 60);

		this.juegoPantalla = juegoPantalla;

		agregarFondos(new Fondo("fondo1.png", 1, 1));
		agregarFondos(new Fondo("fondo2.png", 2, 2));
		agregarFondos(new Fondo("fondo3.png", 3, 3));

		personaje.estadoActual = Estado.corriendo;

		// TODO
		incremtarVelocidad(10);
	}

	@Override
	public void ejectuarLogica() {

		int tmpTranscurrido = ctlTiempoJuegoTask.getTiempoTranscurridos();

		switch (tmpTranscurrido){
		case 5:case 10:
			agregarJugada(new JugadaSimple(this, 1));break;
		case 15:case 20:
			agregarJugada(new JugadaSimple(this, 2));break;
		case 25:
			agregarJugada(new JugadaSimple(this, 3));break;
		case 30:case 40:case 50:
			agregarJugada(new JugadaMedia(this, 3));break;
		case 55:
			agregarJugada(new JugadaCompleja(this, 3));break;
		}
		
	}

	//TODO
	@Override
	public void terminarEscenario() {
		//TODO
		juegoPantalla.siguientePantalla(puntajeFinal);

	}

	// load the drop sound effect and the rain background "music"
	// dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
	// rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
	// rainMusic.setLooping(true);
	// create the camera and the SpriteBatch

}
