package dao;

import java.time.LocalDateTime;
import java.util.List;

import entidades.Cliente;
import entidades.Quarto;
import entidades.Reserva;

public interface ReservaDao {
	
	void criarReserva(Cliente cliente, Quarto quarto, LocalDateTime entrada, LocalDateTime saida);
	void terminarReserva(int idReserva);
	List<Reserva> verReservas();
	Reserva procurarReserva(int idReserva);
}
