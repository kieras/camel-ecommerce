==========================================
PEDIDO PARA ENTREGA
Motoboys S/A
==========================================
Cliente: ${body.cliente}
------------------------------------------
Itens:
<#list body.itens as item>
	${item.quantidade} | ${item.nome} 
</#list>
------------------------------------------
(c) Arkhi Camel E-Commerce