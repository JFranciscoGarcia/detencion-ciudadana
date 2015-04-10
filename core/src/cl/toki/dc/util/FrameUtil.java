package cl.toki.dc.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 *
 * @author Francisco Garcia
 */
public class FrameUtil {
    
      public static TextureRegion[] cortarTextura(String nombreImagen, int cantPartesVertical, int cantPartesHorizontal, boolean invertir) {
        
        Texture imagen = new Texture(Gdx.files.internal(nombreImagen));
        TextureRegion[][] tmp = TextureRegion.split(imagen, imagen.getWidth() / cantPartesVertical, imagen.getHeight() / cantPartesHorizontal);              // #1
        TextureRegion[] frame = new TextureRegion[cantPartesVertical * cantPartesHorizontal];
        
        int index = 0;
        for (int i = 0; i < cantPartesHorizontal; i++) {
            for (int j = 0; j < cantPartesVertical; j++) {
                frame[index++] = tmp[i][j];
            }
        }

        if(invertir){
            for (int j = 0; j < frame.length; j++) {
                frame[j].flip(true, false);
            }
        }
        
        
        return frame;
    }
}
