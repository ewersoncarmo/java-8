package chapter11.paymentmodel.historico;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Client {

	public static void main(String[] args) {
		List<Historico> historicos = Arrays.asList(
				new Historico(1, "cliente 1", "123"),
				new Historico(1, "cliente 1", "456"),
				new Historico(1, "cliente 1", "222"),
				//
				new Historico(2, "cliente 1", "999"),
				//
				new Historico(2, "cliente 2", "789"),
				new Historico(2, "cliente 2", "000"));
		
		Map<Cliente, List<Operacao>> map = historicos.stream()
			.collect(Collectors.groupingBy(t -> new Cliente(t.getTipoCliente(), t.getNomeCliente()),
					Collectors.mapping(t -> new Operacao(t.getContrato()), Collectors.toList())));

		List<Cliente> list = map.entrySet().stream()
			.map(t -> {
				Cliente key = t.getKey();
				
				Cliente cliente = new Cliente(key.getTipoCliente(), key.getNome());
				cliente.setOperacoes(t.getValue());
				
				return cliente;
			})
			.collect(Collectors.toList());
		
		map.entrySet().forEach(System.out::println);
		list.forEach(System.out::println);
	}
}
