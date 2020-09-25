package chapter11.paymentmodel.historico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cliente {

	private Integer tipoCliente;
	private String nome;
	private List<Operacao> operacoes;

	public Cliente(Integer tipoCliente, String nome) {
		this.tipoCliente = tipoCliente;
		this.nome = nome;
		this.operacoes = new ArrayList<>();
	}

	public Integer getTipoCliente() {
		return tipoCliente;
	}

	public String getNome() {
		return nome;
	}
	
	public List<Operacao> getOperacoes() {
		return Collections.unmodifiableList(operacoes);
	}

	public void setOperacoes(List<Operacao> operacoes) {
		this.operacoes = operacoes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tipoCliente == null) ? 0 : tipoCliente.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Cliente [tipoCliente=" + tipoCliente + ", nome=" + nome + ", operacoes=" + operacoes + "]";
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
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (tipoCliente == null) {
			if (other.tipoCliente != null)
				return false;
		} else if (!tipoCliente.equals(other.tipoCliente))
			return false;
		return true;
	}
	
}
