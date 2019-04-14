package org.pmoo.packproyecto.listas;

import java.util.ArrayList;
import java.util.Random;

import org.pmoo.packproyecto.cartas.Carta;

public abstract class ListaCartas {

	// Atributos
	private ArrayList<Carta> lista;

	//Constructora
	/**
	 * Crea un objeto de tipo ListaCartas con la lista vacia.
	 */
	public ListaCartas() {
		this.lista = new ArrayList<Carta>();
	}

	// Getters
	/**
	 * @param indice : int
	 * @return la carta en la posición indice.
	 */
	public Carta getCartaEnPos(int indice) {
		return this.lista.get(indice);
	}

	/**
	 * @return el número de cartas en la lista.
	 */
	public int getTamanyo() {
		return this.lista.size();
	}

	// Métodos
	/**
	 * Añade a la lista la carta pCarta.
	 * @param pCarta
	 */
	public void anyadirCarta(Carta pCarta) {
		this.lista.add(pCarta);
	}

	/**
	 * @return una carta aleatoria de la lista.
	 */
	public Carta cogerCarta() {
		int randomPos = new Random().nextInt(this.getTamanyo());
		return this.getCartaEnPos(randomPos);
	}

	public abstract void inicializar();
}
