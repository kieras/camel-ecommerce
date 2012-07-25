package arkhi.camel.ecommerce.routes;

import org.apache.camel.builder.RouteBuilder;

public class ProcessaPedidoProntoRouteBuilder extends RouteBuilder {

	@Override
	public void configure() {
		
		from("jms:pedidosProntosEntrega")
		.id("DespachaPedidoParaEntregaRoute")
		.to("freemarker:arkhi/camel/ecommerce/pedido_ecommerce.ftl")
		.to("file:data/entregas/?fileName=entrega-$simple{file:name.noext}-$simple{date:now:yyyyMMddHHmmssSSS}.txt")
		.log("Pedido enviado para entrega: ${body.toString}");

		from("jms:pedidosProntosBaixaEstoque")
		.id("RealizaBaixaPedidoEstoqueRoute")
		.to("jpa:arkhi.camel.ecommerce.domain.Pedido")
		.log("Baixa do pedido realizada no estoque: ${body.toString}");

		
	}

}
