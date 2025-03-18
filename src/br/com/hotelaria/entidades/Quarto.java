package br.com.hotelaria.entidades;

import java.util.Objects;

public class Quarto {

	private int numero;
	private int capacidade;
	private EstadoQuarto estado;

	public Quarto() {

	}

	public Quarto(int numero, int capacidade, EstadoQuarto estado) {
		this.numero = numero;
		this.capacidade = capacidade;
		this.estado = estado;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public EstadoQuarto getEstado() {
		return estado;
	}

	public void setEstado(EstadoQuarto estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(numero);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quarto other = (Quarto) obj;
		return numero == other.numero;
	}

	@Override
	public String toString() {
		return "Quarto: " + numero + ", capacidade = " + capacidade + ", estado = " + estado;
	}

}
