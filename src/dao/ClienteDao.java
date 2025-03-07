package dao;

import java.util.List;

import entidades.Cliente;

public interface ClienteDao {
	
	void cadastrarCliente(Cliente cliente);
	List<Cliente> mostrarTodosClientes();
	void modificarDadosCliente();
	Cliente procurarClienteCpf();
	void deletarCliente();
	
}
