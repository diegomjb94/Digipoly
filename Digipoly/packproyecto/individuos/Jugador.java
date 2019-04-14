package org.pmoo.packproyecto.individuos;

import org.pmoo.packproyecto.auxiliares.Dado;
import org.pmoo.packproyecto.auxiliares.Instrucciones;
import org.pmoo.packproyecto.casillas.Calle;
import org.pmoo.packproyecto.casillas.Carcel;
import org.pmoo.packproyecto.casillas.Casilla;
import org.pmoo.packproyecto.casillas.Color;
import org.pmoo.packproyecto.casillas.Propiedad;
import org.pmoo.packproyecto.casillas.Salida;
import org.pmoo.packproyecto.exceptions.BankruptcyException;
import org.pmoo.packproyecto.exceptions.ImprisonedException;
import org.pmoo.packproyecto.exceptions.InvalidPositionException;
import org.pmoo.packproyecto.listas.ListaJugadores;
import org.pmoo.packproyecto.listas.Tablero;

public class Jugador extends Individuo {

	// Atributos
	protected static int nuevoId = 1;
	protected int posicion;
	protected int turnosJugados;
	protected int turnosEnCarcel;
	protected int cartaSalirDeCarcel;
	protected boolean enBancarrota;

	// Constructora
	/**
	 * Crea un objeto de tipo Jugador con un id único, 2500 como dinero, 1 como posicion, una ListaPropiedades vacia, 0 como turnosJugados,
	 * 0 como turnosEnCarcel, 0 como cartaSalirDeCarcel y false como enBancarrota.
	 * @param pNombre : String
	 */
	public Jugador(String pNombre) {
		super(getNuevoId(), pNombre, 2500);
		setNuevoId(getNuevoId() + 1);
		this.posicion = 1;
		this.turnosJugados = 0;
		this.turnosEnCarcel = 0;
		this.cartaSalirDeCarcel = 0;
		this.enBancarrota = false;
	}

	// Getters
	public static int getNuevoId() {
		return nuevoId;
	}

	/**
	 * @return la posicion.
	 */
	public int getPosicion() {
		return this.posicion;
	}

	/**
	 * @return el número de turnosJugados.
	 */
	public int getTurnosJugados() {
		return this.turnosJugados;
	}

	/**
	 * @return el número de turnosEnCarcel.
	 */
	public int getTurnosEnCarcel() {
		return this.turnosEnCarcel;
	}

	/**
	 * @return el número de cartaSalirDeCarcel.
	 */
	public int getCartaSalirDeCarcel() {
		return this.cartaSalirDeCarcel;
	}

	/**
	 * @return si es enBancarrota.
	 */
	public boolean getEnBancarrota() {
		return this.enBancarrota;
	}

	// Setters
	public static void setNuevoId(int pNuevoId) {
		nuevoId = pNuevoId;
	}

	/**
	 * Asigna a posicion, pPosicion.
	 * @param pPosicion : int
	 */
	public void setPosicion(int pPosicion) {
		this.posicion = pPosicion;
	}

	/**
	 * Asigna a turnosJugados, pTurnosJugados.
	 * @param pTurnosJugados : int
	 */
	private void setTurnosJugados(int pTurnosJugados) {
		this.turnosJugados = pTurnosJugados;
	}

	/**
	 * Asigna a turnosEnCarcel, pTurnosEnCarcel.
	 * @param pTurnosEnCarcel : int
	 */
	private void setTurnosEnCarcel(int pTurnosEnCarcel) {
		this.turnosEnCarcel = pTurnosEnCarcel;
	}

	/**
	 * Asigna a cartaSalirDeCarcel, pCartaSalirDeCarcel.
	 * @param pCartaSalirDeCarcel : int
	 */
	private void setCartaSalirDeCarcel(int pCartaSalirDeCarcel) {
		this.cartaSalirDeCarcel = pCartaSalirDeCarcel;
	}

	/**
	 * Asigna a enBancarrota, pEnBancarrota.
	 * @param pEnBancarrota : boolean
	 */
	private void setEnBancarrota(boolean pEnBancarrota) {
		this.enBancarrota = pEnBancarrota;
	}

