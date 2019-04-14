package org.pmoo.packproyecto.cartas;

import org.pmoo.packproyecto.exceptions.BankruptcyException;
import org.pmoo.packproyecto.exceptions.ImprisonedException;
import org.pmoo.packproyecto.individuos.Banca;

public class CobrarDinero extends CartaDinero {
	
		// Constructora
		/**
		 * Crea un objeto de tipo CobrarDinero.
		 * @param pDescripcion : String - Descripcion de CobrarDinero.
		 * @param pDinero : int - Dinero de CobrarDinero.
		 */
		public CobrarDinero(String pDescripcion, int pDinero) {
			super(pDescripcion, pDinero);
		}

		// Métodos
		/**
		 * Se muestra por pantalla la descripcion y el jugador con identificador pId cobra el dinero.
		 * @throws ImprisonedException 
		 * @throws BankruptcyException 
		 */
		@Override
		public void ejecutarCarta(int pId) throws ImprisonedException, BankruptcyException {
			super.ejecutarCarta(pId);
			Banca.getMiBanca().pagarAJugador(this.getDinero(), pId);
		}
}
