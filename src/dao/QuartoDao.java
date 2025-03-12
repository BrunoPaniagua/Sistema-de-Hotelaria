package dao;

import java.sql.SQLException;
import java.util.List;

import entidades.Quarto;

public interface QuartoDao {

	List<Quarto> mostrarQuartos();
	
	void colocarManutencao(int numero);
	
	void tirarManutencao(int numero);
	
	void adicionarQuarto(Quarto quarto);
	
	void deletarQuarto(int numero);
	
	Quarto procurarQuarto(int numero) throws SQLException ;
}
