package br.com.caelum.ingresso.rest;

import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.caelum.ingresso.model.DetalhesDoFilme;
import br.com.caelum.ingresso.model.Filme;

@Component
public class ImdbClient {
	private Logger logger = Logger.getLogger(ImdbClient.class);
	public Optional<DetalhesDoFilme> request(Filme filme){
		RestTemplate client = new RestTemplate();
		String titulo = filme.getNome().replace(" ", "+");
		
		String url = String.format("https://imdb-fj22.herokuapp.com/imdb?title=%s", titulo);
		try{
			DetalhesDoFilme detalhesDoFilme = client.getForObject(url, DetalhesDoFilme.class);
			System.out.println(url);
			return Optional.of(detalhesDoFilme);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			return Optional.empty();
		}
	}
	
}
