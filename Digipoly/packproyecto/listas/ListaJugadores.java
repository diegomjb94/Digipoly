package org.pmoo.packproyecto.listas;

import java.util.ArrayList;
import java.util.Iterator;

import org.pmoo.packproyecto.individuos.Banca;
import org.pmoo.packproyecto.individuos.Individuo;
import org.pmoo.packproyecto.individuos.Jugador;

public class ListaJugadores {
	
	// Atributos
	private ArrayList<Jugador> lista;
	private static ListaJugadores miListaJugadores = new ListaJugadores();

	// Constructora
	/**
	 * Crea un objeto de tipo ListaJugadores.
	 */
	private ListaJugadores() {
		this.lista = new ArrayList<Jugador>();
	}

	// Getters
	/**
	 * @return la unica ListaJugadores.
	 */
	public static ListaJugadores getMiListaJugadores() {
		return miListaJugadores;
	}

	/**
	 * @return un iterador que permite recorrer la ListaJugadores.
	 */
	public Iterator<Jugador> getIterador() {
		return ListaJugadores.getMiListaJugadores().lista.iterator();
	}

	/**
	 * @return el tamaño de la lista.
	 */
	public int getTamanyo() {
		return this.lista.size();
	}

	// Metodos
	/**
	 * Anade pJugador a ListaJugadores.
	 * pJugador : Jugador
	 */
	public void anyadirJugador(Jugador pJugador) {
		ListaJugadores.getMiListaJugadores().lista.add(pJugador);
		
	}

	/**
	 * @param pId : int - Id del jugador a buscar.
	 * @return el Jugador con identificador pId
	 */
	public Jugador buscarJugadorPorId(int pId) {
		Jugador encontrado = null;
		Iterator<Jugador> itr = this.getIterador();
		
		while (itr.hasNext()) {
			Jugador itrJugador = (Jugador) itr.next();
			if (itrJugador.getIdentificador() == pId) {
				encontrado = itrJugador;
			}
		}
		return encontrado;
	}

	/**
	 * @param pPos : int - Posicion de la Propiedad a averiguar si tiene dueño.
	 * @return si la Propiedad con posicion pPos esta comprada por alguien jugador.
	 */
	public boolean estaComprada(int pPos) {
		return !Banca.getMiBanca().getPropiedades().esta(pPos);
	}

	/**
	 * @param pPos : int - Posicion de la Propiedad a buscar el dueño.
	 * @return el dueño de la Propiedad con posicion pPos.
	 */
	public Individuo buscarComprador(int pPos) {
		Jugador comprador = null;
		Iterator<Jugador> itr = this.getIterador();
		
		while (itr.hasNext() && comprador == null) {
			Jugador itrJugador = (Jugador) itr.next();
			if (itrJugador.getPropiedades().esta(pPos)) {
				comprador = itrJugador;
			}
		}
		return (comprador != null)? comprador:Banca.getMiBanca();
	}

	/**
	 * @return si todos los jugadores menos 1 estan en bancarrota.
	 */
	public boolean hayGanador() {
		int cont = 0;
		Iterator<Jugador> itr = this.getIterador();
		
		while(itr.hasNext() && cont < 2){
			Jugador itrJugador = (Jugador) itr.next();
			if (!itrJugador.getEnBancarrota()) {
				cont ++;
			}
		}
		return cont == 1;
	}

	/**
	 * Ejecuta los turnos de todos los Jugadores que no están en bancarrota. Se detiene si hay un solo Jugador que no este en bacarrota.
	 */
	public void rondaDeTurnos() {
		Iterator<Jugador> itr = this.getIterador();
		
		while (itr.hasNext() && !this.hayGanador()) {
			Jugador itrJugador = (Jugador) itr.next();
			if (!itrJugador.getEnBancarrota()) {
				itrJugador.ejecutarTurno();
			}
		}
	}
}
