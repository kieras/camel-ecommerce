package arkhi.camel.ecommerce.routes;


import org.apache.camel.builder.RouteBuilder;

import arkhi.camel.ecommerce.processors.PedidoSplitter;

public class PedidoSplitterRouteBuilder extends RouteBuilder {

	public static final String HEADER_PEDIDO_SIZE = "PedidoSize";
	
	public static final String HEADER_PEDIDO_CORRELATION_ID = "PedidoCorrelationId";

	@Override
	public void configure() {
		
		from("jms:pedidos")
		.id("PedidoSplitterRoute")
		.split().method(PedidoSplitter.class)
			.setHeader(HEADER_PEDIDO_CORRELATION_ID, property("CamelCorrelationId"))
			.setHeader(HEADER_PEDIDO_SIZE, property("CamelSplitSize"))
		.to("jms:itens")
		.log("Item enviado para preparacao: ${body.itens[0].toString}");

	}
	
}
