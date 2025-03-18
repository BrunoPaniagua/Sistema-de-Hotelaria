package dao.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import banco.DB;
import dao.QuartoDao;
import entidades.EstadoQuarto;
import entidades.Quarto;
import excecoes.banco.DbException;
import excecoes.banco.DbIntegrityException;
import excecoes.quarto.CapacidadeInvalidaException;
import excecoes.quarto.QuartoEstadoInvalidoException;
import excecoes.quarto.QuartoEmManutencaoException;
import excecoes.quarto.QuartoJaExistenteException;
import excecoes.quarto.QuartoNaoExisteException;
import excecoes.quarto.QuartoNumeroInvalido;
import utilitarios.DbUtils;

public class QuartoDaoJdbc implements QuartoDao {

	private Connection con;

	public QuartoDaoJdbc(Connection con) {
		this.con = con;
	}

	@Override
	public List<Quarto> mostrarQuartos() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Quarto> quartos = new ArrayList<Quarto>();

		try {
			ps = con.prepareStatement("SELECT * FROM quarto;");
			rs = ps.executeQuery();

			while (rs.next()) {
				quartos.add(DbUtils.instanciarQuarto(rs));
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
	public void colocarManutencao(int numero) {

		PreparedStatement ps = null;
		try {
			Quarto quarto = procurarQuarto(numero);
			if (quarto.getEstado() == EstadoQuarto.MANUTENCAO) {
				throw new QuartoEmManutencaoException("Quarto já está em manutenção");
			} else {
				ps = con.prepareStatement("""
						UPDATE quarto
						SET estado = 'manutencao'
						WHERE numero = ?;
											""");
				ps.setInt(1, numero);
				ps.executeUpdate();
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} catch (QuartoNaoExisteException e) {
			System.out.println(e.getMessage());
		} finally {
			DB.CloseStatement(ps);
		}

	}

	@Override
	public void tirarManutencao(int numero) {
		PreparedStatement ps = null;
		try {
			Quarto quarto = procurarQuarto(numero);
			if (quarto.getEstado() != EstadoQuarto.MANUTENCAO) {
				throw new QuartoEmManutencaoException("Ação indisponível, pois o quarto não está em manutenção");
			} else {
				ps = con.prepareStatement("""
						UPDATE quarto
						SET estado = 'disponivel'
						WHERE numero = ?;
						""");
				ps.setInt(1, numero);

				ps.executeUpdate();
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} catch (QuartoNaoExisteException e) {
			System.out.println(e.getMessage());
		} finally {
			DB.CloseStatement(ps);
		}
	}

	@Override
	public void adicionarQuarto(Quarto quarto) {
		try {

			if(quarto.getNumero()<=0) {
				throw new QuartoNumeroInvalido();
			}
			
			if (quarto.getCapacidade() <= 0) {
				throw new CapacidadeInvalidaException();
			}

			if (quarto.getEstado() == null) {
				throw new QuartoEstadoInvalidoException();
			}
			
			if (procurarQuarto(quarto.getNumero()) != null) {
				throw new QuartoJaExistenteException();
			}

		} catch (SQLException e) {
			throw new DbException(e.getSQLState());
		} catch (QuartoNaoExisteException e) {
			
			PreparedStatement ps = null;
			try {
				ps = con.prepareStatement("""
						INSERT INTO quarto(numero, capacidade, estado)
						VALUES(?, ?, ?)
						""");
				ps.setInt(1, quarto.getNumero());
				ps.setInt(2, quarto.getCapacidade());
				ps.setString(3, quarto.getEstado().name().toLowerCase());

				DbUtils.checarAcao(ps.executeUpdate());
			} catch (SQLException e2) {
				throw new DbException(e.getMessage());
			} finally {
				DB.CloseStatement(ps);
			}
			
		}

	}

	@Override
	public void deletarQuarto(int numero) {
		PreparedStatement ps = null;
		try {

			Quarto quarto = procurarQuarto(numero);

			if (quarto == null) {
				throw new QuartoNaoExisteException();
			}

			ps = con.prepareStatement("""
					DELETE FROM quarto
					WHERE numero = ?;
					""");
			ps.setInt(1, numero);
			DbUtils.checarAcao(ps.executeUpdate());

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} catch (QuartoNaoExisteException e) {
			System.out.println(e.getMessage());
		} finally {
			DB.CloseStatement(ps);
		}

	}

	@Override
	public Quarto procurarQuarto(int numero) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			ps = con.prepareStatement("""
					SELECT *
					FROM quarto
					WHERE numero = ?;
										""");
			ps.setInt(1, numero);
			rs = ps.executeQuery();
			if (rs.next()) {
				return DbUtils.instanciarQuarto(rs);
			} else {
				throw new QuartoNaoExisteException();
			}

		} catch (SQLException e) {
			throw new SQLException();
		} finally {
			DB.CloseResultSet(rs);
			DB.CloseStatement(ps);
		}
	}

}
