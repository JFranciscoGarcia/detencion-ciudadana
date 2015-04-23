package cl.toki.dc.util;

import cl.toki.dc.interfaces.JuegoCore;

import com.badlogic.gdx.utils.Timer.Task;

/**
 *
 * @author Francisco Garcia
 */

//TODO logica fuerte!
public class CtrlTiempoJuegoTask extends Task {

    private int segundosRestantes;
    private int tiempoJuego;
    private boolean seCumplioTiempo;
    private final JuegoCore escenario;

    public CtrlTiempoJuegoTask(JuegoCore escenario, int tiempoJuego) {
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

    public int getTiempoTranscurridos() {
        return tiempoJuego-segundosRestantes;
    }
    
    public String getTiempoRestanteStr() {
        return String.valueOf(getTiempoRestante());
    }
 
    private void tiempoCumplido(){
        escenario.finalizarJuego();
    }

}
