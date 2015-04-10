package cl.toki.dc.util;

import cl.toki.dc.interfaces.Escenario;

import com.badlogic.gdx.utils.Timer.Task;

/**
 *
 * @author Francisco Garcia
 */
public class CtrlTiempoJuegoTask extends Task {

    private int segundosRestantes;
    private int tiempoJuego;
    private boolean seCumplioTiempo;
    private final Escenario escenario;

    public CtrlTiempoJuegoTask(Escenario escenario, int tiempoJuego) {
        this.escenario= escenario;
        this.tiempoJuego=tiempoJuego;
        segundosRestantes= tiempoJuego;
        seCumplioTiempo = false;
    }

    @Override
    public void run() {

        if (!seCumplioTiempo) {
            segundosRestantes -= 1;
        }

        if (segundosRestantes == 0) {
            seCumplioTiempo = true;
            tiempoCumplido();
        }
    }

    public int getTiempoRestante() {
        return segundosRestantes;
    }

    public int getTiempoJuego() {
        return tiempoJuego;
    }
    
    public int getTiempoTranscurridos() {
        return tiempoJuego-segundosRestantes;
    }
    
    public String getTiempoRestanteStr() {
        return String.valueOf(getTiempoRestante());
    }
    //TODO
    private void tiempoCumplido(){
        escenario.finalizarNivel();
    }

}
