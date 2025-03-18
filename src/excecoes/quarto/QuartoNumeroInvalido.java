package excecoes.quarto;

public class QuartoNumeroInvalido extends RuntimeException {
	
	public QuartoNumeroInvalido() {
		super("Digite um número válido");
	}

}
