package cl.toki.dc.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import cl.toki.dc.util.Constantes;
import cl.toki.dc.DetencionCiudadana;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = Constantes.ANCHO_MAXIMO;
        config.height = Constantes.ALTO_MAXIMO;

		new LwjglApplication(new DetencionCiudadana(), config);
	}
}
