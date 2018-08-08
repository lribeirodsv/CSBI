package com.lribeiro.csbi.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.lribeiro.csbi.domain.Categoria;
import com.lribeiro.csbi.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService servCategoria;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)  			//endpoint sera /categorias/id sendo que id é um parametro
	public ResponseEntity<?> listar(@PathVariable Integer id) {       	//armazena informacoes de uma resposta HTTP para um servico REST
		
		Categoria obj = servCategoria.buscaCategoria(id);
		return ResponseEntity.ok().body(obj);
	}
	
}
