package org.pmoo.packproyecto.casillas;

import org.pmoo.packproyecto.auxiliares.Instrucciones;
import org.pmoo.packproyecto.exceptions.BankruptcyException;
import org.pmoo.packproyecto.exceptions.ImprisonedException;
import org.pmoo.packproyecto.exceptions.MortgageException;
import org.pmoo.packproyecto.individuos.Banca;
import org.pmoo.packproyecto.individuos.Jugador;
import org.pmoo.packproyecto.listas.ListaJugadores;

public abstract class Propiedad extends Casilla {

	// Atributos
	protected int precio;
	protected int alquiler;
	protected int precioHipotecar;
	protected boolean hipotecado;

	// Constructora
	/**
	 * Crea un objeto de tipo Propiedad con false como hipotecado.
	 * @param pNombre : String - Nombre de la Propiedad.
	 * @param pPrecio : int - Precio de la Propiedad.
	 * @param pAlquiler : int - Alquiler de la Propiedad.
	 * @param pPrecioHipotecar : int - PrecioHipotecar de la Propiedad.
	 */
	public Propiedad(String pNombre, int pPrecio, int pAlquiler, int pPrecioHipotecar) {
		super(pNombre);
		this.precio = pPrecio;
		this.alquiler = pAlquiler;
		this.precioHipotecar = pPrecioHipotecar;
		this.hipotecado = false;
	}

	// Getters
	/**
	 * @return si esta hipotecado.
	 */
	public boolean getHipotecado() {
		return this.hipotecado;
	}

	/**
	 * @return el precio.
	 */
	public int getPrecio() {
		return this.precio;
	}

	/**
	 * @return el alquiler.
	 */
	public int getAlquiler() {
		return this.alquiler;
	}

	/**
	 * @return el precioHipotecar.
	 */
	public int getPrecioHipotecar() {
		return this.precioHipotecar;
	}

	// Setters
	/**
	 * Asigna a hipotecado, pHipotecado.
	 * @param pHipotecado : boolean
	 */
	private void setHipotecado(boolean pHipotecado) {
		this.hipotecado = pHipotecado;
	}

	// Métodos
	/**
	 * Si no está hipotecado lo hipoteca y la Banca paga al Jugador la hipoteca.
	 */
	public void hipotecar() {
		try {
			if (this.getHipotecado()) {
				throw new MortgageException("Ya está hipotecada.");
			} else {
				Jugador pJugador = (Jugador) ListaJugadores.getMiListaJugadores().buscarComprador(this.getPosicion());
				
				this.setHipotecado(true);
				Banca.getMiBanca().pagarAJugador(this.getPrecioHipotecar(),pJugador.getIdentificador());
			}
		} catch (MortgageException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Si está hipotecado lo hipoteca y el jugador paga la hipoteca.
	 * @throws BankruptcyException 
	 */
	public void deshipotecar() throws BankruptcyException {
		try {
			if (!this.getHipotecado()) {
				throw new MortgageException("No está hipotecada.");
			} else {
				Jugador pJugador = (Jugador) ListaJugadores.getMiListaJugadores().buscarComprador(this.getPosicion());
				
				this.setHipotecado(false);
				pJugador.pagarAJugador(this.getPrecioHipotecar(), Banca.getMiBanca().getIdentificador());
			}
		} catch (MortgageException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * El jugador paga la cantidad correspondiente.
	 * @param pId : int - Es el valor de identificador en un único objeto de tipo Jugador.
	 * @throws BankruptcyException 
	 */
	protected abstract void alquilar(int pId) throws BankruptcyException;

	/**
	 * Si está comprada llama a alquilar, si no, ofrece la posibilidad de comprarla al jugador y la compra si este quiere por su precio original
	 * o la subasta si no la quiere.
	 * @param pId : int - Id del Jugador en la Propiedad.
	 * @throws BankruptcyException 
	 * @throws ImprisonedException 
	 */
	@Override
	public void ejecutarCasilla(int pId) throws BankruptcyException, ImprisonedException {
		super.ejecutarCasilla(pId);
		Jugador pJugador = ListaJugadores.getMiListaJugadores().buscarJugadorPorId(pId);
		
		if (ListaJugadores.getMiListaJugadores().estaComprada(this.getPosicion())) {
			if (!pJugador.getPropiedades().esta(this.getPosicion())) {
				if (!this.getHipotecado()) {
					this.alquilar(pId);
				} else {
					System.out.println("La Propiedad está hipotecada. No ocurre nada.");
				}
			} else {
				System.out.println("Posees la Propiedad. No ocurre nada.");
			}
		} else {
			System.out.println("Puedes comprar " + this.getNombre() + " por " + this.getPrecio() + " €.");
			System.out.println("¿La compras? Se subastará si eliges no comprarla. S/N");

			boolean instruccion = Instrucciones.recogerConfirmacion();
			
			if (instruccion) {
				pJugador.comprar(this.getPrecio(), this.getPosicion());
			} else {
				this.subastar();
			}
		}
	}

	/**
	 * Subasta la Propiedad entre los jugadores.
	 * @throws BankruptcyException
	 */
	private void subastar() throws BankruptcyException {
		System.out.println("Se va a subastar la propiedad " + this.getNombre() + '.');
		Jugador pujador = null;
		int puja = 40;
		System.out.println("La puja comienza en " + (puja + 10) + " €.");
		System.out.println("Cada vez que un jugador escriba su identificador pujará por la propiedad hasta que se escriba '0'.");
		int instruccion;
		instruccion = Instrucciones.recogerNumeroEntre(0, ListaJugadores.getMiListaJugadores().getTamanyo());
		while (instruccion != 0) {
			if (ListaJugadores.getMiListaJugadores().buscarJugadorPorId(instruccion).puedePagar(puja + 10)) {
				puja = puja + 10;
				pujador = ListaJugadores.getMiListaJugadores().buscarJugadorPorId(instruccion);
				
				System.out.println("El jugador " + pujador.getNombre() + " con identificador "
				+ pujador.getIdentificador() + " ha pujado " + puja + " €.");
				System.out.println("La puja siguiente es de " + (puja + 10) + " €.");
			} else {
				System.out.println("No puedes pagarlo.");
			}
			instruccion = Instrucciones.recogerNumeroEntre(0, ListaJugadores.getMiListaJugadores().getTamanyo());
		}

		if (pujador != null) {
			System.out.println("El jugador " + pujador.getNombre() + " ha comprado la propiedad " + this.getNombre() + " por " + puja + " €.");
			pujador.comprar(puja, this.getPosicion());
		} else {
			System.out.println("Nadie compra la propiedad.");
		}
	}

	/**
	 * Imprime la información de la Propiedad.
	 */
	public void imprimir() {
		System.out.println("	-La casilla del tipo " + this.getClass().toString() + " con los atributos:");
		System.out.println("		·Posicion: " + this.getPosicion());
		System.out.println("		·Nombre: " + this.getNombre());
		System.out.println("		·Precio: " + this.getPrecio());
		System.out.println("		·Alquiler: " + this.getAlquiler());
		System.out.println("		·Hipotecado: " + this.getHipotecado());
		System.out.println("		·Precio de la hipoteca: " + this.getPrecioHipotecar());
	}
}
