package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.casadocodigo.loja.models.SystemRole;

public class SystemRoleDao {

    @PersistenceContext
    private EntityManager manager;

    public List<SystemRole> listar(){
        return manager.createQuery("select sr from SystemRole sr", SystemRole.class)
            .getResultList();        
    }

	
}
