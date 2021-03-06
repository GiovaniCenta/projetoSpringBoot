package com.example.demo.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
//import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.example.demo.entidades.pk.OrderItem;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity //Converter objetos pro modelo relacional do DB
@Table(name="tb_product") //evitar conflitos entre o nome da tabela e a classe 
public class Product implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id //Pra avisar que o ID do DB
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Como é uma chave que sera auto incrementada, tem que instanciar pro Spring
	private Integer id;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	
	
	@ManyToMany //relação muitos pra muitos das tabelas
	@JoinTable(name = "tb_product_category", //nome da tabela juntada
	joinColumns = @JoinColumn(name = "product_id"), //nome das chaves que iram se relacionar
	inverseJoinColumns = @JoinColumn(name = "category_id")) //nome da chave do outro lado da associação
	private Set<Category> categories = new HashSet<>();
	
	@OneToMany(mappedBy="id.product")
	private Set<OrderItem> items = new HashSet<>();
	
	public Product() {}

	public Product(Integer id, String name, String description, Double price, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	
	@JsonIgnore
	public Set<Order> getOrders(){ //essa funcao percorre a coleção items que é orderitem para retornar cada elemento order associado
		Set<Order> set = new HashSet<>();
		for (OrderItem x:items) {
			set.add(x.getOrder());
		}
		return set;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	};
	
	
	
}
