package br.com.thenextcut.barbershop.controller.page;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.thenextcut.barbershop.ThenextcutBarbershopApplication;
import br.com.thenextcut.barbershop.domain.dto.AtendimentoDTO;
import br.com.thenextcut.barbershop.domain.model.Atendimento;
import br.com.thenextcut.barbershop.service.AtendimentoService;
import br.com.thenextcut.barbershop.service.PessoaService;
import br.com.thenextcut.barbershop.service.config.PeriodicidadeAtendimentosEnum;

@Controller
@RequestMapping("/calendario/servicos")
public class CalendarioServicosController {

	@Value("${url.domain}")
	private String domainURL;
	
	@Value("${server.servlet.context-path}")
	private String pathURL;
	
	private static Logger logger = LoggerFactory.getLogger(ThenextcutBarbershopApplication.class);
	
	@Autowired
	private AtendimentoService atendimentoService;

	@Autowired
	private PessoaService pessoaService;

	@GetMapping()
	public String agenda(ModelMap model)  {
		logger.info("# Pagina inicial de atendimentos serviços");
		List<AtendimentoDTO> totais= atendimentoService.getAtendimentos(PeriodicidadeAtendimentosEnum.TODOS);
		model.addAttribute("atendimentosTotais", atendimentoService.converteDataGTMLocal(totais));
		return "calendario-atendimentos-servicos/index-calendario-servicos.html";
	}

	@GetMapping("/hoje")
	public String agendaHoje(ModelMap model){
		logger.info("# Pagina inicial de atendimentos HOJE");
		List<AtendimentoDTO> totais= atendimentoService.getAtendimentos(PeriodicidadeAtendimentosEnum.DIARIO);
		model.addAttribute("atendimentosTotais", atendimentoService.converteDataGTMLocal(totais));
		return "calendario-atendimentos-servicos/atendimentos-hoje.html";
	}

	@GetMapping("/editar/{id}/servicos")
	public ModelAndView editarPessoa(@PathVariable("id") Long id) {
		logger.info("# Pagina editar servico com id: " + id);
		ModelAndView mvc = new ModelAndView("calendario-atendimentos-servicos/editar-calendario-servicos.html");
		mvc.addObject("atendimento", atendimentoService.getAtendimentoById(id));
		mvc.addObject("pessoas", pessoaService.getPessoas());
		return mvc;
	}

	@GetMapping("/adicionar")
	public ModelAndView adicionarAtendimento(Atendimento atendimento) {
		logger.info("# Pagina adicionar novo Servico");
		ModelAndView mvc = new ModelAndView("calendario-atendimentos-servicos/editar-calendario-servicos.html");
		mvc.addObject("atendimento", atendimento);
		mvc.addObject("pessoas", pessoaService.getPessoas());
		return mvc;
	}

	@PostMapping("/save")
	public String save(Atendimento atendimento, @RequestParam("atendimentoId") Long id) {
		if (Objects.isNull(id)) {
			logger.info("# Inserindo novo servico com body: " + atendimento.toString());
			atendimentoService.postAtendimento(atendimento);
		} else {
			logger.info("# Atualizando servico com id: " + id);
			atendimentoService.putAtendimento(atendimento, id);
		}
		return "redirect:";
	}

	@DeleteMapping("/remover/{id}/servico")
	public String delete(@PathVariable("id") Long id) {
		logger.info("# Removendo servico com id: " + id);
		atendimentoService.deleteAtendimento(id);
		return "redirect:" + domainURL + pathURL + "/calendario/servicos";
	}
	
}
