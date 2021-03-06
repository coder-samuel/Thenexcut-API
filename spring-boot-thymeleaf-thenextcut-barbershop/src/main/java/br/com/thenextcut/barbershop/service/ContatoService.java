package br.com.thenextcut.barbershop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thenextcut.barbershop.domain.dto.ContatoDTO;
import br.com.thenextcut.barbershop.domain.model.Contato;
import br.com.thenextcut.barbershop.repository.ContatoRepository;
import br.com.thenextcut.barbershop.service.config.UpdateObjectsService;

@Service
public class ContatoService {

	@Autowired
	private ContatoRepository contatoRepository;

	public List<ContatoDTO> getContatos() {
		List<Contato> contatos = contatoRepository.findAll();
		List<ContatoDTO> contatosDTO = new ArrayList<>();
		for (Contato contato : contatos) {
			contatosDTO.add(ContatoDTO.create(contato));
		}
		return contatosDTO;
	}

	public ContatoDTO getContatoById(Long id) {
		Optional<Contato> contato = contatoRepository.findById(id);
		return ContatoDTO.create(contato.get());
	}
	
	public ContatoDTO postContato(Contato contato) {
		return ContatoDTO.create(contatoRepository.save(contato));
	}

	public ContatoDTO putContato(Contato contato, Long id) {
		Contato contatoGravado = contatoRepository.findById(id).get(); // .get() pois deveria retornar um Optional<>
		UpdateObjectsService.merge(contato, contatoGravado);
		return ContatoDTO.create(contatoRepository.save(contatoGravado));
	}

	public void deleteContato(Long id) {
		contatoRepository.deleteById(id);
	}
}
