package br.com.thenextcut.barbershop.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.thenextcut.barbershop.domain.dto.AtendimentoDTO;
import br.com.thenextcut.barbershop.domain.dto.PessoaDTO;
import br.com.thenextcut.barbershop.domain.model.Atendimento;
import br.com.thenextcut.barbershop.domain.model.Pessoa;
import br.com.thenextcut.barbershop.repository.AtendimentoRepository;
import br.com.thenextcut.barbershop.service.config.PeriodicidadeAtendimentosEnum;
import br.com.thenextcut.barbershop.service.config.UpdateObjectsService;

@Service
public class AtendimentoService {

	@Autowired
	private AtendimentoRepository atendimentosRepository;

	@Autowired
	private PessoaService pessoaService;

	public List<AtendimentoDTO> getAtendimentos(PeriodicidadeAtendimentosEnum periodicidadeAtendimentos) {
		List<Atendimento> atendimentos = atendimentosRepository.findAll(Sort.by(Sort.Direction.ASC, "dataAtendimento"));
		List<AtendimentoDTO> atendimentosDTO = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		switch (periodicidadeAtendimentos.getCode()) {
			// Todos os Atendimentos
			case 1:
				for (Atendimento atendimento : atendimentos) {
					atendimentosDTO.add(AtendimentoDTO.create(atendimento));
				}
				break;
			// Atendimentos do dia
			case 2:
				for (Atendimento atendimento : atendimentos) {
					if (atendimento.getDataAtendimento().getDayOfMonth() == LocalDateTime.now().getDayOfMonth()
							&& atendimento.getDataAtendimento().getMonth() == LocalDateTime.now().getMonth()
							&& atendimento.getDataAtendimento().getYear() == LocalDateTime.now().getYear()) {
						atendimentosDTO.add(AtendimentoDTO.create(atendimento));
					}
				}
				break;
			// Atendimentos da semana vigente
			case 3:
				break;
			// Atendimentos do mes vigente
			case 4:
				break;
			// Atendimentos do ano vigente
			case 5:
				break;
			// Atendimentos ja realizados
			case 6:
				for (Atendimento atendimento : atendimentos) {

					if (atendimento.getDataAtendimento().isAfter(LocalDateTime.now())) {
						atendimentosDTO.add(AtendimentoDTO.create(atendimento));
					}
				}
				break;
			default:
				break;
		}
		return atendimentosDTO;
	}

	public List<AtendimentoDTO> converteDataGTMLocal(List<AtendimentoDTO> atendimentos) {
		for (AtendimentoDTO atendimento : atendimentos) {
			atendimento.setDataAtendimento(atendimento.getDataAtendimento().plusHours(3));
		}
		return atendimentos;
	}

	public AtendimentoDTO getAtendimentoById(Long id) {
		Atendimento atendimentos = atendimentosRepository.findById(id).get();
		atendimentos.setDataAtendimento(atendimentos.getDataAtendimento().plusHours(3));
		return AtendimentoDTO.create(atendimentos);
	}

	public AtendimentoDTO postAtendimento(Atendimento atendimento) {
		atendimento.setDataAtendimento(atendimento.getDataAtendimento().plusHours(-3));
		return AtendimentoDTO.create(atendimentosRepository.save(atendimento));
	}

	public AtendimentoDTO putAtendimento(Atendimento atendimento, Long id) {
		Atendimento atendimentoGravado = atendimentosRepository.findById(id).get();
		Long idPessoaGravada = atendimentoGravado.getPessoa().getId();
		Long idPessoaUpdate = atendimento.getPessoa().getId();

		if (!idPessoaGravada.equals(idPessoaUpdate)) {
			PessoaDTO pessoaDTO = pessoaService.getPessoaById(idPessoaUpdate);
			ModelMapper modelMapper = new ModelMapper();
			atendimentoGravado.setPessoa(modelMapper.map(pessoaDTO, Pessoa.class));
		}

		if (!atendimento.getDataAtendimento().plusHours(-3).equals(atendimentoGravado.getDataAtendimento())) {
			atendimentoGravado.setDataAtendimento(atendimento.getDataAtendimento().plusHours(-3));
		}
		UpdateObjectsService.merge(atendimento, atendimentoGravado);
		return AtendimentoDTO.create(atendimentosRepository.save(atendimentoGravado));
	}

	public void deleteAtendimento(Long id) {
		atendimentosRepository.deleteById(id);
	}
}
