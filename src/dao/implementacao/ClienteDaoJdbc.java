package dao.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import banco.DB;
import banco.DbException;
import dao.ClienteDao;
import entidades.Cliente;
import utilitarios.DbUtils;

public class ClienteDaoJdbc implements ClienteDao {

	private Connection con;

	public ClienteDaoJdbc(Connection con) {
		this.con = con;
	}

	@Override
	public void cadastrarCliente(Cliente cliente) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("""
					INSERT INTO cliente(cpf, nome, telefone)
					VALUES(?, ?, ?)
					""");
			ps.setString(1, cliente.getCpf());
			ps.setString(2, cliente.getNome());
			ps.setString(3, cliente.getTelefone());
			
			DbUtils.checarAcao(ps.executeUpdate());
			
		} catch (SQLException e) {
			throw new DbException(e.getSQLState());
		} finally {
			DB.CloseStatement(ps);
		}

	}

	@Override
	public List<Cliente> mostrarTodosClientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void modificarDadosCliente() {
		// TODO Auto-generated method stub

	}

	@Override
	public Cliente procurarClienteCpf() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletarCliente() {
		// TODO Auto-generated method stub

	}

}
