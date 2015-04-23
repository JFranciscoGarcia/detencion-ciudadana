package cl.toki.dc.pantallas;

import cl.toki.dc.DetencionCiudadana;
import cl.toki.dc.interfaces.JuegoCore;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;

/**
 *
 * @author Francisco
 */
public class JuegoPantalla extends Pantalla {

    JuegoCore juegoCore;

    public JuegoPantalla(DetencionCiudadana gam) {
    	super(gam);
    	juegoCore = new JuegoCore(this);
        Gdx.input.setInputProcessor(juegoCore);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(256f, 256f, 256f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        juego.batch.setProjectionMatrix(camera.combined);

        juegoCore.act(Gdx.graphics.getDeltaTime());
        juegoCore.draw();

        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            dispose();
        }

    }


    @Override
    public void dispose() {
    	juegoCore.dispose();
    }

	public void siguientePantalla(int puntajeFinal) {
		Puntaje puntajePantalla = new Puntaje(juego, puntajeFinal);
		juego.setScreen(puntajePantalla);
		dispose();
	}

}
