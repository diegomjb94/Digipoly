package org.pmoo.packproyecto.cartas;

import org.pmoo.packproyecto.casillas.Parking;
import org.pmoo.packproyecto.exceptions.BankruptcyException;
import org.pmoo.packproyecto.exceptions.ImprisonedException;
import org.pmoo.packproyecto.individuos.Banca;
import org.pmoo.packproyecto.listas.ListaJugadores;

public class PagarDinero extends CartaDinero {

	// Constructora
	/**
	 * Crea un objeto de tipo PagarDinero.
	 * @param pDescripcion : String - Descripcion de PagarDinero.
	 * @param pDinero : int - Dinero a pagar.
	 */
	public PagarDinero(String pDescripcion, int pDinero) {
		super(pDescripcion, pDinero);
	}
	
	// Métodos
	/**
	 * Se muestra por pantalla la descripcion, el jugador con identificador pId paga a la Banca el dinero y se acumula en Parking.
	 * @throws ImprisonedException 
	 * @throws BankruptcyException 
	 */
	@Override
	public void ejecutarCarta(int pId) throws ImprisonedException, BankruptcyException {
		super.ejecutarCarta(pId);
		Parking.acumular(this.getDinero());
		ListaJugadores.getMiListaJugadores().buscarJugadorPorId(pId).pagarAJugador(this.getDinero(), Banca.getMiBanca().getIdentificador());
	}
}
