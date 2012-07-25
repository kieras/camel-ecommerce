package arkhi.camel.ecommerce.processors;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import arkhi.camel.ecommerce.domain.Item;
import arkhi.camel.ecommerce.domain.Pedido;

public class ProcessadorArtesanal {

	private static final Logger LOG = LoggerFactory.getLogger(ProcessadorArtesanal.class);

	public Pedido preparar(Pedido pedido) {
		try {
			Item item = pedido.getItens().get(0);
			LOG.info("Preparando produto artesanal... {}", item);
			item.preparar();
			Thread.sleep(1000);
			return pedido;
		} catch (InterruptedException e) {
			LOG.warn("An InterruptedException ocurred! Damn it!", e);
			return pedido;
		}

	}

}
