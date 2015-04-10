package cl.toki.dc;

import cl.toki.dc.interfaces.Personaje;
import cl.toki.dc.pantallas.Presentacion;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class DetencionCiudadana extends Game {
	public Personaje personaje;
	public SpriteBatch batch;
	public Texture img;

	@Override
	public void create() {
		batch = new SpriteBatch();
		// img = new Texture("badlogic.jpg");
		this.setScreen(new Presentacion(this));
	}

	@Override
	public void render() {
		/*
		 * Gdx.gl.glClearColor(1, 0, 0, 1);
		 * Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); batch.begin();
		 * batch.draw(img, 0, 0); batch.end();
		 */
		super.render();
	}
}
