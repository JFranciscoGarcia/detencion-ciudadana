package cl.toki.dc.actor;

import cl.toki.dc.interfaces.Escenario;
import cl.toki.dc.util.Constantes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 *
 * @author Francisco Garcia
 */
public class ContadorActor extends Actor {

    private final Escenario escenario;
    private final BitmapFont contadorFont;

    public ContadorActor(Escenario escenario) {
        this.escenario = escenario;
        contadorFont = new BitmapFont();
        contadorFont.setColor(Color.YELLOW);
        contadorFont.scale(2);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        int v = escenario.ctlTiempoJuegoTask.getTiempoRestante();

        if (v > 10 && v < 20) {
            contadorFont.setColor(Color.ORANGE);
        }

        if (v >= 0 && v <= 10) {
            contadorFont.setColor(Color.RED);
        }

        contadorFont.draw(batch, escenario.ctlTiempoJuegoTask.getTiempoRestanteStr(), Constantes.ANCHO_MAXIMO / 2, Constantes.ALTO_MAXIMO - 10);

    }
}
