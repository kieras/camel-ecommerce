package arkhi.camel.ecommerce.processors;

import java.util.ArrayList;
import java.util.List;

import arkhi.camel.ecommerce.domain.Item;
import arkhi.camel.ecommerce.domain.Pedido;


public class PedidoSplitter {

	public List<Pedido> splitItens(Pedido pedidoCompleto) {

		List<Pedido> pedidosItem = new ArrayList<Pedido>();

		for (Item item : pedidoCompleto.getItens()) {
			Pedido p = new Pedido(pedidoCompleto.getCliente());
			p.setPedidoTwitter(pedidoCompleto.isPedidoTwitter());
			p.adicionarItem(item);
			pedidosItem.add(p);
		}

		return pedidosItem;
	}
}