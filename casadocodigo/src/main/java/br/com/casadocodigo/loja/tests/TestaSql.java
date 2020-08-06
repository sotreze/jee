package br.com.casadocodigo.loja.tests;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.casadocodigo.loja.models.Compra;

public class TestaSql {
	public static void main(String[] args) {	
	
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("casadocodigo-dev");
		EntityManager em = emf.createEntityManager();
		
		Compra compraRealizada = em.find(Compra.class, 1L);
		
		em.getTransaction().begin();
		
		em.createQuery("select * from Compra c where c.usuario_id = :usuario_id");
		
		compraRealizada.getItens();
		
		System.out.println(compraRealizada);

	}


}
