package excecoes.quarto;

import entidades.EstadoQuarto;

public class QuartoEmManutencaoException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private EstadoQuarto estado;
	
	public QuartoEmManutencaoException(String msg) {
		super(msg);
	}

}
