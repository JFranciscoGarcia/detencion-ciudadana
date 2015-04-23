package cl.toki.dc.pantallas;

import cl.toki.dc.DetencionCiudadana;
import cl.toki.dc.util.Constantes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author Francisco
 */
public class Presentacion implements Screen {

    Texture fondoTexture;
    final DetencionCiudadana game;

    OrthographicCamera camera;

    public Presentacion(final DetencionCiudadana gam) {
        fondoTexture = new Texture("fondopantalla.png");
        game = gam;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(fondoTexture, 0, 0, Constantes.ANCHO_MAXIMO, Constantes.ALTO_MAXIMO);
        game.batch.end();

        if (Gdx.input.justTouched()) {
        	
        	Pantalla menuPersonaje = new Personajes(game); 
        	game.setScreen(menuPersonaje);
            dispose();
        }
    }

    @Override
    public void show() {
        //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resize(int width, int height) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pause() {
        //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resume() {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void hide() {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose() {
    	fondoTexture.dispose();  
    }

}
