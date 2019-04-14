package org.pmoo.packproyecto.casillas;

import org.pmoo.packproyecto.exceptions.BankruptcyException;
import org.pmoo.packproyecto.exceptions.ImprisonedException;
import org.pmoo.packproyecto.individuos.Banca;

public class Parking extends Casilla {

	// Atributos
	private static boolean funcional = false;
	private static int dineroAcumulado = 0;

	// Constructora
	/**
	 * Crea un objeto de tipo Parking con "Parking" como nombre.
	 */
	public Parking() {
		super("Parking");
	}

	// Getters
	/**
	 * @return si es funcional.
	 */
	public static boolean getFuncional() {
		return funcional;
	}

	/**
	 * @return el dinero acumulado.
	 */
	public static int getDineroAcumulado() {
		return dineroAcumulado;
	}

	// Setters
	/**
	 * Asigna a funcional, pFuncional.
	 * @param pFuncional : boolean
	 */
	private static void setFuncional(boolean pFuncional) {
		funcional = pFuncional;
	}

	/**
	 * Asigna a dineroAcumulado, pDineroAcumulado.
	 * @param pDineroAcumulado : int
	 */
	private static void setDineroAcumulado(int pDineroAcumulado) {
		dineroAcumulado = pDineroAcumulado;
	}

	// Métodos
	/**
	 * Asigna a funcional, true.
	 */
	public static void activar() {
		setFuncional(true);
	}

	/**
	 * Si es funcional incrementa en pCantidad el atributo dineroAcumulado.
	 * @param pCantidad : int - Dinero a acumular.
	 */
	public static void acumular(int pCantidad) {
		if (getFuncional()) {
			setDineroAcumulado(getDineroAcumulado() + pCantidad);
		}
	}

	/**
	 * Si es funcional la Banca paga a el Jugador el dinero acumulado y este se deja a 0, si no, no ocurre nada.
	 * @param pId : int - Id del Jugador en Parking.
	 * @throws BankruptcyException 
	 * @throws ImprisonedException 
	 */
	@Override
	public void ejecutarCasilla(int pId) throws ImprisonedException, BankruptcyException {
		super.ejecutarCasilla(pId);
		if (getFuncional()) {
			Banca.getMiBanca().pagarAJugador(getDineroAcumulado(), pId);
			setDineroAcumulado(0);
		}
	}
}
