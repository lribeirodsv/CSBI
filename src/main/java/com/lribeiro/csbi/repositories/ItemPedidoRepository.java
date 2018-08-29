package com.lribeiro.csbi.repositories;

import com.lribeiro.csbi.domain.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Camada de acesso a dados, somente com esta interface, e possivel realizar as operacoes de bancos de dados na classe Categoria
public interface ItemPedidoRepository extends JpaRepository <ItemPedido, Integer>{
	
}
