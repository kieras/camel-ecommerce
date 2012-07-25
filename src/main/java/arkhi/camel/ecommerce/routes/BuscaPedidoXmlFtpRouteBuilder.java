package arkhi.camel.ecommerce.routes;


import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;

import arkhi.camel.ecommerce.domain.Duration;
import arkhi.camel.ecommerce.domain.Pedido;

public class BuscaPedidoXmlFtpRouteBuilder extends RouteBuilder {

	@Override
	public void configure() {

		from("ftp:localhost:2121/pedidos?delete=true&username=camel&password=ecommerce")
		.id("BuscaPedidoXmlFtpRoute")
		.process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				Duration.init();
			}
		})
		.convertBodyTo(Pedido.class)
		.to("jms:pedidos")
		.log("Pedido recebido: ${body.toString}");

	}

}
