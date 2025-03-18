package br.com.hotelaria.dao;

import java.util.List;
import java.util.Scanner;

import br.com.hotelaria.entidades.Cliente;

public interface ClienteDao {
	
	void cadastrarCliente(Cliente cliente);
	
	List<Cliente> mostrarTodosClientes();
	
	void modificarDadosCliente(Scanner leitor,String cpf, int escolha);
	
	Cliente procurarClienteCpf(String cpf);
	
	void deletarCliente(String cpf);
	
}
