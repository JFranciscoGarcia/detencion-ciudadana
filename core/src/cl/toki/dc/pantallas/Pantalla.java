package cl.toki.dc.pantallas;

import cl.toki.dc.DetencionCiudadana;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class Pantalla implements Screen {

	public DetencionCiudadana juego;
	public OrthographicCamera camera;
	public Stage stage;
	
	public Pantalla(DetencionCiudadana juego) {
		this.juego = juego;

		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

}
