package br.com.thenextcut.barbershop.domain.dto;

import org.modelmapper.ModelMapper;

import br.com.thenextcut.barbershop.domain.model.Telefone;
import br.com.thenextcut.barbershop.domain.model.enums.TipoTelefone;
import lombok.Data;

@Data
public class TelefoneDTO {

	private TipoTelefone tipoTelefone;
	private String numeroTelefone;
	
	public static TelefoneDTO create(Telefone telefone) {
		ModelMapper modelMapper = new ModelMapper();
		TelefoneDTO telefoneDTO = modelMapper.map(telefone, TelefoneDTO.class);
		return telefoneDTO;
	}

}
