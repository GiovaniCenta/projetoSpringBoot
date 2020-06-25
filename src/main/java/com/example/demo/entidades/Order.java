package com.example.demo.entidades;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="tb_order") //evitar conflitos entre o nome da tabela e a classe order
public class Order implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id //Pra avisar que o ID do DB
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Como é uma chave que sera auto incrementada, tem que instanciar pro Spring
	private Integer id;
	
	
	@JsonFormat(shape= JsonFormat.Shape.STRING,pattern="yyyy-MM-DD'T'HH:mm:ss'Z'",timezone="GMT") //arrumar o formato da data
	private Instant moment;
	//private OrderStatus OrderStatus
	
	//aqui se faz a associacoa entre as duas entidades, como sao muitos pedidos para um usuario
	@ManyToOne //aqui se usa manyToOne
	@JoinColumn(name = "client_id") //a chave estrangeira que conecta as duas é client_id
	private User client;
	
	public Order() {}

	public Order(Integer id, Instant moment, User client) {
		super();
		this.id = id;
		this.moment = moment;
		this.client = client;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	};
	
	
	
}
