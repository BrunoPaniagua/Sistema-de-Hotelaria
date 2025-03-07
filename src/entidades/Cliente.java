package entidades;

import java.util.Objects;

public class Cliente {

	private String cpf;
	private String nome;
	private String telefone;

	public Cliente() {

	}

	public Cliente(String cpf, String nome, String telefone) {
		this.cpf = cpf;
		this.nome = nome;
		this.telefone = telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(cpf, other.cpf);
	}

	@Override
	public String toString() {
		return nome +" Cpf: " + cpf + ", telefone: " + formatarTelefone();
	}
	
	private String formatarTelefone() {
		String pais = telefone.substring(0,2);
		String ddd = telefone.substring(2,4);
		String parte1 = telefone.substring(4,9);
		String parte2 = telefone.substring(9);
		return String.format("+%s (%s) %s-%s",pais, ddd, parte1, parte2);
	}
	

}
