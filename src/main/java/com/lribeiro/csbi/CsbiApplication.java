package com.lribeiro.csbi;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lribeiro.csbi.domain.Categoria;
import com.lribeiro.csbi.domain.Cidade;
import com.lribeiro.csbi.domain.Estado;
import com.lribeiro.csbi.domain.Produto;
import com.lribeiro.csbi.repositories.CategoriaRepository;
import com.lribeiro.csbi.repositories.CidadeRepository;
import com.lribeiro.csbi.repositories.EstadoRepository;
import com.lribeiro.csbi.repositories.ProdutoRepository;

@SpringBootApplication
public class CsbiApplication implements CommandLineRunner {	               //CommandLineRunner é uma Interface que permite a implementação do método run

	@Autowired
	private CategoriaRepository repoCategoria;
	
	@Autowired
	private ProdutoRepository repoProduto;
	
	@Autowired
	private EstadoRepository repoEstado;
	
	@Autowired
	private CidadeRepository repoCidade;
	
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
		
		Estado est1 = new Estado(null, "Minas Gerais");						//Instancia um objeto estado
		Estado est2 = new Estado(null, "São Paulo");						//Instancia um objeto estado
		
		Cidade cid1 = new Cidade(null, "Uberlândia", est1);					//Instancia um objeto cidade
		Cidade cid2 = new Cidade(null, "São Paulo", est2);					//Instancia um objeto cidade
		Cidade cid3 = new Cidade(null, "Campinas", est2);					//Instancia um objeto cidade
		
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));		//Incluindo os produtos nas listas de produtos da categoria (relacionamento virtual) 
		cat2.getProdutos().addAll(Arrays.asList(prod2));					//Incluindo os produtos nas listas de produtos da categoria (relacionamento virtual)
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));					//Incluindo as categorias nas listas de categorias do produto (relacionamento virtual)
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));			//Incluindo as categorias nas listas de categorias do produto (relacionamento virtual)
		prod3.getCategorias().addAll(Arrays.asList(cat1));					//Incluindo as categorias nas listas de categorias do produto (relacionamento virtual)
		
		est1.getCidades().addAll(Arrays.asList(cid1));						//Incluindo as cidades nas listas de cidades do estado (relacionamento virtual)
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));				//Incluindo as cidades nas listas de cidades do estado (relacionamento virtual)
				
		repoCategoria.saveAll(Arrays.asList(cat1, cat2)); 					//Salva as categorias no banco de dados
		repoProduto.saveAll(Arrays.asList(prod1, prod2, prod3)); 			//Salva os produtos no banco de dados
		repoEstado.saveAll(Arrays.asList(est1, est2));						//Salva os estados no banco de dados (os estados tem que vir primeiro devido a integridade referencial)
		repoCidade.saveAll(Arrays.asList(cid1, cid2, cid3)); 				//Salva as cidades no banco de dados		
	}
}
