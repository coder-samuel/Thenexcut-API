package br.com.thenextcut.barbershop.domain.dto;

import org.modelmapper.ModelMapper;

import br.com.thenextcut.barbershop.domain.model.Contato;
import br.com.thenextcut.barbershop.domain.model.Endereco;
import br.com.thenextcut.barbershop.domain.model.Fornecedor;
import lombok.Data;

@Data
public class FornecedorDTO {

	private Long id;
	private String nome;
	private String cnpj;
	private String ramo;
	private Contato contato;
	private Endereco endereco;
	
	public static FornecedorDTO create(Fornecedor fornecedor) {
		ModelMapper modelMapper = new ModelMapper();
		FornecedorDTO fornecedorDTO = modelMapper.map(fornecedor, FornecedorDTO.class);
		return fornecedorDTO;
	}

}
