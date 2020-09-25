package chapter11.paymentmodel.historico;

public class Historico {

	private Integer tipoCliente;
	private String nomeCliente;
	private String contrato;

	public Historico(Integer tipoCliente, String nomeCliente, String contrato) {
		this.tipoCliente = tipoCliente;
		this.nomeCliente = nomeCliente;
		this.contrato = contrato;
	}

	public Integer getTipoCliente() {
		return tipoCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public String getContrato() {
		return contrato;
	}
	
}
