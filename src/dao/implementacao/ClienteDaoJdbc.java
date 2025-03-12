package dao.implementacao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import banco.DB;
import dao.ClienteDao;
import entidades.Cliente;
import excecoes.banco.DbException;
import excecoes.banco.DbIntegrityException;
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
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			ps = con.prepareStatement("SELECT * FROM cliente");
			rs = ps.executeQuery();
			while (rs.next()) {
				clientes.add(DbUtils.instanciarCliente(rs));
			}
			return clientes;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.CloseResultSet(rs);
			DB.CloseStatement(ps);
		}
	}

	@Override
	public void modificarDadosCliente(Scanner leitor, String cpf, int escolha) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			Cliente cliente = procurarClienteCpf(cpf);

			if (cliente == null) {
				return;
			} else {

				switch (escolha) {
				case 1: {
					// mudar nome
					System.out.println("Digite o novo nome");
					String nomeNovo = leitor.nextLine();
					ps = con.prepareStatement("""
							UPDATE cliente
							SET nome = ?
							WHERE cpf = ?;
							""");
					ps.setString(1, nomeNovo);
					ps.setString(2, cpf);

					DbUtils.checarAcao(ps.executeUpdate());

					break;
				}
				case 2: {
					// mudar telefone
					System.out.println("Digite o novo telefone");
					String nomeTelefone = leitor.nextLine();
					ps = con.prepareStatement("""
							UPDATE cliente
							SET telefone = ?
							WHERE cpf = ?;
							""");
					ps.setString(1, nomeTelefone);
					ps.setString(2, cpf);

					DbUtils.checarAcao(ps.executeUpdate());
					break;
				}
				default:
					System.out.println("Dgite uma opcao válida");
				}

			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public Cliente procurarClienteCpf(String cpf) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement("""
					SELECT *
					FROM cliente
					WHERE cpf = ?;
										""");
			ps.setString(1, cpf);
			rs = ps.executeQuery();
			if (rs.next()) {
				return DbUtils.instanciarCliente(rs);
			} else {
				System.out.println("Não possui um cliente com esse CPF");
				return null;
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.CloseResultSet(rs);
			DB.CloseStatement(ps);
		}
	}

	@Override
	public void deletarCliente(String cpf) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("""
					DELETE FROM cliente
					WHERE cpf = ?;
					""");
			ps.setString(1, cpf);
			DbUtils.checarAcao(ps.executeUpdate());

		} catch (SQLException e) {
			throw new DbIntegrityException(e.getMessage());
		} finally {
			DB.CloseStatement(ps);
		}

	}

}
