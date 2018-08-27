package com.lribeiro.csbi.repositories;

import com.lribeiro.csbi.domain.Endereco;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Camada de acesso a dados, somente com esta interface, e possivel realizar as operacoes de bancos de dados na classe Cidade
public interface EnderecoRepository extends JpaRepository <Endereco, Integer>{
	
}
