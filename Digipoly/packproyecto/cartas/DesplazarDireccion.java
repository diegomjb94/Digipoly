package org.pmoo.packproyecto.cartas;

import org.pmoo.packproyecto.exceptions.BankruptcyException;
import org.pmoo.packproyecto.exceptions.ImprisonedException;

public abstract class DesplazarDireccion extends Desplazar {

	// Constructora
	/**
	 * Crea un objeto de tipo DesplazarDireccion.
	 * @param pDescripcion : String - Descripcion de DesplazarDireccion.
	 * @param pPosicion : int - Posiciones que desplaza.
	 */
	public DesplazarDireccion(String pDescripcion, int pPosicion) {
		super(pDescripcion, pPosicion);
	}

	// Métodos
		public void ejecutarCarta(int pId) throws ImprisonedException, BankruptcyException {
			super.ejecutarCarta(pId);
		}
}
