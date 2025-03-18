package br.com.hotelaria.aplicacao;

import br.com.hotelaria.dao.ClienteDao;
import br.com.hotelaria.dao.DaoFactory;
import br.com.hotelaria.dao.QuartoDao;
import br.com.hotelaria.dao.ReservaDao;
import br.com.hotelaria.entidades.Quarto;

public class Program {
	public static void main(String[] args) {

		ClienteDao cliente = DaoFactory.createClienteDao();
		ReservaDao reserva = DaoFactory.createReservaDao();
		QuartoDao quarto = DaoFactory.createQuartoDao();
		
		Quarto quartoTeste = new Quarto(0, 1, null);
		
		try {
			quarto.adicionarQuarto(quartoTeste);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
