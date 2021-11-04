package br.com.thenextcut.barbershop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thenextcut.barbershop.domain.dto.FornecedorDTO;
import br.com.thenextcut.barbershop.domain.model.Fornecedor;
import br.com.thenextcut.barbershop.repository.FornecedorRepository;
import br.com.thenextcut.barbershop.service.config.UpdateObjectsService;

@Service
public class FornecedorService {

	@Autowired
	private FornecedorRepository fornecedorRepository;

	public List<FornecedorDTO> getFornecedores() {
		List<Fornecedor> fornecedores = fornecedorRepository.findAll();
		List<FornecedorDTO> fornecedoresDTO = new ArrayList<>();
		for (Fornecedor fornecedor : fornecedores) {
			fornecedoresDTO.add(FornecedorDTO.create(fornecedor));
		}
		return fornecedoresDTO;
	}

	public FornecedorDTO getFornecedorById(Long id) {
		Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
		return FornecedorDTO.create(fornecedor.get());
	}
	
	public FornecedorDTO postFornecedor(Fornecedor fornecedor) {
		UpdateObjectsService.associaFornecedorTelefone(fornecedor);
		return FornecedorDTO.create(fornecedorRepository.save(fornecedor));
	}

	public FornecedorDTO putFornecedor(Fornecedor fornecedor, Long id) {
		Fornecedor fornecedorGravado = fornecedorRepository.findById(id).get(); // .get() pois deveria retornar um Optional<>
		UpdateObjectsService.merge(fornecedor, fornecedorGravado);
		return FornecedorDTO.create(fornecedorRepository.save(fornecedorGravado));
	}

	public void deleteFornecedor(Long id) {
		fornecedorRepository.deleteById(id);
	}
}
