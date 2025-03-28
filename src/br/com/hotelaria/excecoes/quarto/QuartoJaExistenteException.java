package br.com.hotelaria.excecoes.quarto;

public class QuartoJaExistenteException extends RuntimeException {
	public QuartoJaExistenteException() {
		super("Quarto já existe");
	}

}
