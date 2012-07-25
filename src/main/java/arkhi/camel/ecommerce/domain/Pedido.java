package arkhi.camel.ecommerce.domain;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	@XmlAttribute
	private String cliente;

	@XmlAttribute
	private boolean pedidoTwitter;

	@XmlElementWrapper
	@XmlElement(name = "item")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Item> itens;

	public Pedido() {
		this.itens = new LinkedList<Item>();
	}

	public Pedido(String cliente) {
		super();
		this.cliente = cliente;
		this.pedidoTwitter = false;
		this.itens = new LinkedList<Item>();
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public List<Item> getItens() {
		return itens;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public boolean isPedidoTwitter() {
		return pedidoTwitter;
	}

	public void setPedidoTwitter(boolean pedidoTwitter) {
		this.pedidoTwitter = pedidoTwitter;
	}

	public boolean adicionarItem(Item item) {
		return itens.add(item);
	}

	public int getQuantidadeItens() {
		return itens.size();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pedido [cliente=").append(cliente)
				// .append(", twitter=").append(pedidoTwitter)
				.append(", quantidadeItens=").append(getQuantidadeItens())
				.append("]");
		return builder.toString();
	}

}
