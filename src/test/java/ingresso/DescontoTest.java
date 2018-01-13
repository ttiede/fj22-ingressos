package ingresso;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.descontos.impl.DescontoParaBancos;
import br.com.caelum.ingresso.model.descontos.impl.DescontoParaEstudantes;
import br.com.caelum.ingresso.model.descontos.impl.SemDesconto;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessao;

public class DescontoTest {

	private Filme rogueOne;
	private Sala salda3d;
	private Sessao sessaoDasDez;

	
	@Before
	public void preparaSessoes(){
		this.rogueOne = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12"));
		this.salda3d = new Sala("Salda 3D", new BigDecimal("20.5"));
		this.sessaoDasDez = new Sessao(LocalTime.parse("10:00:00"), rogueOne, salda3d);		
	}
	
	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {	
		Ingresso ingresso = new Ingresso(sessaoDasDez, new SemDesconto());
		Assert.assertEquals(new BigDecimal("32.5"), ingresso.getPreco());
	}

	@Test
	public void deveConcederDescontoDe30PorcentoParaIngressoDeClientesDeBanco() {	
		Ingresso ingresso = new Ingresso(sessaoDasDez, new DescontoParaBancos());
		Assert.assertEquals(new BigDecimal("22.75"), ingresso.getPreco());
	}

	@Test
	public void deveConcederDescontoDe50PorcentoParaIngressoDeClientesEstudantes() {	
		Ingresso ingresso = new Ingresso(sessaoDasDez, new DescontoParaEstudantes());
		Assert.assertEquals(new BigDecimal("16.25"), ingresso.getPreco());
	}
}
