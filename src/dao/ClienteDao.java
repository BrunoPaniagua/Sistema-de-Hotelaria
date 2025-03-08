package dao;

import java.util.List;
import java.util.Scanner;

import entidades.Cliente;

public interface ClienteDao {
	
	void cadastrarCliente(Cliente cliente);
	
	List<Cliente> mostrarTodosClientes();
	
	void modificarDadosCliente(Scanner leitor,String cpf, int escolha);
	
	Cliente procurarClienteCpf(String cpf);
	
	void deletarCliente(String cpf);
	
}
