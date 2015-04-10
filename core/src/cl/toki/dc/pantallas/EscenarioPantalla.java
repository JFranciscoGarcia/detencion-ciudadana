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
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 *
 * @author Francisco
 */
public class EscenarioPantalla extends Pantalla {

	LabelStyle ls = new LabelStyle();
	private Table table;
	private Texture fondo;
	
	private Table contenidoTabla;
	private Table comandosTabla;
	private Table contenedorTabla;

	public EscenarioPantalla(DetencionCiudadana gam) {
		super(gam);
		fondo= new Texture("fondomenu2.jpg");
		
		contenidoTabla= getContenido();
		comandosTabla= getComandos();
		
		contenedorTabla = new Table();
		contenedorTabla.add(contenidoTabla);
		contenedorTabla.row();
		contenedorTabla.add(comandosTabla);
		contenedorTabla.setFillParent(true);
		
		stage.addActor(contenedorTabla);
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
		
		Image valpo = new Image();
		valpo.setDrawable(new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("valpo.png")))));

		Image santiago = new Image();
		santiago.setDrawable(new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("santiago.png")))));

		// 0.9f scales to 90% of the original height and width
		// final float heightScaleFactor = 0.9f;
		// final float widthScaleFactor = 0.9f;
		//
		// valpo.setHeight(valpo.getHeight() * heightScaleFactor);
		// santiago.setWidth(santiago.getWidth() * widthScaleFactor);
		//

		table = new Table();
		//table.defaults().expand().fill();
		table.align(Align.center);
		table.add(new Label("Selecciona una ciudad", ls)).colspan(0).width(100);
		table.row();
		table.add(valpo);
		Label a = new Label(
				"Valparaíso. Ciudad de los shoros del puerto, debes tener cuidado, los choros porteños son rápidos y ascurrios",
				ls);
		a.setWrap(true);

		table.add(a).width(200);
		table.row();
		table.add(santiago);
		Label b = new Label(
				"Santiago. Los shorizos de la capital son enteros víos, y si no soy vío te convertirás en perkin",
				ls);
		b.setWrap(true);
		table.add(b).width(200);
		
		return table;
	}



	public Table getComandos() {
		Table table = new Table();
		

		Image atrasImage = new Image();
		atrasImage.setDrawable(new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("atras.png")))));

		atrasImage.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				anteriorPantalla();
				return true;
			}
		});
		
		
		Image sgtImage = new Image();
		sgtImage.setDrawable(new TextureRegionDrawable(new TextureRegion(
				new Texture(Gdx.files.internal("siguiente.png")))));

		sgtImage.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				siguientePantalla();
				return true;
			}
		});
		
		
		table.add(atrasImage);
		table.add(sgtImage);
		return table;
	}

	protected void anteriorPantalla() {
		PersonajesPantalla menuPersonajes = new PersonajesPantalla(juego);
		juego.setScreen(menuPersonajes);
		dispose();
		
	}
	
	protected void siguientePantalla() {
		JuegoPantalla gas= new JuegoPantalla(juego);
		juego.setScreen(gas);
		dispose();
		
	}
	
	@Override
	public void dispose() {
		fondo.dispose();
		stage.dispose();
	}

}
