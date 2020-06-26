//repositorio responsavel por fazer operações com a entidade User
//é uma interface pois JpaRepository também é Interface
//Não precisa fazer implementação dessa interface pois o spring.data.jpa ja fez a implementação padrão
package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entidades.Product;

@Repository //é um component mais especifico, assim ficando mais semanticamente especifico, nesse caso é só para entender melhor o código, não sendo necessário pois o JPARepository ja faz isso
public interface ProductRepository extends JpaRepository<Product,Integer>{  //vai extender o repositorio do Banco de dados passando o tipo da classe e o tipo do primary key
	
	}