	// Métodos
	/**
	 * Incrementa en 1 cartaSalirDeCarcel y presenta por pantalla la cantidad de cartas que posee.
	 */
	public void anyadirCartaSalirCarcel() {
		this.setCartaSalirDeCarcel(this.getCartaSalirDeCarcel() + 1);
		System.out.println(this.getNombre() + " tiene " + this.getCartaSalirDeCarcel() + " cartas para salir de la carcel.");
	}

	/**
	 * @param pCantidad : int - Cantidad a pagar.
	 * @return si tiene suficiente dinero como para pagar pCantidad.
	 */
	public boolean puedePagar(int pCantidad) {
		return this.getDinero() >= pCantidad;
	}

	/**
	 * Después de pagar al Individuo con pId como Id comprueba si el dinero es negativo. Si es el caso, declara al jugador en bancarrota.
	 * @param pCantidad : int - Cantidad a pagar.
	 * @param pId : int - Id del Individuo que cobra.
	 * @throws BankruptcyException 
	 */
	public void pagarAJugador(int pCantidad, int pId) throws BankruptcyException {
		Individuo pIndividuo;
		if (pId == Banca.getMiBanca().getIdentificador()) {
			pIndividuo = Banca.getMiBanca();
		} else {
			pIndividuo = ListaJugadores.getMiListaJugadores().buscarJugadorPorId(pId);
		}
		
		this.pagar(pCantidad);
		pIndividuo.cobrar(pCantidad);
		if (this.getDinero() < 0) {
			this.declararEnBancarrota(pIndividuo.getIdentificador());
		}
	}

