package arkhi.camel.ecommerce.processors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import arkhi.camel.ecommerce.domain.Item;
import arkhi.camel.ecommerce.domain.Pedido;

public class ProcessadorIndustrializado {

	private static final Logger LOG = LoggerFactory.getLogger(ProcessadorIndustrializado.class);

	public Pedido preparar(Pedido pedido) {
		try {
			Item item = pedido.getItens().get(0);
			LOG.info("Preparando produto industrializado... {}", item);
			item.preparar();
			Thread.sleep(250);
			return pedido;
		} catch (InterruptedException e) {
			LOG.warn("An InterruptedException ocurred! Damn it!", e);
			return pedido;
		}

	}

}
