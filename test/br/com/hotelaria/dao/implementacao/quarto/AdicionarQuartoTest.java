package br.com.hotelaria.dao.implementacao.quarto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.hotelaria.dao.QuartoDao;
import br.com.hotelaria.dao.implementacao.QuartoDaoJdbc;
import br.com.hotelaria.entidades.EstadoQuarto;
import br.com.hotelaria.entidades.Quarto;
import br.com.hotelaria.excecoes.banco.DbException;
import br.com.hotelaria.excecoes.quarto.QuartoCapacidadeInvalidaException;
import br.com.hotelaria.excecoes.quarto.QuartoEstadoInvalidoException;
import br.com.hotelaria.excecoes.quarto.QuartoJaExistenteException;
import br.com.hotelaria.excecoes.quarto.QuartoNumeroInvalidoException;

public class AdicionarQuartoTest {

	@Mock
	private Connection con;

	@Mock
	private PreparedStatement ps;

	@Mock
	private ResultSet rs;

	private QuartoDao quartoDao;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		quartoDao = new QuartoDaoJdbc(con);
	}

	@Test
	void adicionarQuartoComSucesso() throws SQLException {

		Quarto quarto = new Quarto(1, 1, EstadoQuarto.DISPONIVEL);

		when(con.prepareStatement(anyString())).thenReturn(ps);
		when(ps.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(false);

		
		doNothing().when(ps).setInt(anyInt(), anyInt());
		doNothing().when(ps).setString(anyInt(), anyString());
		when(ps.executeUpdate()).thenReturn(1);

		assertDoesNotThrow(() -> quartoDao.adicionarQuarto(quarto));
	}

	@Test
	void adicionarQuartoJaExiste() throws SQLException {

		Quarto quarto = new Quarto(1, 2, EstadoQuarto.DISPONIVEL);

		when(con.prepareStatement(anyString())).thenReturn(ps);
		when(ps.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(true);
		
		when(rs.getInt("numero")).thenReturn(1);
		when(rs.getInt("capacidade")).thenReturn(2);
		when(rs.getString("estado")).thenReturn("disponivel");

		assertThrows(QuartoJaExistenteException.class, () -> quartoDao.adicionarQuarto(quarto));
	}

	@Test
	void adicionarQuartoSQLExceptionTry01() throws SQLException {
		Quarto quarto = new Quarto(1, 2, EstadoQuarto.DISPONIVEL);
		
		when(con.prepareStatement(anyString())).thenThrow(new SQLException());
		assertThrows(DbException.class, () -> quartoDao.adicionarQuarto(quarto));
	}

	@Test
	void adicionarQuartoSQLExceptionTry02() throws SQLException {
		Quarto quarto = new Quarto(1, 2, EstadoQuarto.DISPONIVEL);

		when(con.prepareStatement(anyString())).thenReturn(ps).thenThrow(new SQLException());

		when(ps.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(false);

		assertThrows(DbException.class, () -> quartoDao.adicionarQuarto(quarto));
	}

	@Test
	void adicionarQuartoNumeroInvalidoZero() {
		Quarto quarto = new Quarto(0, 1, EstadoQuarto.DISPONIVEL);
		assertThrows(QuartoNumeroInvalidoException.class, () -> quartoDao.adicionarQuarto(quarto));
	}

	@Test
	void adicionarQuartoCapacidadeInvalida() {
		Quarto quarto = new Quarto(1, 0, EstadoQuarto.DISPONIVEL);
		assertThrows(QuartoCapacidadeInvalidaException.class, () -> quartoDao.adicionarQuarto(quarto));
	}

	@Test
	void adicionarQuartoEstadoInvalido() {
		Quarto quarto = new Quarto(1, 1, null);
		assertThrows(QuartoEstadoInvalidoException.class, () -> quartoDao.adicionarQuarto(quarto));
	}
}
