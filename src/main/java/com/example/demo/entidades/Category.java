package com.example.demo.entidades;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tb_category")
public class Category implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id //Pra avisar que o ID do DB
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Como é uma chave que sera auto incrementada, tem que instanciar pro Sprin
	private Integer id;
	private String name;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "categories")//quer dizer que sera mapeado pela coleção categories da classe produtos
	private Set<Product> products = new HashSet<>();
	
	
	public Category() {}

	public Category(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Set<Product> getProducts() {
		return products;
	}

	
	
	

}
