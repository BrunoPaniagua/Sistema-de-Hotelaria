package dao;

import banco.DB;
import dao.implementacao.ClienteDaoJdbc;
import dao.implementacao.QuartoDaoJdbc;
import dao.implementacao.ReservaDaoJdbc;

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
