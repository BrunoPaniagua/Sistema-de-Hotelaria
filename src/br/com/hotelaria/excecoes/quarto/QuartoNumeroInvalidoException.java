package br.com.hotelaria.excecoes.quarto;

public class QuartoNumeroInvalidoException extends RuntimeException {
	
	public QuartoNumeroInvalidoException() {
		super("Digite um número válido");
	}

}
