package com.lribeiro.csbi;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lribeiro.csbi.domain.Categoria;
import com.lribeiro.csbi.repositories.CategoriaRepository;

@SpringBootApplication
public class CsbiApplication implements CommandLineRunner {	               //CommandLineRunner é uma Interface que permite a implementação do método run

	@Autowired
	private CategoriaRepository repoCategoria;
	
	public static void main(String[] args) {
		SpringApplication.run(CsbiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {                   	//Executa algo quando a aplicação inicia

		Categoria cat1 = new Categoria(null, "Informática");				//Instancia um objeto categoria
		Categoria cat2 = new Categoria(null, "Escritório");					//Instancia um objeto categoria
		Categoria cat3 = new Categoria(null, "Software House");				//Instancia um objeto categoria
		
		repoCategoria.saveAll(Arrays.asList(cat1, cat2, cat3)); 			//Salva as categorias no banco de dados
		
	}
}
