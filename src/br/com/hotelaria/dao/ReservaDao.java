package br.com.hotelaria.dao;

import java.time.LocalDateTime;
import java.util.List;

import br.com.hotelaria.entidades.Cliente;
import br.com.hotelaria.entidades.Quarto;
import br.com.hotelaria.entidades.Reserva;

public interface ReservaDao {
	
	void criarReserva(Cliente cliente, Quarto quarto, LocalDateTime entrada, LocalDateTime saida);
	void terminarReserva(int idReserva);
	List<Reserva> verReservas();
	Reserva procurarReserva(int idReserva);
}
