package dao.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.ReservaDao;
import entidades.Cliente;
import entidades.Quarto;
import entidades.Reserva;
import excecoes.banco.DbException;
import excecoes.banco.DbIntegrityException;
import utilitarios.DbUtils;

public class ReservaDaoJdbc implements ReservaDao {

	private Connection con;

	public ReservaDaoJdbc(Connection con) {
		this.con = con;
	}

	@Override
	public void criarReserva(Cliente cliente, Quarto quarto, LocalDateTime entrada, LocalDateTime saida) {

		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement("""
					INSERT INTO reserva (entrada, saida, cliente_cpf, quarto_numero)
					VALUES
					(?, ?, ?, ?);
					""");
			ps.setObject(1, entrada);
			ps.setObject(2, saida);
			ps.setString(3, cliente.getCpf());
			ps.setInt(4, quarto.getNumero());

			DbUtils.checarAcao(ps.executeUpdate());

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public void terminarReserva(int idReserva) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("""
					DELETE FROM reserva
					WHERE id_reserva = ?
					""");
			ps.setInt(1, idReserva);
			
			DbUtils.checarAcao(ps.executeUpdate());
			
		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		}
	}

	@Override
	public List<Reserva> verReservas() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Reserva> reservas = new ArrayList<Reserva>();
		Map<Integer, Quarto> mapQuarto = new HashMap<>();
		Map<String, Cliente> mapCliente = new HashMap<>();

		try {
			ps = con.prepareStatement("""
					SELECT *
					FROM quarto q
					INNER JOIN reserva r ON r.quarto_numero = q.numero
					INNER JOIN cliente c ON c.cpf = r.cliente_cpf;
															""");
			rs = ps.executeQuery();

			while (rs.next()) {

				Quarto quarto = mapQuarto.get(rs.getInt("numero"));
				Cliente cliente = mapCliente.get(rs.getString("cpf"));

				if (quarto == null) {
					quarto = DbUtils.instanciarQuarto(rs);
				}

				if (cliente == null) {
					cliente = DbUtils.instanciarCliente(rs);
				}

				Reserva reserva = DbUtils.instanciarReserva(rs, cliente, quarto);
				reservas.add(reserva);

			}

			return reservas;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			banco.DB.CloseResultSet(rs);
			banco.DB.CloseStatement(ps);
		}

	}

	@Override
	public Reserva procurarReserva(int idReserva) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Reserva reserva = null;
		try {
			ps = con.prepareStatement("""
					SELECT *
					FROM quarto q
					INNER JOIN reserva r ON r.quarto_numero = q.numero
					INNER JOIN cliente c ON c.cpf = r.cliente_cpf
					WHERE id_reserva = ?;
															""");
			ps.setInt(1, idReserva);
			rs = ps.executeQuery();
			if (rs.next()) {
				Quarto quarto = DbUtils.instanciarQuarto(rs);
				Cliente cliente = DbUtils.instanciarCliente(rs);
				reserva = DbUtils.instanciarReserva(rs, cliente, quarto);
			}

			return reserva;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
	}
}
