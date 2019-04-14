package org.pmoo.packproyecto.casillas;

import org.pmoo.packproyecto.exceptions.BankruptcyException;
import org.pmoo.packproyecto.individuos.Jugador;
import org.pmoo.packproyecto.listas.ListaJugadores;

public class Estacion extends Propiedad {

	// Constructora
	/**
	 * Crea un objeto de tipo Estacion con 200 como precio, 25 como alquiler, 100 como precioHipotecar y false como hipotecado.
	 * @param pNombre : String
	 */
	public Estacion(String pNombre) {
		super(pNombre, 200, 25, 100);
	}

	// Métodos
	@Override
	protected void alquilar(int pId) throws BankruptcyException {
		Jugador alquilado = ListaJugadores.getMiListaJugadores().buscarJugadorPorId(pId),
				comprador = (Jugador) ListaJugadores.getMiListaJugadores().buscarComprador(this.getPosicion());
		int alquilerTotal = (int)(this.getAlquiler() * Math.pow(2, comprador.cantidadDelMismoTipo(this) - 1));
		
		alquilado.pagarAJugador(alquilerTotal, comprador.getIdentificador());
	}
}
