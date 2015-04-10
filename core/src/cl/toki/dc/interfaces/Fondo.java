package cl.toki.dc.interfaces;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author Francisco Garcia
 */
public class Fondo extends Texture{
    private int velocidadMovimiento=0;
    private int cuadro=0;
    private int velocidadMovimientoDefecto=0;
            
            
    public Fondo(String file, int velocidadMovimiento, int velocidadMovimientoDefecto) {
        super(file);
        this.velocidadMovimiento=velocidadMovimiento;
        this.velocidadMovimientoDefecto=velocidadMovimientoDefecto;
        this.cuadro=cuadro+velocidadMovimiento;
        setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
       }
    
    public int getVelocidad(){
        return cuadro;
    }
    
    public void setVelocidadIncremento(int velocidad){
        this.velocidadMovimiento=  this.velocidadMovimiento+velocidad;
    }
    
    public void moverFondo(){
        cuadro=cuadro+velocidadMovimiento;
    }
    
    public void resetear(int velocidad){
        this.velocidadMovimiento=velocidadMovimientoDefecto;
        this.cuadro=cuadro+velocidadMovimientoDefecto;
    }
   
}
