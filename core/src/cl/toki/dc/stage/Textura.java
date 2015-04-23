package cl.toki.dc.stage;

import cl.toki.dc.interfaces.IVelocidad;

import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author Francisco Garcia
 */
public class Textura extends Texture implements IVelocidad {

	public enum Nivel {
		bajo, medio, alto, extremo
	}

	private int velocidadMovimiento = 0;
	private int velocidadMovimientoDefecto = 0;
	private int delta = 0;
	private int cuadro = 0;

	public Textura(String file, Nivel nivelNelocidad) {
		super(file);

		switch (nivelNelocidad) {
		case bajo:
			velocidadMovimiento = 1;
			velocidadMovimientoDefecto = 1;
			delta = 1;
			cuadro = cuadro + velocidadMovimiento;
			break;
		case medio:
			velocidadMovimiento = 5;
			velocidadMovimientoDefecto = 5;
			delta = 5;
			cuadro = cuadro + velocidadMovimiento;
			break;
		case alto:
			velocidadMovimiento = 10;
			velocidadMovimientoDefecto = 10;
			cuadro = cuadro + velocidadMovimiento;
			delta = 5;
			break;
		case extremo:
			velocidadMovimiento = 15;
			velocidadMovimientoDefecto = 15;
			cuadro = cuadro + velocidadMovimiento;
			delta = 15;
			break;
		}

		setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
	}

	public int getVelocidad() {
		return (int) cuadro;
	}

	public void moverFondo() {
		cuadro = cuadro + velocidadMovimiento;
	}

	@Override
	public void incrementarVel() {
		this.velocidadMovimiento = this.velocidadMovimiento + delta;

	}

	@Override
	public void decrementarVel() {
		this.velocidadMovimiento = this.velocidadMovimiento - delta;
	}

	@Override
	public void resetearVel() {
		this.velocidadMovimiento = velocidadMovimientoDefecto;
		this.cuadro = cuadro + velocidadMovimientoDefecto;
	}

}
