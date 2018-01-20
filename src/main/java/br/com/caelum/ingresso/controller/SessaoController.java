package br.com.caelum.ingresso.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.ImagemCapa;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.rest.ImdbClient;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessao;

/**
 * Created by Tiago Tiede on 06/01/18.
 */
@Controller
public class SessaoController {


    @Autowired
    private SalaDao salaDao;
    
    @Autowired
    private SessaoDao sessaoDao;
    
    @Autowired
    private FilmeDao filmeDao;
    
    @Autowired
    private ImdbClient client;
    
    @GetMapping("/admin/sessao")
    public ModelAndView form(@RequestParam("salaId") Integer salaId, SessaoForm form){
    	form.setSalaId(salaId);
        ModelAndView modelAndView = new ModelAndView("sessao/sessao");
        modelAndView.addObject("sala", salaDao.findOne(salaId));
        modelAndView.addObject("filmes", filmeDao.findAll());
        modelAndView.addObject("form", form);
        
        return modelAndView;
    }
    @PostMapping(value = "/admin/sessao")
    @Transactional
    public ModelAndView salva(@Valid SessaoForm form, BindingResult result){
    	if(result.hasErrors()) return form(form.getSalaId(), form);
    	ModelAndView modelAndView = new ModelAndView("redirect:/admin/sala/"+form.getSalaId()+"/sessoes");
    	Sessao sessao = form.toSessao(salaDao, filmeDao);
    	Sala sala =  salaDao.findOne(form.getSalaId());
    	GerenciadorDeSessao gerenciadorDeSessao = new GerenciadorDeSessao( sessaoDao.buscaSessoesDaSala(sala));
    	if(gerenciadorDeSessao.cabe(sessao)){
    		sessaoDao.save(sessao);
    	}
    	return modelAndView;
    }
    
    
    
    @GetMapping("/sessao/{id}/lugares")
    public ModelAndView lugaresNaSessao(@PathVariable("id")  Integer sessaoId){
    	ModelAndView modelAndView = new ModelAndView("sessao/lugares");
    	Sessao sessao = sessaoDao.fineOne(sessaoId);
    
    	Optional<ImagemCapa> imagemCapa = client.request(sessao.getFilme(), ImagemCapa.class);
        
    	modelAndView.addObject("sessao", sessao);
    	modelAndView.addObject("imagemCapa", imagemCapa.orElse(new ImagemCapa()));
    	modelAndView.addObject("tiposDeIngressos", TipoDeIngresso.values());
    	
        return modelAndView;
    }
}
