package com.spring;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.domain.Categoria;
import com.spring.domain.Cidade;
import com.spring.domain.Cliente;
import com.spring.domain.Endereco;
import com.spring.domain.Estado;
import com.spring.domain.ItemPedido;
import com.spring.domain.Pagamento;
import com.spring.domain.PagamentoComBoleto;
import com.spring.domain.Pedido;
import com.spring.domain.Produto;
import com.spring.domain.enums.EstadoPagamento;
import com.spring.domain.enums.TipoCliente;
import com.spring.repositories.CategoriaRepository;
import com.spring.repositories.CidadeRepository;
import com.spring.repositories.ClienteRepository;
import com.spring.repositories.EnderecoRepository;
import com.spring.repositories.EstadoRepository;
import com.spring.repositories.ItemPedidoRepository;
import com.spring.repositories.PagamentoRepository;
import com.spring.repositories.PedidoRepository;
import com.spring.repositories.ProdutoRepository;

@SpringBootApplication
public class ModeloConceitualApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(ModeloConceitualApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 1500.00);
		Produto p2 = new Produto(null, "Impressora", 180.00);
		Produto p3 = new Produto(null, "Mouse", 15.00);
		
		p1.getCategorias().add((Categoria) Arrays.asList(p1, p2, p3));
		p2.getCategorias().add((Categoria) Arrays.asList(p2, p3));
		p3.getCategorias().add((Categoria) Arrays.asList(p1));
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas gerais");
		Estado est2 = new Estado(null, "Sao paulo");

		Cidade c1 = new Cidade(null, "Uberlandia");
		Cidade c2 = new Cidade(null, "Nova lima");
		Cidade c3 = new Cidade(null, "Campinas");
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "99856587", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("965458742", "982563636"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto302", "Jardim", "35425256", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Abolicao", "14", "Apto102", "Esmeralda", "35428756", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/02/2021 10:23"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("11/05/2021 15:38"), cli1, e2);
		
		Pagamento pagto1 = new PagamentoComBoleto(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("25/08/2020 04:15"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1  = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2  = new ItemPedido(ped2, p3, 0.00, 2, 80.00);
		ItemPedido ip3  = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().add((Categoria) Arrays.asList(ip1));
		p2.getItens().add((Categoria) Arrays.asList(ip3));
		p3.getItens().add((Categoria) Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
		
		
		
		
		
		
		
		
	
		
		
		
		

		
		
		
		
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		
		
	}
	
}