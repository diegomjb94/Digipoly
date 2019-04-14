package org.pmoo.packproyecto.casillas;

import org.pmoo.packproyecto.auxiliares.Dado;
import org.pmoo.packproyecto.exceptions.BankruptcyException;
import org.pmoo.packproyecto.individuos.Jugador;
import org.pmoo.packproyecto.listas.ListaJugadores;

public class ServicioPublico extends Propiedad {

	// Constructora
	/**
	 * Crea un objeto de tipo ServicioPublico con 150 como precio, 0 como alquiler y false como hipotecado.
	 * @param pNombre : String - Nombre del ServicioPublico.
	 */
	public ServicioPublico(String pNombre) {
		super(pNombre, 150, 0, 75);
	}

	// Métodos
	/**
	 * @return la suma de 2 número aleatorios entre 1 y 6.
	 */
	private int alquilerBase() {
		return Dado.getMiDado().tirarDado() + Dado.getMiDado().tirarDado();
	}

	/**
	 * El jugador paga la cantidad correspondiente.
	 * @param pId : int - Id del jugador en el ServicioPublico.
	 * @throws BankruptcyException 
	 */
	@Override
	protected void alquilar(int pId) throws BankruptcyException {
		Jugador alquilado = ListaJugadores.getMiListaJugadores().buscarJugadorPorId(pId),
				comprador = (Jugador) ListaJugadores.getMiListaJugadores().buscarComprador(this.getPosicion());
		int alquilerTotal = (int)(this.alquilerBase() * Math.pow(3, comprador.cantidadDelMismoTipo(this)));
		
		alquilado.pagarAJugador(alquilerTotal, comprador.getIdentificador());
	}
}
