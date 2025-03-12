package aplicacao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import dao.ClienteDao;
import dao.DaoFactory;
import dao.QuartoDao;
import dao.ReservaDao;
import entidades.Cliente;
import entidades.EstadoQuarto;
import entidades.Quarto;
import entidades.Reserva;

public class Program {
	public static void main(String[] args) {

		ClienteDao cliente = DaoFactory.createClienteDao();
		ReservaDao reserva = DaoFactory.createReservaDao();
		QuartoDao quarto = DaoFactory.createQuartoDao();
		
		Quarto quartoTeste = new Quarto(4, 1, null);
		
		try {
			quarto.adicionarQuarto(quartoTeste);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
