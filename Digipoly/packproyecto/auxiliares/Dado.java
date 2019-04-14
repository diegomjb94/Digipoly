package org.pmoo.packproyecto.auxiliares;

import java.util.Random;

public class Dado {

	// Atributos
	private int caras;
	private static Dado miDado = new Dado(6);

	// Constructora
	/**
	 * Crea un objeto de tipo Dado.
	 * @param pCaras : int - Numero de caras del Dado.
	 */
	private Dado(int pCaras) {
		this.caras = pCaras;
	}

	// Getters
	/**
	 * @return el único objeto de tipo Dado.
	 */
	public static Dado getMiDado() {
		return miDado;
	}

	/**
	 * @return las caras.
	 */
	public int getCaras() {
		return this.caras;
	}

	// Métodos
	/**
	 * @return un número aleatorio entre 1 y caras (ambos incluidos).
	 */
	public int tirarDado() {
		int num = new Random().nextInt(this.getCaras()) + 1;
		System.out.println("Has sacado un " + num + '.');
		return num;
	}
}
