package cl.toki.dc.pantallas;

import cl.toki.dc.DetencionCiudadana;
import cl.toki.dc.actor.AbuelaActor;
import cl.toki.dc.actor.BuenoActor;
import cl.toki.dc.interfaces.Personaje;
import cl.toki.dc.interfaces.Personaje.Estado;
import cl.toki.dc.util.Constantes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 *
 * @author Francisco
 */
public class PersonajesPantalla extends Pantalla {

	private Personaje personajeSeleccionado;
	private Label descripcion;
	LabelStyle ls = new LabelStyle();
	private Texture fondo;

	private Table contenidoTabla;
	private Table comandosTabla;
	private Table contenedorTabla;

	private BuenoActor bueno;
	private AbuelaActor abuela;

	public PersonajesPantalla(DetencionCiudadana gam) {
		super(gam);
		fondo= new Texture("menujugadores.jpg");
		
		contenidoTabla= getContenido();
		comandosTabla= getComandos();
		
		contenedorTabla = new Table();
		contenedorTabla.add(contenidoTabla);
		contenedorTabla.row();
		contenedorTabla.add(comandosTabla);
		contenedorTabla.setFillParent(true);
		
		stage.addActor(contenedorTabla);
	}

	private void switchPersonaje(Personaje nuevoPersonaje) {
		Cell<Personaje> cella = contenidoTabla.getCell(personajeSeleccionado);
		cella.setActor(nuevoPersonaje);

		personajeSeleccionado = nuevoPersonaje;
		descripcion.setText(nuevoPersonaje.getDescripcion());
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

	@Override
	public void dispose() {
		fondo.dispose();
		stage.dispose();
	}
		

	public Table getContenido() {

		ls = new LabelStyle();
		ls.fontColor = Color.WHITE;
		ls.font = new BitmapFont();

		bueno = new BuenoActor(Estado.presentandose);
		abuela = new AbuelaActor(Estado.presentandose);

		personajeSeleccionado = bueno;

		Actor actor1 = bueno.getImagenPresentacion();
		Actor actor2 = abuela.getImagenPresentacion();

		actor1.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				switchPersonaje(bueno);
				return true;
			}

		});

		actor2.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				switchPersonaje(abuela);
				return true;
			}
		});

		Table table = new Table();

		table.add(new Label("Selecciona un personaje", ls)).colspan(0)
				.width(100);
		table.row();
		table.add(actor1).width(100).height(100);
		table.add(actor2).width(100).height(100);
		table.row();
		table.add(bueno).width(200).height(200);
		descripcion = new Label(bueno.getDescripcion(), ls);
		descripcion.setWrap(true);

		table.add(descripcion).width(200);

		return table;

	}

	public Table getComandos() {
		Table table = new Table();

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

		table.add(sgtImage);
		return table;
	}


	protected void siguientePantalla() {
		personajeSeleccionado.restablecerTamanio();
		juego.personaje= personajeSeleccionado;
		EscenarioPantalla escenarioPantalla = new EscenarioPantalla(juego);
		juego.setScreen(escenarioPantalla);
		dispose();

	}

}
