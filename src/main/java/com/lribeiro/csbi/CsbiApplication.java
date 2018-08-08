package com.lribeiro.csbi;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lribeiro.csbi.domain.Categoria;
import com.lribeiro.csbi.domain.Produto;
import com.lribeiro.csbi.repositories.CategoriaRepository;
import com.lribeiro.csbi.repositories.ProdutoRepository;

@SpringBootApplication
public class CsbiApplication implements CommandLineRunner {	               //CommandLineRunner é uma Interface que permite a implementação do método run

	@Autowired
	private CategoriaRepository repoCategoria;
	
	@Autowired
	private ProdutoRepository repoProduto;
	
	public static void main(String[] args) {
		SpringApplication.run(CsbiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {                   	//Executa algo quando a aplicação inicia

		Categoria cat1 = new Categoria(null, "Informática");				//Instancia um objeto categoria
		Categoria cat2 = new Categoria(null, "Escritório");					//Instancia um objeto categoria
				
		Produto prod1 = new Produto(null, "Computador", 2000.00);			//Instancia um objeto produto
		Produto prod2 = new Produto(null, "Impressora", 800.00);			//Instancia um objeto produto
		Produto prod3 = new Produto(null, "Mouse", 80.00);					//Instancia um objeto produto
		
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));		//Incluindo os produtos nas listas de produtos da categoria (relacionamento virtual) 
		cat2.getProdutos().addAll(Arrays.asList(prod2));					//Incluindo os produtos nas listas de produtos da categoria (relacionamento virtual)
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));					//Incluindo as categorias nas listas de categorias do produto (relacionamento virtual)
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));			//Incluindo as categorias nas listas de categorias do produto (relacionamento virtual)
		prod3.getCategorias().addAll(Arrays.asList(cat1));					//Incluindo as categorias nas listas de categorias do produto (relacionamento virtual)
				
		repoCategoria.saveAll(Arrays.asList(cat1, cat2)); 					//Salva as categorias no banco de dados
		repoProduto.saveAll(Arrays.asList(prod1, prod2, prod3)); 			//Salva os produtos no banco de dados
	}
}
