package cl.toki.dc.util;

/**
 *
 * @author Francisco Garcia
 */
public class TiempoUtil {

	public static long getSegTrasncurridos(long milSegInicio, long milSegFin) {
		long segundosTrans = (milSegFin - milSegInicio) / 1000;

		return segundosTrans;
	}

	public static long getMilTrasncurridos(long milSegInicio, long milSegFin) {
		long milTrans = milSegFin - milSegInicio;

		return milTrans;
	}

	public static boolean tiempoContenido(int tiempoBuscar, int... tiempoLista) {

		for (int i = 0; i < tiempoLista.length; i++) {
			if (tiempoLista[i] == tiempoBuscar) {
				return true;
			}
		}
		return false;
	}
}
