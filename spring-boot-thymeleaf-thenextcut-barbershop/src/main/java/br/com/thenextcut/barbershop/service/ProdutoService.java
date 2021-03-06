package br.com.thenextcut.barbershop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.thenextcut.barbershop.domain.dto.FornecedorDTO;
import br.com.thenextcut.barbershop.domain.dto.ProdutoDTO;
import br.com.thenextcut.barbershop.domain.model.Fornecedor;
import br.com.thenextcut.barbershop.domain.model.Produto;
import br.com.thenextcut.barbershop.repository.ProdutoRepository;
import br.com.thenextcut.barbershop.service.config.UpdateObjectsService;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private FornecedorService fornecedorService;

	public List<ProdutoDTO> getProdutos() {
		List<Produto> produtos = produtoRepository.findAll(Sort.by(Sort.Direction.ASC, "data"));
		List<ProdutoDTO> produtosDTO = new ArrayList<>();
		for (Produto produto : produtos) {
			produtosDTO.add(ProdutoDTO.create(produto));
		}
		return produtosDTO;
	}

	public ProdutoDTO getProdutoById(Long id) {
		Produto produto = produtoRepository.findById(id).get();
		produto.setData(produto.getData().plusHours(3));
		return ProdutoDTO.create(produto);
	}

	public ProdutoDTO postProduto(Produto produto) {
		if (produto.getFornecedor().getId().equals(-1L)) {
			produto.setFornecedor(null);
		}
		produto.setData(produto.getData().plusHours(-3));
		return ProdutoDTO.create(produtoRepository.save(produto));
	}

	public ProdutoDTO putProduto(Produto produto, Long id) {
		Produto produtoGravado = produtoRepository.findById(id).get(); // .get() pois deveria retornar um Optional<>
		Long idFornecedorGravado = null;
		if (!Objects.isNull(produtoGravado.getFornecedor())) {
			idFornecedorGravado = produtoGravado.getFornecedor().getId();
		} else {
			idFornecedorGravado = -1L;
		}
		Long idFornecedorUpdate = produto.getFornecedor().getId();

		if (!idFornecedorUpdate.equals(-1L) && !idFornecedorGravado.equals(idFornecedorUpdate)) {
			FornecedorDTO fornecedorDTO = fornecedorService.getFornecedorById(idFornecedorUpdate);
			ModelMapper modelMapper = new ModelMapper();
			produtoGravado.setFornecedor(modelMapper.map(fornecedorDTO, Fornecedor.class));
		}
		if (!produto.getData().plusHours(-3).equals(produtoGravado.getData())) {
			produtoGravado.setData(produto.getData().plusHours(-3));
		}
		if (produto.getFornecedor().getId().equals(-1L)) {
			produto.setFornecedor(null);
			produtoGravado.setFornecedor(null);
		}
		UpdateObjectsService.merge(produto, produtoGravado);
		return ProdutoDTO.create(produtoRepository.save(produtoGravado));
	}

	public List<ProdutoDTO> converteDataGTMLocal(List<ProdutoDTO> produtos) {
		for (ProdutoDTO produto : produtos) {
			produto.setData(produto.getData().plusHours(3));
		}
		return produtos;
	}

	public void deleteProduto(Long id) {
		produtoRepository.deleteById(id);
	}
}
