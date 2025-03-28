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
import br.com.hotelaria.entidades.Quarto;
import br.com.hotelaria.excecoes.banco.DbIntegrityException;
import br.com.hotelaria.excecoes.quarto.QuartoNaoExisteException;
import br.com.hotelaria.utilitarios.DbUtils;

public class DeletarQuartoTest {

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
	void deletarQuartoSucesso() throws SQLException {

		when(con.prepareStatement(anyString())).thenReturn(ps);
		when(ps.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(true);
		
		when(rs.getInt("numero")).thenReturn(1);
		when(rs.getInt("capacidade")).thenReturn(1);
		when(rs.getString("estado")).thenReturn("disponivel");
		
		doNothing().when(ps).setInt(anyInt(), anyInt());

		when(ps.executeUpdate()).thenReturn(1);

		assertDoesNotThrow(() -> quartoDao.deletarQuarto(anyInt()));
	}

	@Test
	void deletarQuartoNaoExisteException() throws SQLException {

		when(con.prepareStatement(anyString())).thenReturn(ps);
		when(ps.executeQuery()).thenReturn(rs);
		when(rs.next()).thenReturn(false);

		assertThrows(QuartoNaoExisteException.class, () -> quartoDao.deletarQuarto(anyInt()));

	}

	@Test
	void deletarQuartoDbIntegrityException() throws SQLException {

		when(con.prepareStatement(anyString())).thenThrow(new SQLException());
		assertThrows(DbIntegrityException.class, () -> quartoDao.deletarQuarto(1));

	}

}
