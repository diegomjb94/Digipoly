package org.pmoo.packproyecto.individuos;

import org.pmoo.packproyecto.exceptions.BankruptcyException;
import org.pmoo.packproyecto.listas.ListaPropiedades;

public abstract class Individuo {

	// Atributos
	protected int identificador;
	protected String nombre;
	protected int dinero;
	protected ListaPropiedades propiedades;

	// Constructora
	/**
	 * Crea un objeto de tipo Individuo con una lista vacia como propiedades.
	 * @param pId : int - Id del Individuo.
	 * @param pNombre : String - Nombre del Individuo.
	 * @param pDinero : int - Dinero del Individuo.
	 */
	public Individuo(int pId, String pNombre, int pDinero) {
		this.identificador = pId;
		this.nombre = pNombre;
		this.dinero = pDinero;
		this.propiedades = new ListaPropiedades();
	}

	// Getters
	/**
	 * @return el identificador.
	 */
	public int getIdentificador() {
		return this.identificador;
	}

	/**
	 * @return el nombre.
	 */
	public String getNombre() {
		return this.nombre;
	}

	/**
	 * @return el dinero.
	 */
	public int getDinero() {
		return this.dinero;
	}

	/**
	 * @return las propiedades.
	 */
	public ListaPropiedades getPropiedades() {
		return this.propiedades;
	}

	// Setters
	/**
	 * Asigna a dinero, pDinero.
	 * @param pDinero : int
	 */
	protected void setDinero(int pDinero) {
		this.dinero = pDinero;
	}

	// Métodos
	/**
	 * Decrementa en pCantidad dinero y presenta por pantalla la cantidad de dinero que posee.
	 * @param pCantidad : int
	 */
	protected void pagar(int pCantidad) {
		this.setDinero(this.getDinero() - pCantidad);
		System.out.println(this.getNombre() + " paga " + pCantidad + " €. Ahora tiene " + this.getDinero() + " €.");
}

	/**
	 * Incrementa en pCantidad dinero y presenta por pantalla la cantidad de dinero que posee.
	 * @param pCantidad : int
	 */
	protected void cobrar(int pCantidad) {
		this.setDinero(this.getDinero() + pCantidad);
		System.out.println(this.getNombre() + " cobra " + pCantidad + " €. Ahora tiene " + this.getDinero() + " €.");
	}

	public abstract void pagarAJugador(int pCantidad, int pId) throws BankruptcyException;
}
