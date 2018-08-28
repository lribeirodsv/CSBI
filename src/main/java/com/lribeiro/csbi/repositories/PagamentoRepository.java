package com.lribeiro.csbi.repositories;

import com.lribeiro.csbi.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //Camada de acesso a dados, somente com esta interface, e possivel realizar as operacoes de bancos de dados na classe Categoria
public interface PagamentoRepository extends JpaRepository <Pagamento, Integer>{
	
}
