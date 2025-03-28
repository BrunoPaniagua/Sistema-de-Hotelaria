package br.com.hotelaria.excecoes.quarto;

public class QuartoCapacidadeInvalidaException extends RuntimeException {
	
	public QuartoCapacidadeInvalidaException() {
		super("Coloque uma quantidade válida");
	}

}
