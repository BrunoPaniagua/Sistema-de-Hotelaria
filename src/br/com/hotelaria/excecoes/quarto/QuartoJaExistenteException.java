package br.com.hotelaria.excecoes.quarto;

public class QuartoJaExistenteException extends RuntimeException {
	
	public QuartoJaExistenteException() {
		super("Já existe um quarto com esse número");
	}

}
