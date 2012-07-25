package arkhi.camel.ecommerce.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum TipoItem {
		ARTESANAL, INDUSTRIALIZADO;
	}

	@Id
	@GeneratedValue
	private Integer id;

	@Enumerated(EnumType.STRING)
	private TipoItem tipo;

	private Integer quantidade;

	private String nome;

	private boolean preparado;

	public Item() {
	}

	public Item(TipoItem tipo, Integer quantidade, String nome) {
		super();
		this.tipo = tipo;
		this.quantidade = quantidade;
		this.nome = nome;
	}

	public TipoItem getTipo() {
		return tipo;
	}

	public void setTipo(TipoItem tipo) {
		this.tipo = tipo;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isPreparado() {
		return preparado;
	}

	public void setPreparado(boolean preparado) {
		this.preparado = preparado;
	}

	public void preparar() {
		preparado = true;
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
		builder.append("Item [tipo=").append(tipo).append(", quantidade=")
				.append(quantidade).append(", nome=").append(nome)
				.append(", preparado=").append(preparado).append("]");
		return builder.toString();
	}

}
