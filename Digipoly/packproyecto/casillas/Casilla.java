package org.pmoo.packproyecto.casillas;

import org.pmoo.packproyecto.exceptions.BankruptcyException;
import org.pmoo.packproyecto.exceptions.ImprisonedException;
import org.pmoo.packproyecto.individuos.Jugador;
import org.pmoo.packproyecto.listas.ListaJugadores;

public abstract class Casilla {

	// Atributos
	protected int posicion;
	protected static int nuevaPos = 1;
	protected String nombre;

	// Constructora
	/**
	 * Crea un objeto de tipo Casilla con una posición única y actualiza nuevaPos.
	 * @param pNombre : String - Nombre de la Calle.
	 */
	public Casilla(String pNombre) {
		this.posicion = getNuevaPos();
		setNuevaPos(nuevaPos + 1);
		this.nombre = pNombre;
	}

	// Getters
	/**
	 * @return la posicion.
	 */
	public int getPosicion() {
		return this.posicion;
	}

	/**
	 * @return la nueva posicion.
	 */
	private static int getNuevaPos() {
		return nuevaPos;
	}

	/**
	 * @return el nombre.
	 */
	public String getNombre() {
		return this.nombre;
	}

	// Setters
	private static void setNuevaPos(int pNuevaPos) {
		nuevaPos = pNuevaPos;
	}
	/**
	 * Presenta por pantalla que un jugador a caido en la casilla.
	 * @param pId - Es el valor de identificador de un único objeto de tipo Jugador.
	 * @throws ImprisonedException 
	 * @throws BankruptcyException 
	 */
	// Métodos
	public void ejecutarCasilla(int pId) throws ImprisonedException, BankruptcyException {
		Jugador pJugador = ListaJugadores.getMiListaJugadores().buscarJugadorPorId(pId);
		
		System.out.println("El Jugador " + pJugador.getNombre() + " a caido en la casilla número " + this.getPosicion() + ": " + this.getNombre() + '.');
	}
}
