package org.pmoo.packproyecto.casillas;

import org.pmoo.packproyecto.auxiliares.Instrucciones;
import org.pmoo.packproyecto.exceptions.BankruptcyException;
import org.pmoo.packproyecto.exceptions.ImprisonedException;
import org.pmoo.packproyecto.individuos.Jugador;
import org.pmoo.packproyecto.listas.ListaJugadores;

public class Carcel extends Casilla {

	// Atributos
	private int fianza;
	private static Carcel miCarcel = null;

	// Constructora
	/**
	 * Crea un objeto de tipo Carcel con "Cárcel" como nombre.
	 * @param pFianza : int - Fianza de la Carcel.
	 */
	private Carcel(int pFianza) {
		super("Cárcel");
		this.fianza = pFianza;
	}

	// Getters
	/**
	 * Si no existe ninguna Carcel la crea.
	 * @return la única carcel.
	 */
	public static Carcel getMiCarcel() {
		if (miCarcel == null) {
			miCarcel = new Carcel(50);
		}
		return miCarcel;
	}

	/**
	 * @return la fianza.
	 */
	public int getFianza() {
		return this.fianza;
	}

	// Métodos
	/**
	 * Si el jugador está encarcelado se le presentan sus posibles opciones: pagar la fianza, tirar los dados (y, si puede, utilizar una carta de salir
	 * de cárcel). El jugador elige lo que quiere realizar y se ejecuta lo correspondiente. Si elige algo no válido se ejecuta tirar los dados.
	 * @param pId : int - Id del jugador que está en la casilla.
	 * @throws ImprisonedException 
	 * @throws BankruptcyException 
	 */
	@Override
	public void ejecutarCasilla(int pId) throws ImprisonedException, BankruptcyException {
		super.ejecutarCasilla(pId);
		Jugador pJugador = ListaJugadores.getMiListaJugadores().buscarJugadorPorId(pId);
		
		if (pJugador.getTurnosEnCarcel() > 0) {
			System.out.println("Estás en la cárcel.");
			System.out.println("Puedes hacer varias cosas para salir:");
			System.out.println("	1.- Pagar la fianza (" + this.getFianza() + " €).");
			System.out.println("	2.- Tirar los dados con la esperanza de sacar doble.");
			if (pJugador.getCartaSalirDeCarcel() > 0) {
				System.out.println("	3.- Utilizar una tarjeta de Salir de la Cárcel (tienes " + pJugador.getCartaSalirDeCarcel() + ").");
			}
			System.out.println("Escribe el número coorespondiente a la acción que quieres realizar.");
			
			int instruccion;
			instruccion = Instrucciones.recogerNumeroEntre(1, 3);
			
			switch (instruccion) {
			case 1:
				pJugador.pagarFianza();
				pJugador.mover();
				break;
			case 2:
				pJugador.intentarSalirCarcel();
				break;
			default:
				if (pJugador.getCartaSalirDeCarcel() > 0) {
					pJugador.utilizarCartaSalirCarcel();
					pJugador.mover();
				} else {
					System.out.println("Esa opción no está disponible. Intentas salir de la carcel.");
					pJugador.intentarSalirCarcel();
				}
				break;
			}
		} else {
			System.out.println("¡Oh, no! ¡Estás en la cárcel!");
			System.out.println("Pero tranquilo... solo de visita.");
		}
	}
}
