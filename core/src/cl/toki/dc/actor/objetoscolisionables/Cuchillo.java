package cl.toki.dc.actor.objetoscolisionables;

import java.util.Random;

import cl.toki.dc.interfaces.JuegoCore;
import cl.toki.dc.interfaces.Objeto;
import cl.toki.dc.util.Constantes;
import cl.toki.dc.util.FrameUtil;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;

/**
 *
 * @author Francisco Garcia
 */
public class Cuchillo extends Objeto {

    public Cuchillo(JuegoCore escenario) {
        super(escenario, Constantes.ANCHO_MAXIMO, Constantes.ALTO_MAXIMO / 6,100);
        verificarAsignarVelocidad();
    }

    @Override
    public Animation getAnimacion() {
        Random random = new Random();
        float animacionR = (random.nextFloat() / 0.3f > 0.3f) ? 0.3f : random.nextFloat() / 0.3f;

        return new Animation(animacionR, FrameUtil.cortarTextura("cuchillo.png", 1, 1, false));
    }

    @Override
    public void realizarAnimacionColision() {

        ParallelAction seq = new ParallelAction();

       // MoveToAction moveAction = new MoveToAction();
        RotateToAction rotateAction = new RotateToAction();
        //ScaleToAction scaleAction = new ScaleToAction();

        setOrigin(getWidth() / 2, getHeight() / 2);

        rotateAction.setRotation(180f);
        rotateAction.setDuration(0.2f);

        //scaleAction.setScale(0.8f);
        //scaleAction.setDuration(0.5f);
        seq.addAction(rotateAction);
        //seq.addAction(scaleAction);

        addAction(seq);
    }

    @Override
    public void incrementarVel() {
        verificarAsignarVelocidad();
    }

    @Override
    public void decrementarVel() {
        verificarAsignarVelocidad();
    }

    @Override
    public void resetearVel() {
        verificarAsignarVelocidad();
    }

    private void verificarAsignarVelocidad() {
        switch (escenario.pinera.nivelActualVel) {
            case uno:
                velocidad = 5;
                break;
            case dos:
                velocidad = 10;
                break;
            case tres:
                velocidad = 15;
                break;
        }
    }
}
