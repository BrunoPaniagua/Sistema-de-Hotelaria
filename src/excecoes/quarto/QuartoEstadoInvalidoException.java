package excecoes.quarto;

public class QuartoEstadoInvalidoException extends RuntimeException {
	
	public QuartoEstadoInvalidoException() {
		super("Coloque um estado válido");
	}

}
