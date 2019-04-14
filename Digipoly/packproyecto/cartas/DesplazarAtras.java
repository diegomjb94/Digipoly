package org.pmoo.packproyecto.cartas;

import org.pmoo.packproyecto.exceptions.BankruptcyException;
import org.pmoo.packproyecto.exceptions.ImprisonedException;
import org.pmoo.packproyecto.individuos.Jugador;
import org.pmoo.packproyecto.listas.ListaJugadores;
import org.pmoo.packproyecto.listas.Tablero;

public class DesplazarAtras extends Desplazar {

	// Constructora
	/**
	 * Crea un objeto de tipo DesplazarAtras.
	 * @param pDescripcion : String - Descripcion de DesplazarAtras.
	 * @param pPosicion : int - Posiciones que desplaza hacia atras.
	 */
	public DesplazarAtras(String pDescripcion, int pPosicion) {
		super(pDescripcion, pPosicion);
	}

	// Métodos
		public void ejecutarCarta(int pId) throws ImprisonedException, BankruptcyException {
			super.ejecutarCarta(pId);
			
			Jugador pJugador = ListaJugadores.getMiListaJugadores().buscarJugadorPorId(pId);
			
			pJugador.setPosicion(pJugador.getPosicion() - this.getPosicion());
			while (pJugador.getPosicion() < 1) {
				pJugador.setPosicion(pJugador.getPosicion() + Tablero.getMiTablero().getTamanyo());
			}
			pJugador.ejecutarCasilla();
		}
}
