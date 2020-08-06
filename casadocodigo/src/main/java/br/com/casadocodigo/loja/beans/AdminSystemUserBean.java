package br.com.casadocodigo.loja.beans;



import java.io.IOException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.daos.SystemRoleDao;
import br.com.casadocodigo.loja.daos.SystemUserDao;
import br.com.casadocodigo.loja.models.SystemRole;
import br.com.casadocodigo.loja.models.SystemUser;

@Named
@RequestScoped
public class AdminSystemUserBean {
	
	private SystemUser systemUser = new SystemUser();
	
	@Inject
	private SystemUserDao systemUserDao;
	
	@Inject
	private SystemRoleDao systemRoleDao;
	
	@Inject
	private FacesContext context;
		
	@Transactional
	public String salvar() throws IOException {
		systemUserDao.salvar(systemUser);
		
		context.getExternalContext()
        .getFlash().setKeepMessages(true); // Aqui estamos ativando o FlashScope
		context
        .addMessage(null, new FacesMessage("Usuário cadastrado com sucesso!"));
		
		return "/admin/system/users?faces-redirect=true"; // E retornamos a página que o usuário irá sem o .xhtml
    }
	
	public List<SystemRole> getPermissoes(){
		return systemRoleDao.listar();
	}

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

}
