package br.com.thenextcut.barbershop.domain.dto;

import org.modelmapper.ModelMapper;

import br.com.thenextcut.barbershop.domain.model.Endereco;
import lombok.Data;

@Data
public class EnderecoDTO {

	private String rua;
	private String bairro;
	private String numero;
	private String cep;
	private String cidade;
	private String estado;
	
	public static EnderecoDTO create(Endereco endereco) {
		ModelMapper modelMapper = new ModelMapper();
		EnderecoDTO enderecoDTO = modelMapper.map(endereco, EnderecoDTO.class);
		return enderecoDTO;
	}
	
}
