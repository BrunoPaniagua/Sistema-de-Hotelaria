package br.com.hotelaria.aplicacao;

import java.util.List;

import br.com.hotelaria.dao.ClienteDao;
import br.com.hotelaria.dao.DaoFactory;
import br.com.hotelaria.dao.QuartoDao;
import br.com.hotelaria.dao.ReservaDao;
import br.com.hotelaria.entidades.EstadoQuarto;
import br.com.hotelaria.entidades.Quarto;

public class Program {
	public static void main(String[] args) {

		try {
			
			QuartoDao quartoDao = DaoFactory.createQuartoDao();
			
			quartoDao.deletarQuarto(-1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
}
