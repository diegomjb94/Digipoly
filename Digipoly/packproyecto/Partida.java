package org.pmoo.packproyecto;

import org.pmoo.packproyecto.auxiliares.Instrucciones;
import org.pmoo.packproyecto.casillas.Parking;
import org.pmoo.packproyecto.casillas.Salida;
import org.pmoo.packproyecto.individuos.Jugador;
import org.pmoo.packproyecto.listas.CajaDeComunidad;
import org.pmoo.packproyecto.listas.ListaJugadores;
import org.pmoo.packproyecto.listas.Suerte;
import org.pmoo.packproyecto.listas.Tablero;

public class Partida {

	// Métodos
	/**
	 * Realiza las inicializaciones del Tablero y las ListasCartas.
	 */
	private static void inicializaciones() {
		Tablero.getMiTablero().inicializar();
		CajaDeComunidad.getMiCajaDeComunidad().inicializar();
		Suerte.getMiSuerte().inicializar();
	}

	/**
	 * Añade jugadores a la ListaJugadores hasta que el usuario decida parar.
	 */
	private static void anyadirJugadores() {
		System.out.println("Si escribes '-' dejarás de añadir jugadores.");
		String nombre;
		do {
			System.out.println("Escribe el nombre del Jugador " + Jugador.getNuevoId() + '.');
			nombre = Instrucciones.recogerNombre();
			if (nombre.charAt(0) == '-') {
				if (Jugador.getNuevoId() < 3) {
					System.out.println("Debes añadir al menos 2 jugadores.");
				}
			} else {
				ListaJugadores.getMiListaJugadores().anyadirJugador(new Jugador(nombre));
			}
		} while (nombre.charAt(0) != '-' || Jugador.getNuevoId() < 3);
	}

	/**
	 * Da la opción de activar las funcionalidades extra.
	 */
	private static void funcionalidadesExtra() {
		System.out.println("¿Quieres que al caer directamente en la casilla Salida se cobre el doble? S/N");
		boolean instruccion = Instrucciones.recogerConfirmacion();
		if (instruccion) {
			Salida.getMiSalida().activar();
		}

		System.out.println("¿Quieres que se recaude el dinero de las multas y lo gane el jugador que caiga en Parking? S/N");
		instruccion = Instrucciones.recogerConfirmacion();
		if (instruccion) {
			Parking.activar();
		}
	}

	//////////////////////////////////////// Programa Principal ////////////////////////////////////////
	/**
	 * Comienza la partida.
	 * @param args
	 */
	public static void main(String[] args) {

		inicializaciones();
		anyadirJugadores();
		funcionalidadesExtra();
		
		while (!ListaJugadores.getMiListaJugadores().hayGanador()) {
			ListaJugadores.getMiListaJugadores().rondaDeTurnos();
		}
	}
	//////////////////////////////////////////////// Fin ////////////////////////////////////////////////
}
