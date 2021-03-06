package br.com.thenextcut.barbershop.controller.page;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

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
import br.com.thenextcut.barbershop.domain.model.Pessoa;
import br.com.thenextcut.barbershop.service.PessoaService;

@Controller
@RequestMapping("/agenda/pessoas")
public class PessoaController {

	@Value("${url.domain}")
	private String domainURL;

	@Value("${server.servlet.context-path}")
	private String pathURL;

	private static Logger logger = LoggerFactory.getLogger(ThenextcutBarbershopApplication.class);

	@Autowired
	private PessoaService pessoaService;

	@GetMapping()
	public String agenda(ModelMap model) {
		logger.info("# Pagina inicial de contatos pessoas");
		model.addAttribute("pessoas", pessoaService.getPessoas());
		return "agenda/index-agenda-pessoas.html";
	}

	@GetMapping("/editar/{id}/pessoa")
	public ModelAndView editarPessoa(@PathVariable("id") Long id) {
		logger.info("# Pagina editar pessoa com id: " + id);
		ModelAndView mvc = new ModelAndView("agenda/editar-agenda-pessoas.html");
		mvc.addObject("pessoa", pessoaService.getPessoaById(id));
		return mvc;
	}

	@GetMapping("/adicionar")
	public ModelAndView adicionarPessoa(Pessoa pessoa) {
		logger.info("# Pagina adicionar nova pessoa");
		ModelAndView mv = new ModelAndView("agenda/editar-agenda-pessoas.html");
		mv.addObject("pessoa", pessoa);
		return mv;
	}

	@PostMapping("/save")
	public String save(Pessoa pessoa, @RequestParam("pessoaId") Long id) {
		if (Objects.isNull(id)) {
			logger.info("# Inserindo nova pessoa com body: " + pessoa.toString());
			pessoaService.postPessoa(pessoa);
		} else {
			logger.info("# Atualizando pessoa com id: " + id);
			pessoaService.putPessoa(pessoa, id);
		}
		return "redirect:";
	}

	@DeleteMapping("/remover/{id}/pessoa")
	public String delete(@PathVariable("id") Long id) {
		logger.info("# Removendo pessoa com id: " + id);
		pessoaService.deletePessoa(id);
		return "redirect:" + domainURL + pathURL + "/agenda/pessoas";
	}
}
