package br.com.hotelaria.excecoes.quarto;

import br.com.hotelaria.entidades.EstadoQuarto;

public class QuartoEmManutencaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private EstadoQuarto estado;
	
	public QuartoEmManutencaoException(String msg) {
		super(msg);
	}

}
