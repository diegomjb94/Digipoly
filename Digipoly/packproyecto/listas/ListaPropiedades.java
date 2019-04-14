package org.pmoo.packproyecto.listas;

import java.util.ArrayList;
import java.util.Iterator;

import org.pmoo.packproyecto.casillas.Calle;
import org.pmoo.packproyecto.casillas.Color;
import org.pmoo.packproyecto.casillas.Propiedad;

public class ListaPropiedades {

	// Atributos
	private ArrayList<Propiedad> lista;

	// Constructora
	/**
	 * Crea un objeto de tipo lista.
	 */
	public ListaPropiedades() {
		this.lista = new ArrayList<Propiedad>();

	}

	// Metodos
	/**
	 * Annade pPropiedad a la lista.
	 * @param pPropiedad : Propiedad
	 */
	public void anyadirPropiedad(Propiedad pPropiedad) {
		this.lista.add(pPropiedad);
		
	}

	public void eliminarPropiedad(Propiedad pPropiedad) {
		this.lista.remove(pPropiedad);
	}
	/**
	 * @return un iterador que permite recorrer la lista.
	 */
	public Iterator<Propiedad> getIterador() {
		return this.lista.iterator();
	}

	/**
	 * Añade todos los objetos de pLista a lista.
	 * @param pLista : ArrayList<Propiedad>
	 */
	public void anyadirColeccion(ArrayList<Propiedad> pLista) {
		this.lista.addAll(pLista);
	}

	/**
	 * @param pPos : int
	 * @return si hay una Propiedad con posicion pPos.
	 */
	public boolean esta(int pPos) {
		boolean encontrado = false;
		Iterator<Propiedad> itr = this.getIterador();
		
		while (itr.hasNext() && !encontrado) {
			Propiedad itrPropiedad = (Propiedad) itr.next();
			if (itrPropiedad.getPosicion() == pPos) {
				encontrado = true;
			}
		}
		return encontrado;
	}

	/**
	 * @param pPos : int
	 * @return la Propiedad con posicion pPos
	 */
	public Propiedad buscarPorPosicion(int pPos) {
		Propiedad encontrado = null;
		Iterator<Propiedad> itr = this.getIterador();
		
		while (itr.hasNext() && encontrado == null) {
			Propiedad itrPropiedad = (Propiedad) itr.next();
			if (itrPropiedad == Tablero.getMiTablero().buscarPorPosicion(pPos)) {
				encontrado = itrPropiedad;
			}
		}
		return encontrado;
	}

	/**
	 * @param pPropiedad : Propiedad
	 * @return la cantidad de objetos de la misma clase que pPropiedad.
	 */
	public int cantidadDelMismoTipo (Propiedad pPropiedad) {
		int cont = 0;
		Iterator<Propiedad> itr = this.getIterador();
		while (itr.hasNext()) {
			Propiedad itrPropiedad = (Propiedad) itr.next();
			if (itrPropiedad.getClass() == pPropiedad.getClass()){
				cont ++;
			}
		}
		return cont;
	}

	/**
	 * @param pColor : Color
	 * @return la cantidad de Calles del color pColor.
	 */
	public int cuantasDelMismoColor(Color pColor) {
		int cont = 0;
		Iterator<Propiedad> itr = this.getIterador();
		while (itr.hasNext()) {
			Propiedad itrPropiedad = (Propiedad) itr.next();
			if (itrPropiedad instanceof Calle && ((Calle)itrPropiedad).getColor() == pColor) {
				cont ++;
			}
		}
		return cont;
	}

	/**
	 * Borra la Propiedad con posicion pPosicion de la lista y la añade a la lista del Jugador con identificador pId.
	 * @param pPosicion : int
	 * @param pId : int
	 */
	public void cambiarDuenyo(int pPosicion, int pId){	
		ListaJugadores.getMiListaJugadores().buscarJugadorPorId(pId).getPropiedades().anyadirPropiedad(this.buscarPorPosicion(pPosicion));
		this.eliminarPropiedad(this.buscarPorPosicion(pPosicion));
	}

	/**
	 * Borra todas las Propiedades y las añade a la lista del Jugador con identificador pId.
	 * @param pId
	 */
	public void cambiarDuenyoTodas(int pId) {
		
		ListaJugadores.getMiListaJugadores().buscarJugadorPorId(pId).getPropiedades().anyadirColeccion(this.lista);
		this.resetear();
	}

	/**
	 * Vacia la lista.
	 */
	private void resetear() {
		this.lista.clear();
	}

	/**
	 * Imprime la información de todas las Propiedades de la lista.
	 */
	public void imprimir() {
		Iterator<Propiedad> itr = this.getIterador();
		while (itr.hasNext()) {
			Propiedad itrPropiedad = (Propiedad) itr.next();
			itrPropiedad.imprimir();
		}
	}
}
