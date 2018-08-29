package com.lribeiro.csbi;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lribeiro.csbi.domain.Categoria;
import com.lribeiro.csbi.domain.Cidade;
import com.lribeiro.csbi.domain.Cliente;
import com.lribeiro.csbi.domain.Endereco;
import com.lribeiro.csbi.domain.Estado;
import com.lribeiro.csbi.domain.ItemPedido;
import com.lribeiro.csbi.domain.Pagamento;
import com.lribeiro.csbi.domain.PagamentoBoleto;
import com.lribeiro.csbi.domain.PagamentoCartao;
import com.lribeiro.csbi.domain.Pedido;
import com.lribeiro.csbi.domain.Produto;
import com.lribeiro.csbi.domain.enums.EstadoPagamento;
import com.lribeiro.csbi.domain.enums.TipoCliente;
import com.lribeiro.csbi.repositories.CategoriaRepository;
import com.lribeiro.csbi.repositories.CidadeRepository;
import com.lribeiro.csbi.repositories.ClienteRepository;
import com.lribeiro.csbi.repositories.EnderecoRepository;
import com.lribeiro.csbi.repositories.EstadoRepository;
import com.lribeiro.csbi.repositories.ItemPedidoRepository;
import com.lribeiro.csbi.repositories.PagamentoRepository;
import com.lribeiro.csbi.repositories.PedidoRepository;
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
	
	@Autowired
	private ClienteRepository repoCliente;
	
	@Autowired
	private EnderecoRepository repoEndereco;
	
	@Autowired
	private PedidoRepository repoPedido;
	
	@Autowired
	private PagamentoRepository repoPagamento;
	
	@Autowired
	private ItemPedidoRepository repoItemPedido;
	
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
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "3637812377", TipoCliente.PESSOA_FISICA);      	//Instancia um objeto Cliente
				
		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim", "38220834", cli1, cid1);				//Instancia um objeto Endereco
		Endereco end2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, cid2);				//Instancia um objeto Endereco
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pgto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		Pagamento pgto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		
		ItemPedido item1 = new ItemPedido(ped1, prod1, 0.00, 1, 2000.00);
		ItemPedido item2 = new ItemPedido(ped1, prod3, 0.00, 2, 80.00);
		ItemPedido item3 = new ItemPedido(ped2, prod2, 100.00, 1, 800.00);
		
		ped1.setPagamento(pgto1);											//Seta o pagamento do pedido
		ped1.getItens().addAll(Arrays.asList(item1, item2));				//Associa os itens ao pedido
		
		ped2.setPagamento(pgto2);											//Seta o pagamento do pedido
		ped1.getItens().addAll(Arrays.asList(item3));						//Associa os itens ao pedido
				
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));				//Associa os pedidos ao cliente
		
		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));		//Incluindo os produtos nas listas de produtos da categoria (relacionamento virtual) 
		cat2.getProdutos().addAll(Arrays.asList(prod2));					//Incluindo os produtos nas listas de produtos da categoria (relacionamento virtual)
		
		prod1.getCategorias().addAll(Arrays.asList(cat1));					//Incluindo as categorias nas listas de categorias do produto (relacionamento virtual)
		prod1.getItens().addAll(Arrays.asList(item1));						//Associa itens de pedido ao produto
		
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));			//Incluindo as categorias nas listas de categorias do produto (relacionamento virtual)
		prod2.getItens().addAll(Arrays.asList(item3));						//Associa itens de pedido ao produto
		
		prod3.getCategorias().addAll(Arrays.asList(cat1));					//Incluindo as categorias nas listas de categorias do produto (relacionamento virtual)
		prod3.getItens().addAll(Arrays.asList(item2));						//Associa itens de pedido ao produto
		
		est1.getCidades().addAll(Arrays.asList(cid1));						//Incluindo as cidades nas listas de cidades do estado (relacionamento virtual)
		est2.getCidades().addAll(Arrays.asList(cid2, cid3));				//Incluindo as cidades nas listas de cidades do estado (relacionamento virtual)
		
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));	//Adiciona telefones ao cliente
		cli1.getEnderecos().addAll(Arrays.asList(end1, end2));  			//Adiciona enderecos ao cliente
		
	
		repoCategoria.saveAll(Arrays.asList(cat1, cat2)); 					//Salva as categorias no banco de dados
		repoProduto.saveAll(Arrays.asList(prod1, prod2, prod3)); 			//Salva os produtos no banco de dados
		repoEstado.saveAll(Arrays.asList(est1, est2));						//Salva os estados no banco de dados (os estados tem que vir primeiro devido a integridade referencial)
		repoCidade.saveAll(Arrays.asList(cid1, cid2, cid3)); 				//Salva as cidades no banco de dados
		repoCliente.saveAll(Arrays.asList(cli1));							//Salva os clientes no banco de dados
		repoEndereco.saveAll(Arrays.asList(end1, end2));					//Salva os enderecos no banco de dados
		repoPedido.saveAll(Arrays.asList(ped1, ped2));						//Salva os pedidos no banco de dados		
		repoPagamento.saveAll(Arrays.asList(pgto1, pgto2));					//Salva os pagamentos no banco de dados		
		repoItemPedido.saveAll(Arrays.asList(item1, item2, item3));			//Salva os itens do pedido no banco de dados
	}
}
