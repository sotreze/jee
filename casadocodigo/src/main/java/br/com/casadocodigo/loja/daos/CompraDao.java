package br.com.casadocodigo.loja.daos;

import java.io.Serializable;
//import java.util.List;
import java.util.List;

import javax.ejb.Stateful;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.Compra;
//import br.com.casadocodigo.loja.models.Livro;
import br.com.casadocodigo.loja.security.CurrentUser;

@Stateful // teste
public class CompraDao implements Serializable {

	private static final long serialVersionUID = 1L;

	@PersistenceContext
	private EntityManager manager;

	@Inject
	private CurrentUser currentUser;
		
	public CurrentUser get() {
		return currentUser;
	}
	
	/*
	 * public Livro getLivro() { return getLivro(); }
	 * 
	 * public Livro buscarPorId(Integer id) { return manager.find(Livro.class, id);
	 * }
	 */
	
	public List<Compra> listar() {
		String usuarioLogado = currentUser.get().getEmail();
		System.out.println("Usuario Logado: " + usuarioLogado);
		return manager.createQuery("select c from Compra c where c.usuarioLogado = :usuarioLogado", Compra.class)
				.setParameter("usuarioLogado", usuarioLogado)
				.getResultList();
	}
	
	public void salvar(Compra compra) {
		manager.persist(compra);
	}

	public Compra buscarPorUuid(String uuid) {
		return manager.createQuery("select c from Compra c where c.uuid = :uuid", Compra.class)
				.setParameter("uuid", uuid).getSingleResult();
	}

	// teste

	/*
	 * public Compra buscarPorUsuarioLogado(String usuarioLogado) { return
	 * manager.find(Compra.class, usuarioLogado); }
	 */
}
