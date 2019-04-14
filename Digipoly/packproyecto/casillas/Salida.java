package org.pmoo.packproyecto.casillas;

import org.pmoo.packproyecto.exceptions.BankruptcyException;
import org.pmoo.packproyecto.exceptions.ImprisonedException;
import org.pmoo.packproyecto.individuos.Banca;

public class Salida extends Casilla {

	// Atributos
	private int salario;
	private boolean funcionalDoble;
	private static Salida miSalida = null;

	// Constructora
	/**
	 * Crea un objeto de tipo Salida con "Salida" como nombre y false como funcionalDoble.
	 * @param pSalario - Salario de Salida.
	 */
	private Salida(int pSalario) {
		super("Salida");
		this.salario = pSalario;
		this.funcionalDoble = false;
	}

	// Getters
	/**
	 * Si no existe ninguna Salida la crea.
	 * @return la única Salida.
	 */
	public static Salida getMiSalida() {
		if (miSalida == null) {
			miSalida = new Salida(200);
		}
		return miSalida;
	}

	/**
	 * @return si es funcional.
	 */
	public boolean getFuncionalDoble() {
		return this.funcionalDoble;
	}

	/**
	 * @return el salario.
	 */
	public int getSalario() {
		return this.salario;
	}

	// Setters
	/**
	 * Asigna a funcionalDoble, pFuncional
	 * @param pFuncional : boolean
	 */
	private void setFuncionalDoble(boolean pFuncional) {
		this.funcionalDoble = pFuncional;
	}

	// Métodos
	/**
	 * Asigna a funcionalDoble, true.
	 */
	public void activar() {
		this.setFuncionalDoble(true);
	}

	/**
	 * Si es funcional la Banca paga al Jugador el salario.
	 * @param pId : int - Id del Jugador en la Salida.
	 * @throws BankruptcyException 
	 * @throws ImprisonedException 
	 */
	@Override
	public void ejecutarCasilla(int pId) throws ImprisonedException, BankruptcyException {
		super.ejecutarCasilla(pId);
		if (this.getFuncionalDoble()) {
			Banca.getMiBanca().pagarAJugador(this.getSalario(), pId);
		}
	}
}
