package br.com.hotelaria.excecoes.quarto;

public class QuartoNaoExisteException extends RuntimeException {
	
	public QuartoNaoExisteException() {
		super("Esse quarto não existe");
	}
	
}
