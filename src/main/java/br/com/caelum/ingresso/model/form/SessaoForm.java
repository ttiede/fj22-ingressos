package br.com.caelum.ingresso.model.form;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

/**
 * Created by nando on 03/03/17.
 */
public class SessaoForm {

	private Integer id;

	@NotNull
	private Integer salaId;

	@DateTimeFormat(pattern = "HH:mm")
	@NotNull
	private LocalTime horario;

	@NotNull
	private Integer filmeID;

	public Sessao toSessao(SalaDao salaDao, FilmeDao filmeDao) {
		Filme filme = filmeDao.findOne(filmeID);
		Sala sala = salaDao.findOne(salaId);
		Sessao sessao = new Sessao(this.horario, filme, sala);

		return sessao;
	}

}
