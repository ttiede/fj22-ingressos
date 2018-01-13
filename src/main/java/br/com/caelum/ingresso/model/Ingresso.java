package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.descontos.Desconto;


/**
 * Created by Tiago Tiede on 13/01/18.
 */
public class Ingresso {

	private Sessao sessao;

	private BigDecimal preco;

	public Ingresso() {
	}
	
	public Ingresso(Sessao sessao, Desconto tiDesconto) {
		this.sessao = sessao;
		this.preco = tiDesconto.aplicarDescontoSobre(sessao.getPreco());
	}
	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
}