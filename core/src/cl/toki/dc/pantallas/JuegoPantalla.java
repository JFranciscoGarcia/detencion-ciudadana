package cl.toki.dc.pantallas;

import cl.toki.dc.DetencionCiudadana;
import cl.toki.dc.stage.ValparaisoEscenario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;

/**
 *
 * @author Francisco
 */
public class JuegoPantalla extends Pantalla {

    ValparaisoEscenario valparaisoEscenario;

    public JuegoPantalla(DetencionCiudadana gam) {
    	super(gam);
        valparaisoEscenario = new ValparaisoEscenario(this);
        Gdx.input.setInputProcessor(valparaisoEscenario);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(256f, 256f, 256f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();

        juego.batch.setProjectionMatrix(camera.combined);

        valparaisoEscenario.act(Gdx.graphics.getDeltaTime());
        valparaisoEscenario.draw();

        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            dispose();
        }

    }


    @Override
    public void dispose() {
        valparaisoEscenario.dispose();
    }

	public void siguientePantalla(int puntajeFinal) {
		PuntajePantalla puntajePantalla = new PuntajePantalla(juego, puntajeFinal);
		juego.setScreen(puntajePantalla);
		dispose();
	}

}
