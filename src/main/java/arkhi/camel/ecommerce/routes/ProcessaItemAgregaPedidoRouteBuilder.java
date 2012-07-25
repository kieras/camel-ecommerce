package arkhi.camel.ecommerce.routes;

import static arkhi.camel.ecommerce.routes.PedidoSplitterRouteBuilder.HEADER_PEDIDO_CORRELATION_ID;
import static arkhi.camel.ecommerce.routes.PedidoSplitterRouteBuilder.HEADER_PEDIDO_SIZE;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.processor.aggregate.AggregationStrategy;

import arkhi.camel.ecommerce.domain.Duration;
import arkhi.camel.ecommerce.domain.Item;
import arkhi.camel.ecommerce.domain.Pedido;
import arkhi.camel.ecommerce.processors.ProcessadorArtesanal;
import arkhi.camel.ecommerce.processors.ProcessadorIndustrializado;

public class ProcessaItemAgregaPedidoRouteBuilder extends RouteBuilder {

	@Override
	public void configure() {
		
		/*
		 * Se desejar processar em paralelo (Competing Consumers EIP),
		 * utilize a opcao 'concurrentConsumers=30' no endpoint.
		 */
		from("jms:itens?concurrentConsumers=30")
		.id("ProcessaItemRoute")
			.choice()
				.when(simple("${body.itens[0].tipo} == 'ARTESANAL'"))
					.bean(ProcessadorArtesanal.class)
				.when(simple("${body.itens[0].tipo} == 'INDUSTRIALIZADO'"))
					.bean(ProcessadorIndustrializado.class)
			.end()
		.to("jms:itensProcessados")
		.log("Item processado: ${body.itens[0].toString}");
		
		
		from("jms:itensProcessados")
		.id("AgregaPedidoRoute")
			.aggregate(header(HEADER_PEDIDO_CORRELATION_ID), new PedidoAggregationStrategy())
				.completionSize(header(HEADER_PEDIDO_SIZE))
			.multicast().parallelProcessing()
				.to("jms:pedidosProntosEntrega", "jms:pedidosProntosBaixaEstoque")
		.process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				System.out.println("[[[[[" + Duration.get() + "]]]]]");
			}
		})
		.log("Pedido preparado e embalado para entrega: ${body.toString}");

	}
	
}


class PedidoAggregationStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if (oldExchange == null) {
			return newExchange;
		}
		
		Item novoItem = newExchange.getIn().getBody(Pedido.class).getItens().get(0);
		oldExchange.getIn().getBody(Pedido.class).adicionarItem(novoItem);
		
		return oldExchange;
	}

}

