package utilitarios;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import entidades.Cliente;
import entidades.EstadoQuarto;
import entidades.Quarto;
import entidades.Reserva;

public class DbUtils {
	
	public static void checarAcao(int rows) {
		if (rows == 0) {
			System.out.println("Algo deu errado");
		} else {
			System.out.println("Ação feita com sucesso");
		}
	}
	
	public static Cliente instanciarCliente(ResultSet rs) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setCpf(rs.getString("cpf"));
		cliente.setNome(rs.getString("nome"));
		cliente.setTelefone(rs.getString("telefone"));
		return cliente;
	}
	
	public static Quarto instanciarQuarto(ResultSet rs) throws SQLException {
		Quarto quarto = new Quarto();
		quarto.setNumero(rs.getInt("numero"));
		quarto.setCapacidade(rs.getInt("capacidade"));
		quarto.setEstado(EstadoQuarto.valueOf(rs.getString("estado").toUpperCase()));
		return quarto;
	}
	
	public static Reserva instanciarReserva(ResultSet rs, Cliente cliente, Quarto quarto ) throws SQLException {
		Reserva reserva = new Reserva();
		reserva.setIdReserva(rs.getInt("id_reserva"));
		reserva.setEntrada(rs.getTimestamp("entrada").toLocalDateTime());
		reserva.setSaida(rs.getTimestamp("saida").toLocalDateTime());
		reserva.setCliente(cliente);
		reserva.setQuarto(quarto);
		return reserva;
	}
	
}
