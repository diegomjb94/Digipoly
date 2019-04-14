package org.pmoo.packproyecto.listas;

import org.pmoo.packproyecto.cartas.CobrarDinero;
import org.pmoo.packproyecto.cartas.DesplazarAPosicion;
import org.pmoo.packproyecto.cartas.DesplazarAdelante;
import org.pmoo.packproyecto.cartas.DesplazarAtras;
import org.pmoo.packproyecto.cartas.EnviarALaCarcel;
import org.pmoo.packproyecto.cartas.PagarDinero;
import org.pmoo.packproyecto.cartas.SalirCarcel;
import org.pmoo.packproyecto.casillas.Salida;

public class CajaDeComunidad extends ListaCartas {

	// Atributos
	private static CajaDeComunidad miCajaDeComunidad = new CajaDeComunidad();

	// Constructora
	/**
	 * Crea un objeto de tipo CajaDeComunidad.
	 */
	private CajaDeComunidad() {
		super();
	}

	// Getters
	/**
	 * @return la única Caja de Comunidad.
	 */
	public static CajaDeComunidad getMiCajaDeComunidad() {
		return miCajaDeComunidad;
	}

	// Métodos
	/**
	 * Inicializa la Caja de Comunidad.
	 */
	public void inicializar() {
		this.anyadirCarta(new CobrarDinero("Recibes 30 € como premio en un concurso de deletreo.", 30));
		this.anyadirCarta(new CobrarDinero("Recibes 50 € por error de la banca a tu favor.", 50));
		this.anyadirCarta(new CobrarDinero("Recibes 50 € por un error administrativo.", 50));
		this.anyadirCarta(new CobrarDinero("Recibes 75 € en una rifa.", 75));
		this.anyadirCarta(new CobrarDinero("Recibes 100 € por ser tu cumpleaños.", 100));
		this.anyadirCarta(new CobrarDinero("Te han concedido la beca. Recibes 150 €.", 150));
		this.anyadirCarta(new CobrarDinero("Recibes 200 € gracias a las acciones inbertidas en la banca.", 200));
		this.anyadirCarta(new CobrarDinero("Te ha tocado la lotería. Recibes 300 €.", 300));
		
		this.anyadirCarta(new PagarDinero("Pagas 5 € por un decimo de loteria.", 5));
		this.anyadirCarta(new PagarDinero("Pagas 30 €, por el servicio de empadronamiento.", 30));
		this.anyadirCarta(new PagarDinero("Pagas 50 € al servicio de recogida de basuras.", 50));
		this.anyadirCarta(new PagarDinero("Pagas 75 € por un regalo para tu pareja.", 75));
		this.anyadirCarta(new PagarDinero("Pagas 100 € por las cuotas escolares.", 100));
		this.anyadirCarta(new PagarDinero("Pagas 150 € por gastos de hospitalizacion.", 150));
		this.anyadirCarta(new PagarDinero("Pagas 150 € por un error administrativo.", 150));
		this.anyadirCarta(new PagarDinero("Exceso de velocidad. Multa de 200 €.", 200));
		
		this.anyadirCarta(new SalirCarcel("Con esta carta puedes salir de la carcel."));
		this.anyadirCarta(new SalirCarcel("Con esta carta puedes salir de la carcel."));
		
		this.anyadirCarta(new EnviarALaCarcel("Vas a la carcel"));
		
		this.anyadirCarta(new DesplazarAPosicion("Date una vuelta por el Paseo del Prado. Avanza al Paseo del Prado.", 40));
		this.anyadirCarta(new DesplazarAPosicion("Avanza a Salida.", Salida.getMiSalida().getPosicion()));
		this.anyadirCarta(new DesplazarAPosicion("Avanza a Estacion de Goya, si pasas por 'Salida' obten " + Salida.getMiSalida().getSalario() + " €.", 6));
		this.anyadirCarta(new DesplazarAPosicion("Avanza a Estacion de las Delicias, si pasas por 'Salida' obten " + Salida.getMiSalida().getSalario() + " €.", 16));
		this.anyadirCarta(new DesplazarAPosicion("Avanza a Estacion del Mediodia, si pasas por 'Salida' obten " + Salida.getMiSalida().getSalario() + " €.", 26));
		this.anyadirCarta(new DesplazarAPosicion("Avanza a Estacion del Norte, si pasas por 'Salida' obten " + Salida.getMiSalida().getSalario() + " €.", 36));
		
		this.anyadirCarta(new DesplazarAdelante("Avanza 3 casillas", 3));
		this.anyadirCarta(new DesplazarAdelante("Avanza 5 casillas", 5));
		this.anyadirCarta(new DesplazarAdelante("Avanza 10 casillas", 10));
		
		this.anyadirCarta(new DesplazarAtras("Retrocede 2 casillas", 2));
		this.anyadirCarta(new DesplazarAtras("Retrocede 4 casillas", 4));
		this.anyadirCarta(new DesplazarAtras("Retrocede 9 casillas", 9));
	}
}