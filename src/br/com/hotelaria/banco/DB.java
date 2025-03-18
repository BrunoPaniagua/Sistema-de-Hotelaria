package br.com.hotelaria.banco;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import br.com.hotelaria.excecoes.banco.DbException;


public class DB {
	private static Connection con = null;

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			props.load(fs);
			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}

	public static Connection getConnection() {
		if (con == null) {
			try {
				Properties props = loadProperties();
				String user = props.getProperty("user");
				String password = props.getProperty("password");
				String dburl = props.getProperty("dburl");

				con = DriverManager.getConnection(dburl, user, password);
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
		return con;
	}

	public static void CloseConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void CloseStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	public static void CloseResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}
}
