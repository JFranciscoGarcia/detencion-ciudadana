package cl.toki.dc.pantallas;

import cl.toki.dc.DetencionCiudadana;
import cl.toki.dc.util.Constantes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 *
 * @author Francisco
 */
public class PuntajePantalla extends Pantalla {

	LabelStyle ls = new LabelStyle();
	int puntajeFinal = 0;
	private Texture fondo;
	private Table contenidoTabla;
	private Table comandosTabla;
	private Table contenedorTabla;

	public PuntajePantalla(DetencionCiudadana gam, int puntajeFinal) {
		super(gam);
	
		this.puntajeFinal = puntajeFinal;
		fondo= new Texture("menujugadores.jpg");
		
		contenidoTabla= getContenido();
		comandosTabla= getComandos();
		
		contenedorTabla = new Table();
		contenedorTabla.add(contenidoTabla);
		contenedorTabla.row();
		contenedorTabla.add(comandosTabla);
		contenedorTabla.setFillParent(true);
		
		stage.addActor(contenedorTabla);
		//contenedorTabla.debug();
	}

	
	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		juego.batch.setProjectionMatrix(camera.combined);

		juego.batch.begin();
		juego.batch.draw(fondo, 0, 0, Constantes.ANCHO_MAXIMO,Constantes.ALTO_MAXIMO);
		juego.batch.end();

		stage.draw();
	}

	public Table getContenido() {

		ls = new LabelStyle();
		ls.fontColor = Color.WHITE;
		ls.font = new BitmapFont();
		ls.font.scale(2);

		Table table = new Table();

		table.add(new Label("Punataje", ls)).colspan(0).width(100);
		table.row();
		String puntaje = " " + puntajeFinal;
		table.add(new Label(puntaje, ls)).width(100).height(100);
		table.row();
		return table;

	}

	public Table getComandos() {
		Table table = new Table();

		Image sgtImage = new Image();
		sgtImage.setDrawable(new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("atras.png")))));

		sgtImage.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				atraspantalla();
				return true;
			}
		});

		table.add(sgtImage);
		return table;
	}

	protected void atraspantalla() {
		PersonajesPantalla menuPesonajes = new PersonajesPantalla(juego);
		juego.setScreen(menuPesonajes);
		dispose();

	}
	
	@Override
	public void dispose() {
		fondo.dispose();
		stage.dispose();
	}

}
