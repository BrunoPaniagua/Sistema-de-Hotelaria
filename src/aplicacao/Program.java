package aplicacao;

import java.util.List;
import java.util.Scanner;

import dao.ClienteDao;
import dao.DaoFactory;
import dao.QuartoDao;
import entidades.Cliente;
import entidades.Quarto;

public class Program {
	public static void main(String[] args) {
		
		/*
		 * QuartoDao quartoDao = DaoFactory.createQuartoDao();
		 * 
		 * System.out.println("--- Alterar para disponivel ---");
		 * quartoDao.TirarManutencao(2);
		 * 
		 * System.out.println("--- Mostrar quartos ---"); List<Quarto> quartos =
		 * quartoDao.MostrarQuartos(); for (Quarto quarto : quartos) {
		 * System.out.println(quarto); }
		 * 
		 * System.out.println("--- Alterar para manutencao ---");
		 * quartoDao.ColocarManutencao(2);
		 * 
		 * System.out.println("--- Mostrar quartos ---"); quartos =
		 * quartoDao.MostrarQuartos(); for (Quarto quarto : quartos) {
		 * System.out.println(quarto); }
		 */
		
		ClienteDao clienteDao = DaoFactory.createClienteDao();
		
		/*
		System.out.println("--- Adicionar Cliente ---");
		clienteDao.cadastrarCliente(new Cliente("10987654321", "Fernando", "5519123456789"));
		*/
		
		System.out.println("--- Mostar todos os clientes ---");
		List<Cliente> clientes = clienteDao.mostrarTodosClientes();
		clientes.forEach(System.out::println);
		
		System.out.println(clienteDao.procurarClienteCpf("10987654321"));
		
		System.out.println("--------------------");
		Scanner leitor = new Scanner(System.in);
		clienteDao.modificarDadosCliente(leitor, "10987654321", 2);
	}
}
