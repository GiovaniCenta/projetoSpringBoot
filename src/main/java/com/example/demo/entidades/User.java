package com.example.demo.entidades;
//Aqui vai a declaração da entidade usuário junto com os métodos getters e setters além do equals e hashCode para futuras comparações
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity //Converter objetos pro modelo relacional do DB
@Table(name="tb_user") //evitar conflitos entre o nome da tabela e a classe 
public class User implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id //Pra avisar que o ID do DB
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Como é uma chave que sera auto incrementada, tem que instanciar pro Spring
	private Integer id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	@JsonIgnore //serve para não ocorrer um Loop no jackson, que é quem faz a serialização e conexão das entidades
	//se você carregar um objeto muitos para um o objeto um vem automaticamente, o outro não ocorre, isso é o lazy loading
	@OneToMany(mappedBy="client") //faz a associação um usuario para muitos pedidos por meio do objeto client da classe order por meio de map
	private List<Order> orders = new ArrayList<>();
	

	
	public User(){};
	public User(Integer id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}
	public List<Order> getOrders() {
		return orders;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
