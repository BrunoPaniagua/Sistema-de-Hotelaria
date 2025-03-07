package dao.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import banco.DbException;
import dao.QuartoDao;
import entidades.EstadoQuarto;
import entidades.Quarto;
import utilitarios.DbUtils;

public class QuartoDaoJdbc implements QuartoDao {

	private Connection con;

	public QuartoDaoJdbc(Connection con) {
		this.con = con;
	}

	private Quarto instanciarQuarto(ResultSet rs) throws SQLException {
		Quarto quarto = new Quarto();
		quarto.setNumero(rs.getInt("numero"));
		quarto.setCapacidade(rs.getInt("capacidade"));
		quarto.setEstado(EstadoQuarto.valueOf(rs.getString("estado").toUpperCase()));
		return quarto;
	}

	@Override
	public List<Quarto> MostrarQuartos() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Quarto> quartos = new ArrayList<Quarto>();

		try {
			ps = con.prepareStatement("SELECT * FROM quarto;");
			rs = ps.executeQuery();

			while (rs.next()) {
				quartos.add(instanciarQuarto(rs));
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			banco.DB.CloseStatement(ps);
			banco.DB.CloseResultSet(rs);
		}

		return quartos;
	}

	@Override
	public void ColocarManutencao(int numero) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("""
					UPDATE quarto
					SET estado = 'manutencao'
					WHERE numero = ?;
										""");
			ps.setInt(1, numero);

			DbUtils.checarAcao(ps.executeUpdate());

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public void TirarManutencao(int numero) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("""
					UPDATE quarto
					SET estado = 'disponivel'
					WHERE numero = ?;
					""");
			ps.setInt(1, numero);
			
			DbUtils.checarAcao(ps.executeUpdate());
			
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

}
