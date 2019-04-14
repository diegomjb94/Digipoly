package org.pmoo.packproyecto.individuos;

import org.pmoo.packproyecto.listas.ListaJugadores;

public class Banca extends Individuo {

	// Atributos
	private static Banca miBanca = new Banca();

	// Constructora
	/**
	 * Crea un objeto de tipo Banca con id 0, nombre "Banca" y dinero 1000000000.
	 */
	private Banca(){
		super(0, "Banca", 1000000000);
	}

	// Getters
	/**
	 * @return La única Banca existente.
	 */
	public static Banca getMiBanca() {
		return miBanca;
	}

	// Métodos
	/**
	 * Después de pagar al jugador con id pId comprueba si el dinero es negativo. Si es el caso, ejecuta un rescate financiero.
	 * @param pCantidad : int - Cantidad a pagar.
	 * @param pId : int - Id del jugador que recibe el dinero.
	 */
	@Override
	public void pagarAJugador(int pCantidad, int pId) {
		Jugador pJugador = ListaJugadores.getMiListaJugadores().buscarJugadorPorId(pId);
		
		this.pagar(pCantidad);
		pJugador.cobrar(pCantidad);
		if (this.getDinero() < 0) {
			this.rescateFinanciero();
		}
	}

	/**
	 * Asigna a dinero 1000000000.
	 */
	private void rescateFinanciero() {
		System.out.println("¡Habéis dejado a la Banca sin un €!");
		System.out.println("La Banca pide un rescate y recupera su ejectivo, 1000000000 €.");
		this.setDinero(1000000000);
	}
}
