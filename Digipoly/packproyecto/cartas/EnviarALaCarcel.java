package org.pmoo.packproyecto.cartas;

import org.pmoo.packproyecto.casillas.Carcel;
import org.pmoo.packproyecto.exceptions.BankruptcyException;
import org.pmoo.packproyecto.exceptions.ImprisonedException;
import org.pmoo.packproyecto.individuos.Jugador;
import org.pmoo.packproyecto.listas.ListaJugadores;

public class EnviarALaCarcel extends Desplazar {

	// Constructora
	/**
	 * Crea un objeto de tipo EnviarALaCarcel.
	 * @param pDescripcion : String - Descripcion de EnviarALaCarcel.
	 */
	public EnviarALaCarcel(String pDescripcion) {
		super(pDescripcion, Carcel.getMiCarcel().getPosicion());
	}
	
	// Métodos
	public void ejecutarCarta(int pId) throws ImprisonedException, BankruptcyException {
		super.ejecutarCarta(pId);
		
		Jugador pJugador = ListaJugadores.getMiListaJugadores().buscarJugadorPorId(pId);
		
		pJugador.enviarALaCarcel();
	}
}