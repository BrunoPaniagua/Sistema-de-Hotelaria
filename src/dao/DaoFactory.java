package dao;

import banco.DB;
import dao.implementacao.QuartoDaoJdbc;

public class DaoFactory {

	public static QuartoDao createQuartoDao() {
		return new QuartoDaoJdbc(DB.getConnection());
	}
}
