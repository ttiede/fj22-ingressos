package br.com.caelum.ingresso.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DetalhesDoFilme {

	@JsonProperty("Title")
	private String titulo;
	
	@JsonProperty("Year")
	private Integer ano;
	
	@JsonProperty("Poster")
	private String imagem;
	
	@JsonProperty("Director")
	private String diretores;
	
	@JsonProperty("Writer")
	private String escritores;

	@JsonProperty("Actors")
	private String atores;

	@JsonProperty("Plot")
	private String descricao;
	
	@JsonProperty("imdRating")
	private String avaliacao;


}
