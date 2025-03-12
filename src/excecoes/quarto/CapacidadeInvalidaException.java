package excecoes.quarto;

public class CapacidadeInvalidaException extends RuntimeException {
	
	public CapacidadeInvalidaException() {
		super("Coloque uma quantidade válida");
	}

}