	/**
	 * Si puede pagar la cantidad y pPosicion es la posicion de una Propiedad, incluye esta Propiedad en las propiedades de este jugador y
	 * la borra de la de su dueño, presenta por pantalla lo ocurrido y acto seguido paga al dueño pCantidad.
	 * @param pCantidad : int - Cantidad por la que se compra la Propiedad.
	 * @param pPosicion : int - Posicion de la Propiedad a comprar.
	 * @throws BankruptcyException 
	 */
	public void comprar(int pCantidad, int pPosicion) throws BankruptcyException {
		try {
			if (Tablero.getMiTablero().buscarPorPosicion(pPosicion) instanceof Propiedad) {
				if (this.puedePagar(pCantidad)) {
					if (!(Tablero.getMiTablero().buscarPorPosicion(pPosicion) instanceof Calle) || ((Calle) Tablero.getMiTablero().buscarPorPosicion(pPosicion)).getCantidadMejoras() == 0) {
						Individuo duenyo = ListaJugadores.getMiListaJugadores().buscarComprador(pPosicion);
						
						duenyo.getPropiedades().cambiarDuenyo(pPosicion, this.getIdentificador());
						System.out.println(this.getNombre() + " a comprado a " + duenyo.getNombre() + " la casilla " + Tablero.getMiTablero().buscarPorPosicion(pPosicion).getNombre() + '.');
						this.pagarAJugador(pCantidad, duenyo.getIdentificador());
					} else {
						System.out.println("No puedes comprar Calles mejoradas.");
					}
				} else {
					System.out.println("No puedes permitirtelo");
				}
			} else {
				throw new InvalidPositionException("La posición no se corresponde a una Propiedad. No se puede comprar.");
			}
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * @param pColor : Color - Color del que se comprueba si tiene monopolio.
	 * @return la comparación entre el número de Calles de pColor que posee el jugador y total.
	 */
	private boolean tieneMonopolio(Color pColor) {
		return (this.getPropiedades().cuantasDelMismoColor(pColor) == Tablero.getMiTablero().cuantasDelMismoColor(pColor));
	}

	/**
	 * Si pPosicion es la posicion de una Calle del jugador, este tiene monopolio del color de la calle y puede pagar el precioMejora de la Calle llama
	 * a mejorar de la Calle.
	 * @param pPosicion : int - Posicion de la Propiedad a mejorar.
	 * @throws BankruptcyException 
	 */
	public void mejorar(int pPosicion) throws BankruptcyException {
		Casilla pCasilla = Tablero.getMiTablero().buscarPorPosicion(pPosicion);
		try {
			if (pCasilla instanceof Calle) {
				if (ListaJugadores.getMiListaJugadores().buscarComprador(pPosicion).getIdentificador() == this.getIdentificador()
						&& this.tieneMonopolio(((Calle) pCasilla).getColor()) && this.puedePagar(((Calle) pCasilla).getPrecioMejora())) {
					((Calle) pCasilla).mejorar();
				}
			} else {
				throw new InvalidPositionException("La posición no se corresponde a una Calle. No se puede mejorar.");
			}
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Si pPosicion es la posicion de una Calle del jugador llama a venderMejora de la Calle.
	 * @param pPosicion : int - Posicion de la Propiedad a vender mejora.
	 */
	public void venderMejora(int pPosicion) {
		Casilla pCasilla = Tablero.getMiTablero().buscarPorPosicion(pPosicion);
		try {
			if (pCasilla instanceof Calle) {
				if (ListaJugadores.getMiListaJugadores().buscarComprador(pPosicion).getIdentificador() == this.getIdentificador()) {
					((Calle) pCasilla).venderMejora();
				}
			} else {
				throw new InvalidPositionException("La posición no se corresponde a una Calle. No se puede vender una mejora.");
			}
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Si pPosicion es la posicion de una Propiedad del jugador llama a hipotecar de la Propiedad.
	 * @param pPosicion : int - Posicion de la Propiedad a hipotecar.
	 */
	public void hipotecar(int pPosicion) {
		Casilla pCasilla = Tablero.getMiTablero().buscarPorPosicion(pPosicion);
		try {
			if (pCasilla instanceof Propiedad) {
				if (ListaJugadores.getMiListaJugadores().buscarComprador(pPosicion).getIdentificador() == this.getIdentificador()) {
					if (!(pCasilla instanceof Calle) || ((Calle) pCasilla).getCantidadMejoras() == 0) {
						((Propiedad) pCasilla).hipotecar();
					} else {
						System.out.println("No puedes hipotecarlo si tiene mejoras.");
					}
				}
			} else {
				throw new InvalidPositionException("La posición no se corresponde a una Propiedad. No se puede hipotecar.");
			}
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Si pPosicion es la posicion de una Propiedad del jugador y puede pagar el precio de la Calle llama a deshipotecar de la Propiedad.
	 * @param pPosicion : int
	 * @throws BankruptcyException 
	 */
	public void deshipotecar(int pPosicion) throws BankruptcyException {
		Casilla pCasilla = Tablero.getMiTablero().buscarPorPosicion(pPosicion);
		try {
			if (pCasilla instanceof Propiedad) {
				if (ListaJugadores.getMiListaJugadores().buscarComprador(pPosicion).getIdentificador() == this.getIdentificador()) {
					((Propiedad) pCasilla).deshipotecar();
				}
			} else {
				throw new InvalidPositionException("La posición no se corresponde a una Propiedad. No se puede deshipotecar.");
			}
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Asigna a posicion pPosicion y si es un valor mayor al tamaño del tablero le resta ese tamaño y le hace cobrar el salario presentandolo por pantalla.
	 * Acto seguido ejecuta la casilla en la que se encuentra.
	 * @param pPosicion - Nueva posible posicion del Jugador.
	 * @throws ImprisonedException 
	 * @throws BankruptcyException 
	 */
	public void desplazarA(int pPosicion) throws ImprisonedException, BankruptcyException {
		this.setPosicion(pPosicion);
		while (this.getPosicion() > Tablero.getMiTablero().getTamanyo()) {
			this.setPosicion(this.getPosicion() - Tablero.getMiTablero().getTamanyo());
			Banca.getMiBanca().pagarAJugador(Salida.getMiSalida().getSalario(), this.getIdentificador());
			System.out.println("Al pasar por la casilla de Salida cobras el salario.");
		}
		this.ejecutarCasilla();
	}

	/**
	 * Tira 2 dados, aumenta posicion en el valor de esos dos dados (si supera el tamaño del tablero se corrige, la Banca le paga el salario y lo
	 * presenta por pantalla) y ejecuta la nueva casilla en la que se encuentra. Esto lo repite hasta que el valor de los 2 dados sea diferente
	 * o si es la 3ª vez que el valor de los dados es igual, en cuyo caso envía al jugador a la carcel. Si en algún momento el jugador es encarcelado
	 * se termina el método.
	 * @throws BankruptcyException 
	 */
	public void mover() throws BankruptcyException {
		try {
			int tirada1 = Dado.getMiDado().tirarDado();
			int tirada2 = Dado.getMiDado().tirarDado();
			int dobles = 0;
			while (tirada1 == tirada2 && dobles < 2) {
				this.desplazarA(this.getPosicion() + tirada1 + tirada2);
				dobles ++;
				tirada1 = Dado.getMiDado().tirarDado();
				tirada2 = Dado.getMiDado().tirarDado();
			}

			if (tirada1 == tirada2) {
				this.enviarALaCarcel();
			} else {
				this.desplazarA(this.getPosicion() + tirada1 + tirada2);
			}
		} catch (ImprisonedException e) {
			System.out.println(e.getMessage()); // Se detiene todo proceso relacionado con el movimiento si el jugador es enviado a la carcel.
		}
	}

	/**
	 * @param pPropiedad
	 * @return el número de objetos de la misma clase que pPropiedad entre las propiedades del jugador.
	 */
	public int cantidadDelMismoTipo(Propiedad pPropiedad) {
		return this.getPropiedades().cantidadDelMismoTipo(pPropiedad);
	}

	/**
	 * Le asigna a posicion la posicion de la Carcel, a turnosEnCarcel 3 y lo presenta por pantalla.
	 * @throws ImprisonedException 
	 */
	public void enviarALaCarcel() throws ImprisonedException {
		this.setPosicion(Carcel.getMiCarcel().getPosicion());
		this.setTurnosEnCarcel(3);
		throw new ImprisonedException(this.getNombre() + " ha sido encarcelado.");
	}

	/**
	 * Sale de la carcel y paga la fianza.
	 * @throws BankruptcyException 
	 */
	public void pagarFianza() throws BankruptcyException {
		System.out.println("El jugador " + this.getNombre() + " paga la fianza.");
		this.salirCarcel();
		this.pagarAJugador(Carcel.getMiCarcel().getFianza(), Banca.getMiBanca().getIdentificador());
	}

	/**
	 * Tira 2 dados, si son iguales sale de la carcel y se desplaza con la suma de los 2 dados (no vuelve a tirar), si no son iguales pasa el
	 * turno en la carcel.
	 * @throws ImprisonedException 
	 * @throws BankruptcyException 
	 */
	public void intentarSalirCarcel() throws ImprisonedException, BankruptcyException {
		int tirada1 = Dado.getMiDado().tirarDado();
		int tirada2 = Dado.getMiDado().tirarDado();
		if (tirada1 == tirada2) {
			this.salirCarcel();
			this.desplazarA(this.getPosicion() + tirada1 + tirada2);
		}
		else {
			this.pasarTurnoEnCarcel();
		}
	}

	/**
	 * Decrementa en 1 turnosEnCarcel. Acto seguido, si los turnosEnCarcel son 0 le obliga a pagar la fianza.
	 * @throws BankruptcyException 
	 */
	private void pasarTurnoEnCarcel() throws BankruptcyException {
		this.setTurnosEnCarcel(this.getTurnosEnCarcel() - 1);
		if (this.getTurnosEnCarcel() == 0) {
			this.pagarFianza();
		} else {
			System.out.println("Has pasado el turno en la carcel. Todavía debes esperar " + this.getTurnosEnCarcel() + " turnos.");
		}
	}

	/**
	 * Reduce en 1 cartaSalirCarcel y sale de la carcel.
	 */
	public void utilizarCartaSalirCarcel() {
		this.setCartaSalirDeCarcel(this.getCartaSalirDeCarcel() - 1);
		System.out.println("Usas una carta de Salir Carcel. Te quedan " + this.getCartaSalirDeCarcel() + '.');
		this.salirCarcel();
	}

	/**
	 * Asigna a turnosEnCarcel 0 y lo presenta por pantalla.
	 */
	private void salirCarcel() {
		this.setTurnosEnCarcel(0);
		System.out.println(this.getNombre() + " ha cumplido su condena.");
	}

	/**
	 * Asigna a enBancarrota false, le da a pJugador sus cartas para salir de la carcel y todas sus propiedades, y lo presenta por pantalla.
	 * @param pId : int - Es el identificador de un jugador o la banca.
	 * @throws BankruptcyException 
	 */
	private void declararEnBancarrota(int pId) throws BankruptcyException {
		Jugador pJugador = ListaJugadores.getMiListaJugadores().buscarJugadorPorId(pId);
		
		this.setEnBancarrota(true);
		pJugador.setCartaSalirDeCarcel(pJugador.getCartaSalirDeCarcel() + this.getCartaSalirDeCarcel());
		this.setCartaSalirDeCarcel(0);
		this.getPropiedades().cambiarDuenyoTodas(pId);
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println(this.getNombre() + " ha sido declarado en bancarrota en su turno número" + (this.getTurnosJugados() + 1) +
				" por el jugador " + pJugador.getNombre());
		System.out.println("--------------------------------------------------------------------------------");
		throw new BankruptcyException();
	}

	/**
	 * Ejecuta la casilla en la que se encuentra el Jugador.
	 * @throws ImprisonedException 
	 * @throws BankruptcyException 
	 */
	public void ejecutarCasilla() throws ImprisonedException, BankruptcyException {
		Casilla pCasilla = Tablero.getMiTablero().buscarPorPosicion(this.getPosicion());
		
		pCasilla.ejecutarCasilla(this.getIdentificador());
	}
	/**
	 * Si está encarcelado llama al ejecutarCasilla de Carcel, si no llama a mover. Después, este o no encarcelado le ofrece al jugador la
	 * posibilidad de comprar, mejorar, vender una mejora, hipotecar o deshipotecar algo (y realiza los procesos respectivos) una y otra vez
	 * hasta que no quiera hacer nada más. Entonces incrementa en uno turnosJugados.
	 * @throws ImprisonedException 
	 */
	public void ejecutarTurno() {
		System.out.println("////////////////////////////////////////////////////////////////////////////////");
		System.out.println("			Turno del jugador " + this.getNombre());
		System.out.println("////////////////////////////////////////////////////////////////////////////////");
		try {
			if (this.getTurnosEnCarcel() > 0) {
				try {
					Carcel.getMiCarcel().ejecutarCasilla(this.getIdentificador());
				} catch (ImprisonedException e) {} // El ejecutarCasilla de Carcel nunca lanza ImprisonedException.
			} else {
				this.mover();
			}
			
			int instruccion;
			
			do {
				System.out.println("Las operaciones que puedes realizar son las siguientes:");
				System.out.println("	1.- Puedes comprar una propiedad.");
				System.out.println("	2.- Puedes hipotecar una propiedad.");
				System.out.println("	3.- Puedes deshipotecar una mejora.");
				System.out.println("	4.- Puedes mejorar una propiedad.");
				System.out.println("	5.- Puedes vender una mejora de una propiedad.");
				System.out.println("Escribe el número de la opcion que quieres realizar o 0 si no quieres hacer nada.");
				
				instruccion = Instrucciones.recogerNumeroEntre(0, 5);
				if (instruccion != 0) {
					System.out.println("Escribe el número de la casilla en la que quieres hacerlo.");
					int posicion = Instrucciones.recogerPosicion();
					
					if (Tablero.getMiTablero().buscarPorPosicion(posicion) instanceof Propiedad) {
						switch (instruccion) {
						case 1:
							if (ListaJugadores.getMiListaJugadores().buscarComprador(posicion).getIdentificador() != Banca.getMiBanca().getIdentificador()) {
								System.out.println("Introduce ahora la cantidad por la que vas a comprar dicha posicion.");
								int cantidad = Instrucciones.recogerNumeroEntre(0, this.getDinero());
								this.comprar(cantidad, posicion);
							} else {
								System.out.println("No puedes comprarle directamente a la Banca.");
							}
							break;
						case 2:
							this.hipotecar(posicion);
							break;
						case 3:
							this.deshipotecar(posicion);
							break;
						case 4:
							this.mejorar(posicion);
							break;
						case 5:
							this.venderMejora(posicion);
							break;
						}
					} else {
						System.out.println("Esa posición no se corresponde a una Propiedad.");
					}
				}
			} while (instruccion != 0);
			
			this.finTurno();
			
		} catch (BankruptcyException e) {} // Se detiene inmediantamente el turno de un jugador al ser declarado en bancarrota.
	}

	/**
	 * Incrementa en 1 turnosJugados.
	 */
	private void finTurno() {
		this.setTurnosJugados(this.getTurnosJugados() + 1);
		System.out.println("Posees:");
		System.out.println("	-Dinero: " + this.getDinero());
		System.out.println("	-Posicion: " + this.getPosicion());
		System.out.println("	-Cartas de salir de la carcel: " + this.getCartaSalirDeCarcel());
		this.getPropiedades().imprimir();
	}
}