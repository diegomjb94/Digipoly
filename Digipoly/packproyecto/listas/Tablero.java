package org.pmoo.packproyecto.listas;

import java.util.ArrayList;
import java.util.Iterator;

import org.pmoo.packproyecto.casillas.Calle;
import org.pmoo.packproyecto.casillas.Carcel;
import org.pmoo.packproyecto.casillas.Casilla;
import org.pmoo.packproyecto.casillas.CasillaCajaDeComunidad;
import org.pmoo.packproyecto.casillas.CasillaSuerte;
import org.pmoo.packproyecto.casillas.Color;
import org.pmoo.packproyecto.casillas.Estacion;
import org.pmoo.packproyecto.casillas.Multa;
import org.pmoo.packproyecto.casillas.Parking;
import org.pmoo.packproyecto.casillas.Propiedad;
import org.pmoo.packproyecto.casillas.Salida;
import org.pmoo.packproyecto.casillas.ServicioPublico;
import org.pmoo.packproyecto.casillas.VeALaCarcel;
import org.pmoo.packproyecto.individuos.Banca;

public class Tablero {

	// Atributos
	private ArrayList<Casilla> lista;
	private static Tablero miTablero = new Tablero();

	// Constructora
	/**
	 * Crea un objeto de tipo Tablero.
	 */
	private Tablero() {
		this.lista = new ArrayList<Casilla>();
	}

	// Getters
	/**
	 * @return el unico Tablero.
	 */
	public static Tablero getMiTablero() {
		return miTablero;
	}

	/**
	 * @return un iterador que permite recorrer el Tablero.
	 */
	public Iterator<Casilla> getIterador() {
		return this.lista.iterator();
	}

	/**
	 * @return el tamanno de Tablero.
	 */
	public int getTamanyo() {
		return this.lista.size();
	}

	// Métodos
	/**
	 * Annade pCasilla a Tablero.
	 * pCasilla : Casilla
	 */
	private void anyadirCasilla(Casilla pCasilla) {
		this.lista.add(pCasilla);
	}

	/**
	 * @param pPosicion : int
	 * @return el objeto de tipo Casilla de Tablero cuya posicion es pPosicion.
	 */
	public Casilla buscarPorPosicion(int pPosicion) {
		Casilla encontrado = null;
		Iterator<Casilla>itr= this.getIterador();
		
		while (itr.hasNext() && encontrado == null) {
			Casilla itrCasilla = (Casilla) itr.next();
			if (itrCasilla.getPosicion() == pPosicion) {
				encontrado = itrCasilla;
			}
			
		}
		return encontrado;
	}

	/**
	 * @param pColor : Color
	 * @return el numero de Calles cuyo color es pColor en Tablero.
	 */
	public int cuantasDelMismoColor(Color pColor) {
		Casilla encontrado = null;
		int cont = 0;
		Iterator<Casilla>itr= this.getIterador();
		
		while (itr.hasNext() && encontrado == null) {
			Casilla itrCasilla = (Casilla) itr.next();
			if (itrCasilla instanceof Calle && ((Calle)itrCasilla).getColor() == pColor){
				cont ++;
			}
		}
		return cont;
	}

