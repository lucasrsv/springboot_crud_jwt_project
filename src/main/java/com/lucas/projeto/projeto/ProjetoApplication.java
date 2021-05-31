package com.lucas.projeto.projeto;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import com.lucas.projeto.projeto.domain.Categoria;
import com.lucas.projeto.projeto.domain.Cidade;
import com.lucas.projeto.projeto.domain.Cliente;
import com.lucas.projeto.projeto.domain.Endereco;
import com.lucas.projeto.projeto.domain.Estado;
import com.lucas.projeto.projeto.domain.ItemPedido;
import com.lucas.projeto.projeto.domain.Pagamento;
import com.lucas.projeto.projeto.domain.PagamentoComBoleto;
import com.lucas.projeto.projeto.domain.PagamentoComCartao;
import com.lucas.projeto.projeto.domain.Pedido;
import com.lucas.projeto.projeto.domain.Produto;
import com.lucas.projeto.projeto.domain.enums.EstadoPagamento;
import com.lucas.projeto.projeto.domain.enums.TipoCliente;
import com.lucas.projeto.projeto.repositories.CategoriaRepository;
import com.lucas.projeto.projeto.repositories.CidadeRepository;
import com.lucas.projeto.projeto.repositories.ClienteRepository;
import com.lucas.projeto.projeto.repositories.EnderecoRepository;
import com.lucas.projeto.projeto.repositories.EstadoRepository;
import com.lucas.projeto.projeto.repositories.ItemPedidoRepository;
import com.lucas.projeto.projeto.repositories.PagamentoRepository;
import com.lucas.projeto.projeto.repositories.PedidoRepository;
import com.lucas.projeto.projeto.repositories.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetoApplication implements CommandLineRunner {

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
		SpringApplication.run(ProjetoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);

		Estado est1 = new Estado(null, "Pernambuco");
		Estado est2 = new Estado(null, "Paraíba");

		Cidade c1 = new Cidade(null, "Recife", est1);
		Cidade c2 = new Cidade(null, "João Pessoa", est2);

		Cliente cli1 = new Cliente(null, "Maria", "lucasrsvalenca@gmail.com", "11111111111", TipoCliente.PESSOAFISICA,
				"123");

		Endereco end1 = new Endereco(null, "Rua Flores", "300", "Apt. 303", "Jardim", "33990011", cli1, c1);

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("20/06/2000 20:30"), cli1, end1);

		Pedido ped2 = new Pedido(null, sdf.parse("20/06/2000 21:30"), cli1, end1);

		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);

		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2021 00:00"),
				null);
		ped2.setPagamento(pag2);

		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 800.0);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.0);

		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2));

		cli1.getTelefones().addAll(Arrays.asList("33333333", "99999999"));
		cli1.getEnderecos().addAll(Arrays.asList(end1));

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2));
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1));
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1, pag2));
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

	}

}
