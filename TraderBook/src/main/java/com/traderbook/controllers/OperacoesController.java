package com.traderbook.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.traderbook.domains.Banca;
import com.traderbook.domains.Operacao;
import com.traderbook.repositories.RepositorioBanca;
import com.traderbook.repositories.RepositorioCompeticao;
import com.traderbook.repositories.RepositorioEstrategia;
import com.traderbook.repositories.RepositorioMercado;
import com.traderbook.repositories.RepositorioOperacao;
import com.traderbook.repositories.RepositorioPais;

@Controller
@RequestMapping("/operations")
public class OperacoesController {
	
	@Autowired
	private RepositorioOperacao repositorioOperacao;
	@Autowired
	private RepositorioCompeticao repositorioCompeticao;
	@Autowired
	private RepositorioEstrategia repositorioEstrategia;
	@Autowired
	private RepositorioMercado repositorioMercado;
	@Autowired
	private RepositorioPais repositorioPais;
	@Autowired
	private RepositorioBanca repositorioBanca;
	
	@GetMapping("/list")
	public ModelAndView listar() {
		ModelAndView result = new ModelAndView("operacao/listar");
		List<Operacao> operacoes = repositorioOperacao.findAll();
		operacoes.sort((d1, d2) -> {
			if(d1.getData().after(d2.getData()))
				return 1;
			if(d1.getData().before(d2.getData()))
				return -1;
			return 0;
		});
		result.addObject("operacoes", operacoes);
		return result;
	}
	
	@GetMapping("/add")
	public ModelAndView adicionar() {
		ModelAndView result = new ModelAndView("operacao/adicionar");
		Operacao operacao = new Operacao();
		result.addObject("operacao", operacao);
		result.addObject("competicoes", repositorioCompeticao.findAll());
		result.addObject("estrategias", repositorioEstrategia.findAll());
		result.addObject("mercados", repositorioMercado.findAll());
		result.addObject("paises", repositorioPais.findAll());
		result.addObject("bancas", repositorioBanca.findAll());
		return result;
	}
	
	@PostMapping("/add")
	public String adicionar(Operacao operacao) {
		//Calculando o retorno sobre investimento
		operacao.setROI(operacao.getValorRetorno() / operacao.getValorInvestido());
		//Recuperando a banca escolhida
		Banca banca = repositorioBanca.getOne(operacao.getBancaOperacao().getId());
		//Atualizando saldo da banca
		double atualizaSaldo = banca.getSaldo() + operacao.getValorRetorno();
		banca.setSaldo(atualizaSaldo);
		//Salvando alterações
		repositorioOperacao.save(operacao);
		repositorioBanca.save(banca);
		return "redirect:/operations/list";
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView result = new ModelAndView("operacao/editar");
		//Repassando o valor anterior, para posterior alteração no saldo da banca
		Operacao operacao = repositorioOperacao.getOne(id);
		operacao.setValorAnterior(operacao.getValorRetorno());
		
		result.addObject("operacao", operacao);
		result.addObject("competicoes", repositorioCompeticao.findAll());
		result.addObject("estrategias", repositorioEstrategia.findAll());
		result.addObject("mercados", repositorioMercado.findAll());
		result.addObject("paises", repositorioPais.findAll());
		result.addObject("bancas", repositorioBanca.findAll());
		return result;
	}
	
	@PostMapping("/edit")
	public String editar(Operacao operacao) {
		//Calculando o retorno sobre investimento
		operacao.setROI(operacao.getValorRetorno() / operacao.getValorInvestido());
		//Alterar valor na banca
		Banca banca = repositorioBanca.getOne(operacao.getBancaOperacao().getId());
		double valorAAtualizar = operacao.getValorRetorno() - operacao.getValorAnterior();
		valorAAtualizar += banca.getSaldo();
		banca.setSaldo(valorAAtualizar);
		//Salvando altareções
		repositorioBanca.save(banca);
		repositorioOperacao.save(operacao);
		return "redirect:/operations/list";
	}
	
	@GetMapping("/delete/{id}")
	public String excluir(@PathVariable Long id) {
		Operacao operacao = repositorioOperacao.getOne(id);
		Banca banca = repositorioBanca.getOne(operacao.getBancaOperacao().getId());
		//Devolvendo valor para a banca
		double valorADevolver = operacao.getValorRetorno() * -1.0;
		valorADevolver += banca.getSaldo();
		banca.setSaldo(valorADevolver);
		//Realizando o ajuste na banca e excluindo a operação
		repositorioBanca.save(banca);
		repositorioOperacao.deleteById(id);
		return "redirect:/operations/list";
	}

}
