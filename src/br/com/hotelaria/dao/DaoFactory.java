package br.com.hotelaria.dao;

import br.com.hotelaria.banco.DB;
import br.com.hotelaria.dao.implementacao.ClienteDaoJdbc;
import br.com.hotelaria.dao.implementacao.QuartoDaoJdbc;
import br.com.hotelaria.dao.implementacao.ReservaDaoJdbc;

public class DaoFactory {

	public static QuartoDao createQuartoDao() {
		return new QuartoDaoJdbc(DB.getConnection());
	}
	
	public static ClienteDao createClienteDao() {
		return new ClienteDaoJdbc(DB.getConnection());
	}
	
	public static ReservaDao createReservaDao() {
		return new ReservaDaoJdbc(DB.getConnection());
	}
	
}
