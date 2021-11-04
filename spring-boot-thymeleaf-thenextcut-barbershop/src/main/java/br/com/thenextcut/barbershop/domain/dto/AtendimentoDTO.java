package br.com.thenextcut.barbershop.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.thenextcut.barbershop.domain.model.Atendimento;
import br.com.thenextcut.barbershop.domain.model.Pessoa;
import br.com.thenextcut.barbershop.domain.model.enums.ModoPagamento;
import br.com.thenextcut.barbershop.domain.model.enums.StatusPagamento;
import br.com.thenextcut.barbershop.domain.model.enums.TipoAtendimento;
import lombok.Data;

@Data
public class AtendimentoDTO {

	private Long id;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime dataAtendimento;
	
	private BigDecimal valorAtendimento;
	private boolean atendimentoRealizado;
	private TipoAtendimento tipoAtendimento;
	private StatusPagamento statusPagamento;
	private ModoPagamento modoPagamento;
	private Pessoa pessoa;
	
	public static AtendimentoDTO create(Atendimento atendimento) {
		ModelMapper modelMapper = new ModelMapper();
		AtendimentoDTO atendimentosMarcadosDTO = modelMapper.map(atendimento, AtendimentoDTO.class);
		return atendimentosMarcadosDTO;
	}
}
