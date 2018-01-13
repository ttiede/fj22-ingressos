package br.com.caelum.ingresso.model.descontos.impl;

import java.math.BigDecimal;

import br.com.caelum.ingresso.model.descontos.Desconto;

public class DescontoParaBancos implements Desconto{

	@Override
	public BigDecimal aplicarDescontoSobre(BigDecimal precoOriginal) {
		return precoOriginal.subtract(trintaPorCentoSobre(precoOriginal));
	}
	
	private BigDecimal trintaPorCentoSobre(BigDecimal precoOriginal){
		return precoOriginal.multiply(new BigDecimal("0.3"));
	}
}
