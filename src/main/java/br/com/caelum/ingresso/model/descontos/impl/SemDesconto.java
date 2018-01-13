package br.com.caelum.ingresso.model.descontos.impl;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.descontos.Desconto;

public class SemDesconto implements Desconto{

	@Override
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
		return precoOriginal;
	}
}
