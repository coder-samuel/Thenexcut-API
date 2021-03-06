package br.com.thenextcut.barbershop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thenextcut.barbershop.domain.dto.EnderecoDTO;
import br.com.thenextcut.barbershop.domain.model.Endereco;
import br.com.thenextcut.barbershop.repository.EnderecoRepository;
import br.com.thenextcut.barbershop.service.config.UpdateObjectsService;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public List<EnderecoDTO> getEnderecos() {
		List<Endereco> enderecos = enderecoRepository.findAll();
		List<EnderecoDTO> enderecosDTO = new ArrayList<>();
		for (Endereco endereco : enderecos) {
			enderecosDTO.add(EnderecoDTO.create(endereco));
		}
		return enderecosDTO;
	}

	public EnderecoDTO getEnderecoById(Long id) {
		Optional<Endereco> endereco = enderecoRepository.findById(id);
		return EnderecoDTO.create(endereco.get());
	}

	public EnderecoDTO postEndereco(Endereco endereco) {
		return EnderecoDTO.create(enderecoRepository.save(endereco));
	}

	public EnderecoDTO putEndereco(Endereco endereco, Long id) {		
		Endereco enderecoGravado = enderecoRepository.findById(id).get(); // .get() pois deveria retornar um Optional<>
		UpdateObjectsService.merge(endereco, enderecoGravado);
		return EnderecoDTO.create(enderecoRepository.save(enderecoGravado));
	}

	public void deleteEndereco(Long id) {
		enderecoRepository.deleteById(id);
	}

}
