package dao;

import java.util.List;

import entidades.Quarto;

public interface QuartoDao {

	List<Quarto> MostrarQuartos();
	
	void ColocarManutencao(int numero);
	
	void TirarManutencao(int numero);
	
	void adicionarQuarto(Quarto quarto);
	
	void deletarQuarto(int numero);
}
