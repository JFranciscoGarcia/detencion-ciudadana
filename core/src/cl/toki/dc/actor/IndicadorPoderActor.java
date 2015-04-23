package cl.toki.dc.actor;

import cl.toki.dc.interfaces.JuegoCore;
import cl.toki.dc.util.Constantes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author Francisco Garcia
 */
public class IndicadorPoderActor extends Actor {

    private final JuegoCore escenario;
    private final BitmapFont contadorFont;
    private Texture nivel;
    private final Texture nivel1;
    private final Texture nivel2;
    private final Texture nivel3;

    public IndicadorPoderActor(JuegoCore escenario) {
        this.escenario = escenario;
        contadorFont = new BitmapFont();
        contadorFont.setColor(Color.YELLOW);
        contadorFont.scale(2);

        nivel1 = new Texture(Gdx.files.internal("n1.png"));
        nivel2 = new Texture(Gdx.files.internal("n2.png"));
        nivel3 = new Texture(Gdx.files.internal("n3.png"));

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        switch (escenario.pinera.nivelActualVel) {
            case uno:
                nivel = nivel1;
                break;

            case dos:
                nivel = nivel2;
                break;

            case tres:
                nivel = nivel3;
                break;

        }

        batch.draw(nivel, Constantes.ANCHO_MAXIMO - (nivel.getWidth()+ 20), Constantes.ALTO_MAXIMO - (nivel.getHeight() + 10));

    }
}
