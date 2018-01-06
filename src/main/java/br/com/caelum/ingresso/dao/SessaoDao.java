package br.com.caelum.ingresso.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.model.Sessao;

/**
 * Created by nando on 03/03/17.
 */
@Repository
public class SessaoDao {
	@PersistenceContext
	private EntityManager manager;
	private void save( Sessao sessao) {
		manager.persist(sessao);
	}
}