	/**
	 * Inicializa a Tablero (la casilla con posicion 1 debe ser la Salida y debe haber una única Carcel).
	 */
	public void inicializar() {
		this.anyadirCasilla(Salida.getMiSalida()); // Posicion 1
		this.anyadirCasilla(new Calle("Ronda de Valencia", 60, 2, Color.MARRON, 50, 30)); // Posicion 2
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(2));
		this.anyadirCasilla(new CasillaCajaDeComunidad()); // Posicion 3
		this.anyadirCasilla(new Calle("Plaza Lavapies", 60, 4, Color.MARRON, 50, 30)); // Posicion 4
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(4));
		this.anyadirCasilla(new Multa("Impuesto sobre el capital", 200)); // Posicion 5
		this.anyadirCasilla(new Estacion("Estacion de Goya")); // Posicion 6
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(6));
		this.anyadirCasilla(new Calle("Glorieta cuatro caminos", 100, 6, Color.CYAN, 50, 50)); // Posicion 7
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(7));
		this.anyadirCasilla(new CasillaSuerte()); // Posicion 8
		this.anyadirCasilla(new Calle("Avenida de Reina Victoria", 100, 6, Color.CYAN, 50, 50)); // Posicion 9
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(9));
		this.anyadirCasilla(new Calle("Calle Bravo Murillo", 120, 8, Color.CYAN, 50, 60)); // Posicion 10
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(10));
		
		this.anyadirCasilla(Carcel.getMiCarcel()); // Posicion 11
		this.anyadirCasilla(new Calle("Glorieta de Bilbao", 140, 10, Color.ROSA, 100, 70)); // Posicion 12
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(12));
		this.anyadirCasilla(new ServicioPublico("Compañia de electricidad")); // Posicion 13
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(13));
		this.anyadirCasilla(new Calle("Calle Alberto Aguilera", 140, 10, Color.ROSA, 100, 70)); // Posicion 14
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(14));
		this.anyadirCasilla(new Calle("Calle Fuencarral", 160, 12, Color.ROSA, 100, 80)); // Posicion 15
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(15));
		this.anyadirCasilla(new Estacion("Estacion de las Delicias")); // Posicion 16
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(16));
		this.anyadirCasilla(new Calle("Avda. Felipe II", 180, 14, Color.NARANJA, 100, 90)); // Posicion 17
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(17));
		this.anyadirCasilla(new CasillaCajaDeComunidad()); // Posicion 18
		this.anyadirCasilla(new Calle("Calle Velazquez", 180, 14, Color.NARANJA, 100, 90)); // Posicion 19
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(19));
		this.anyadirCasilla(new Calle("Calle Serrano", 200, 16, Color.NARANJA, 100, 100)); // Posicion 20
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(20));
		
		this.anyadirCasilla(new Parking()); // Posicion 21
		this.anyadirCasilla(new Calle("Avenida de America", 220, 18, Color.ROJO, 150, 110)); // Posicion 22
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(22));
		this.anyadirCasilla(new CasillaSuerte()); // Posicion 23
		this.anyadirCasilla(new Calle("Calle Maria de Molina", 220, 18, Color.ROJO, 150, 110)); // Posicion 24
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(24));
		this.anyadirCasilla(new Calle("Calle de Cea Bermudez", 240, 20, Color.ROJO, 150, 120)); // Posicion 25
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(25));
		this.anyadirCasilla(new Estacion("Estacion del Mediodia")); // Posicion 26
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(26));
		this.anyadirCasilla(new Calle("Avenida de Los Reyes Catolicos", 260, 22, Color.AMARILLO, 150, 130)); // Posicion 27
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(27));
		this.anyadirCasilla(new Calle("Calle Bailen", 260, 22, Color.AMARILLO, 150, 130)); // Posicion 28
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(28));
		this.anyadirCasilla(new ServicioPublico("Compañia de distribucion de aguas")); // Posicion 29
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(29));
		this.anyadirCasilla(new Calle("Plaza de España", 280, 24, Color.AMARILLO, 150, 140)); // Posicion 30
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(30));
		
		this.anyadirCasilla(new VeALaCarcel()); // Posicion 31
		this.anyadirCasilla(new Calle("Puerta del Sol", 300, 26, Color.VERDE, 200, 150)); // Posicion 32
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(32));
		this.anyadirCasilla(new Calle("Calle de Alcala", 300, 26, Color.VERDE, 200, 150)); // Posicion 33
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(33));
		this.anyadirCasilla(new CasillaCajaDeComunidad()); // Posicion 34
		this.anyadirCasilla(new Calle("Gran Via", 320, 28, Color.VERDE, 200, 160)); // Posicion 35
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(35));
		this.anyadirCasilla(new Estacion("Estacion del Norte")); // Posicion 36
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(36));
		this.anyadirCasilla(new CasillaSuerte()); // Posicion 37
		this.anyadirCasilla(new Calle("Paseo de la Castellana", 350, 35, Color.ULTRAMARINO, 200, 175)); // Posicion 38
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(38));
		this.anyadirCasilla(new Multa("Impuesto de lujo", 100)); // Posicion 39
		this.anyadirCasilla(new Calle("Paseo del Prado", 400, 50, Color.ULTRAMARINO, 200, 200)); // Posicion 40
		Banca.getMiBanca().getPropiedades().anyadirPropiedad((Propiedad) this.buscarPorPosicion(40));
	}
}