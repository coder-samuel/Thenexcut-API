package br.com.thenextcut.barbershop.domain.dto;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;

import br.com.thenextcut.barbershop.domain.model.Contato;
import br.com.thenextcut.barbershop.domain.model.Telefone;
import lombok.Data;

@Data
public class ContatoDTO {
	
	private String email;
	private List<Telefone> telefones = new ArrayList<>();
	
	public static ContatoDTO create(Contato contato) {
		ModelMapper modelMapper = new ModelMapper();
		ContatoDTO contatoDTO = modelMapper.map(contato, ContatoDTO.class);
		return contatoDTO;
	}
}
