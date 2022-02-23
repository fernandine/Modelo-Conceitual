package com.spring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.domain.Categoria;
import com.spring.domain.Cidade;
import com.spring.domain.Estado;
import com.spring.domain.Produto;
import com.spring.repositories.CategoriaRepository;
import com.spring.repositories.CidadeRepository;
import com.spring.repositories.EstadoRepository;
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
		
		Estado est1 = new Estado(null, "Minas gerais");
		Estado est2 = new Estado(null, "Sao paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia");
		Cidade c2 = new Cidade(null, "Nova lima");
		Cidade c3 = new Cidade(null, "Campinas");
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
	}
	
}