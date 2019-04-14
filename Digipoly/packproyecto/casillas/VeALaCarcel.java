package org.pmoo.packproyecto.casillas;

import org.pmoo.packproyecto.exceptions.BankruptcyException;
import org.pmoo.packproyecto.exceptions.ImprisonedException;
import org.pmoo.packproyecto.individuos.Jugador;
import org.pmoo.packproyecto.listas.ListaJugadores;

public class VeALaCarcel extends Casilla {

	// Constructora
	/**
	 * Crea un objeto de tipo VeALaCarcel con "Ve a la cárcel" como nombre.
	 */
	public VeALaCarcel() {
		super("Ve a la cárcel");
	}

	// Métodos
	/**
	 * Envía a la carcel al jugador.
	 * @param pId : int - Id del Jugador en VeALaCarcel.
	 * @throws ImprisonedException 
	 * @throws BankruptcyException 
	 */
	@Override
	public void ejecutarCasilla(int pId) throws ImprisonedException, BankruptcyException {
		super.ejecutarCasilla(pId);
		Jugador pJugador = ListaJugadores.getMiListaJugadores().buscarJugadorPorId(pId);

		pJugador.enviarALaCarcel();
	}
}
