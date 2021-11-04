package br.com.thenextcut.barbershop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.thenextcut.barbershop.domain.dto.TelefoneDTO;
import br.com.thenextcut.barbershop.domain.model.Telefone;
import br.com.thenextcut.barbershop.repository.TelefoneRepository;
import br.com.thenextcut.barbershop.service.config.UpdateObjectsService;

@Service
public class TelefoneService {

	@Autowired
	private TelefoneRepository telefoneRepository;

	public List<TelefoneDTO> getTelefones() {
		List<Telefone> telefones = telefoneRepository.findAll();
		List<TelefoneDTO> telefonesDTO = new ArrayList<>();
		for (Telefone telefone : telefones) {
			telefonesDTO.add(TelefoneDTO.create(telefone));
		}
		return telefonesDTO;
	}

	public TelefoneDTO getTelefoneById(Long id) {
		Optional<Telefone> telefone = telefoneRepository.findById(id);
		return TelefoneDTO.create(telefone.get());
	}

	public TelefoneDTO postTelefone(Telefone telefone) {
		return TelefoneDTO.create(telefoneRepository.save(telefone));
	}

	public TelefoneDTO putTelefone(Telefone telefone, Long id) {
		Telefone telefoneGravado = telefoneRepository.findById(id).get(); // .get() pois deveria retornar um Optional<>
		UpdateObjectsService.merge(telefone, telefoneGravado);
		return TelefoneDTO.create(telefoneRepository.save(telefoneGravado));
	}

	public void deleteTelefone(Long id) {
		telefoneRepository.deleteById(id);
	}
}
