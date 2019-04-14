package org.pmoo.packproyecto.casillas;

import org.pmoo.packproyecto.exceptions.BankruptcyException;
import org.pmoo.packproyecto.exceptions.DowngradeLimitException;
import org.pmoo.packproyecto.exceptions.UpgradeLimitException;
import org.pmoo.packproyecto.individuos.Banca;
import org.pmoo.packproyecto.individuos.Jugador;
import org.pmoo.packproyecto.listas.ListaJugadores;

public class Calle extends Propiedad {

	// Atributos
	private Color color;
	private int precioMejora;
	private int cantidadMejoras;

	// Constructora
	/**
	 * Crea un objeto de tipo Calle con false como hipotecado.
	 * @param pNombre : String - Nombre de la Calle.
	 * @param pPrecio : int - Precio de la Calle.
	 * @param pAlquiler : int - Alquiler de la Calle.
	 * @param pColor : Color - Color de la Calle.
	 * @param pPrecioMejora : int - PrecioMejora de la Calle.
	 * @param pPrecioHipotecar : int - PrecioHipotecar de la Calle.
	 */
	public Calle(String pNombre, int pPrecio, int pAlquiler, Color pColor, int pPrecioMejora, int pPrecioHipotecar) {
		super(pNombre, pPrecio, pAlquiler, pPrecioHipotecar);
		this.color = pColor;
		this.precioMejora = pPrecioMejora;
		this.cantidadMejoras = 0;
	}

	// Getters
	/**
	 * @return la cantidad de mejoras.
	 */
	public int getCantidadMejoras() {
		return this.cantidadMejoras;
	}

	/**
	 * @return el color.
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * @return el precio que cuesta una mejora.
	 */
	public int getPrecioMejora() {
		return this.precioMejora;
	}

	// Setters
	/**
	 * Atribuye pCantidadMejoras al atributo cantidadMejoras.
	 * @param pCantidadMejoras : int
	 */
	private void setCantidadMejoras(int pCantidadMejoras) {
		this.cantidadMejoras = pCantidadMejoras;
	}

	// Métodos
	/**
	 * Incrementa en 1 mejoras.
	 */
	private void anyadirMejora() {
		this.setCantidadMejoras(this.getCantidadMejoras() + 1);
	}

	/**
	 * Decrementa en 1 mejoras.
	 */
	private void eliminarMejora() {
		this.setCantidadMejoras(this.getCantidadMejoras() - 1);
	}

	/**
	 * Añade una mejora y hace pagar al jugador el precio si no hay ya 5 mejoras.
	 * @throws BankruptcyException 
	 */
	public void mejorar() throws BankruptcyException {
		try {
			if (this.cantidadMejoras == 5) {
				throw new UpgradeLimitException("No se pueden realizar más mejoras");
			} else {
				Jugador comprador = (Jugador) ListaJugadores.getMiListaJugadores().buscarComprador(this.getPosicion());
				
				this.anyadirMejora();
				comprador.pagarAJugador(this.getPrecioMejora(), Banca.getMiBanca().getIdentificador());
			}
		} catch (UpgradeLimitException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Remueve una mejora y hace que el jugador cobre el precio si hay alguna mejora.
	 */
	public void venderMejora() {
		try {
			if (this.cantidadMejoras == 0) {
				throw new DowngradeLimitException("No hay mejoras para vender");
			} else {
				Jugador comprador = (Jugador) ListaJugadores.getMiListaJugadores().buscarComprador(this.getPosicion());
				
				this.eliminarMejora();
				Banca.getMiBanca().pagarAJugador(this.getPrecioMejora(), comprador.getIdentificador());
			}
		} catch (DowngradeLimitException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void alquilar(int pId) throws BankruptcyException {
		Jugador alquilado = ListaJugadores.getMiListaJugadores().buscarJugadorPorId(pId),
				comprador = (Jugador) ListaJugadores.getMiListaJugadores().buscarComprador(this.getPosicion());
		int alquilerTotal = this.getAlquiler() * (int) Math.pow(2, this.getCantidadMejoras());
		
		alquilado.pagarAJugador(alquilerTotal, comprador.getIdentificador());
	}

	public void imprimir() {
		super.imprimir();
		System.out.println("		·Color: " + this.getColor());
		System.out.println("		·Mejoras: " + this.getCantidadMejoras());
		System.out.println("		·Precio por mejora: " + this.getPrecioMejora());
	}
}
