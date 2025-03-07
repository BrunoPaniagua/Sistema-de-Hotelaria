package utilitarios;

public class DbUtils {
	
	public static void checarAcao(int rows) {
		if (rows == 0) {
			System.out.println("Algo deu errado");
		} else {
			System.out.println("Ação feita com sucesso");
		}
	}
}
