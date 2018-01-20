package ingresso;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;

public class SessaoTest {
	private Filme rogueOne;
	private Sala salda3d;
	private Sessao sessaoDasDez;
	private Sessao sessaoDasTreze;
	private Sessao sessaoDasDezoito;
	private Lugar lugar1;
	private Lugar lugar2;
	private Lugar lugar3;
	private Lugar lugar4;

	
	@Before
	public void preparaSessoes(){
		this.rogueOne = new Filme("Rogue One", Duration.ofMinutes(120), "SCI-FI", BigDecimal.ONE);
		this.salda3d = new Sala("Salda 3D", BigDecimal.TEN);
		this.sessaoDasDez = new Sessao(LocalTime.parse("10:00:00"), rogueOne, salda3d);
		this.lugar1 = new Lugar("A",1);
		this.lugar2 = new Lugar("A",2);
		this.lugar3 = new Lugar("A",3);
		this.lugar4 = new Lugar("A",4);

	}
	
	@Test
	public void naoDeveConcederDescontoParaIngressoNormal() {	
		Ingresso ingresso = new Ingresso(sessaoDasDez, TipoDeIngresso.INTEIRO, lugar1);
		Set<Ingresso> ingressos = Stream.of(ingresso).collect(Collectors.toSet()); 
		sessaoDasDez.setIngressos(ingressos);
		
		Assert.assertFalse(sessaoDasDez.isDisponivel(this.lugar1));
		Assert.assertTrue(sessaoDasDez.isDisponivel(this.lugar2));
		Assert.assertTrue(sessaoDasDez.isDisponivel(this.lugar3));
		Assert.assertTrue(sessaoDasDez.isDisponivel(this.lugar4));
		
	}

}
