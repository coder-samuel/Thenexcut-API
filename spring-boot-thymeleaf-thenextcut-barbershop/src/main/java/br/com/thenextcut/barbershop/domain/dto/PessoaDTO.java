package br.com.thenextcut.barbershop.domain.dto;

import org.modelmapper.ModelMapper;

import br.com.thenextcut.barbershop.domain.model.Contato;
import br.com.thenextcut.barbershop.domain.model.Endereco;
import br.com.thenextcut.barbershop.domain.model.Pessoa;
import lombok.Data;

@Data
public class PessoaDTO {
	
	private Long id;
	private String nome;
	private Endereco endereco;
	private Contato contato;
	
	public static PessoaDTO create(Pessoa pessoa) {
		ModelMapper modelMapper = new ModelMapper();
		PessoaDTO pessoaDTO = modelMapper.map(pessoa, PessoaDTO.class);
		return pessoaDTO;
	}
}
