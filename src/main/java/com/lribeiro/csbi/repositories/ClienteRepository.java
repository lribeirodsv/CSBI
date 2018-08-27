package com.lribeiro.csbi.repositories;

import com.lribeiro.csbi.domain.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Camada de acesso a dados, somente com esta interface, e possivel realizar as operacoes de bancos de dados na classe Cidade
public interface ClienteRepository extends JpaRepository <Cliente, Integer>{
	
}
