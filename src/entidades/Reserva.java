package entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Reserva {

	private int idReserva;
	private LocalDateTime entrada;
	private LocalDateTime saida;

	private Cliente cliente;
	private Quarto quarto;

	public Reserva() {

	}

	public Reserva(int idReserva, LocalDateTime entrada, LocalDateTime saida, Cliente cliente, Quarto quarto) {
		this.idReserva = idReserva;
		this.entrada = entrada;
		this.saida = saida;
		this.cliente = cliente;
		this.quarto = quarto;
	}

	public int getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}

	public LocalDateTime getEntrada() {
		return entrada;
	}

	public void setEntrada(LocalDateTime entrada) {
		this.entrada = entrada;
	}

	public LocalDateTime getSaida() {
		return saida;
	}

	public void setSaida(LocalDateTime saida) {
		this.saida = saida;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idReserva);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return idReserva == other.idReserva;
	}
	
	
	
	@Override
	public String toString() {
		return "Reserva " + idReserva + ", entrada=" + entrada + ", saida=" + saida + ", cliente=" + cliente.getNome()
				+ ", quarto=" + quarto.getNumero();
	}
	
	

}
