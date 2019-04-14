package org.pmoo.packproyecto.casillas;

import org.pmoo.packproyecto.exceptions.BankruptcyException;
import org.pmoo.packproyecto.exceptions.ImprisonedException;
import org.pmoo.packproyecto.listas.CajaDeComunidad;

public class CasillaCajaDeComunidad extends CasillaCarta {

	// Constructora
	/**
	 * Crea un objeto de tipo CasillaCajaDeComunidad con "Caja de Comunidad" como nombre.
	 */
	public CasillaCajaDeComunidad () {
		super("Caja de Comunidad");
	}
	
	// Métodos
	/**
	 * Coge una carta aleatoria de la lista de cartas CajaDeComunidad y la ejecuta.
	 * @throws ImprisonedException 
	 * @throws BankruptcyException 
	 */
	public void ejecutarCasilla(int pId) throws ImprisonedException, BankruptcyException {
		super.ejecutarCasilla(pId);
		CajaDeComunidad.getMiCajaDeComunidad().cogerCarta().ejecutarCarta(pId);
	}
}
