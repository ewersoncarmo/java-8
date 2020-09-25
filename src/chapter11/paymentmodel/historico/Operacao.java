package chapter11.paymentmodel.historico;

public class Operacao {

	private String contrato;

	public Operacao(String contrato) {
		this.contrato = contrato;
	}

	public String getContrato() {
		return contrato;
	}

	@Override
	public String toString() {
		return "Operacao [contrato=" + contrato + "]";
	}
	
}
