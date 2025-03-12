package excecoes.quarto;

public class EstadoQuartoInvalidoException extends RuntimeException {
	
	public EstadoQuartoInvalidoException() {
		super("Coloque um estado válido");
	}

}
