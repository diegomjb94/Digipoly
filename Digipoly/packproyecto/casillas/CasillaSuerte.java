package org.pmoo.packproyecto.casillas;

import org.pmoo.packproyecto.exceptions.BankruptcyException;
import org.pmoo.packproyecto.exceptions.ImprisonedException;
import org.pmoo.packproyecto.listas.Suerte;

public class CasillaSuerte extends CasillaCarta {

	// Constructora
	/**
	 * Crea un objeto de tipo CasillaSuerte con "Suerte" como nombre.
	 */
	public CasillaSuerte () {
		super("Suerte");
	}
	
	// Métodos
	/**
	 * Coge una carta aleatoria de la lista de cartas Suerte y la ejecuta.
	 * @throws ImprisonedException 
	 * @throws BankruptcyException 
	 */
	public void ejecutarCasilla(int pId) throws ImprisonedException, BankruptcyException {
		super.ejecutarCasilla(pId);
		Suerte.getMiSuerte().cogerCarta().ejecutarCarta(pId);
	}
}
