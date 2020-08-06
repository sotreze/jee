package br.com.casadocodigo.loja.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.casadocodigo.loja.daos.CompraDao;
import br.com.casadocodigo.loja.daos.LivroDao;
import br.com.casadocodigo.loja.models.Compra;
import br.com.casadocodigo.loja.models.Livro;

@Model
public class AdminListaComprasBean {
	
    @Inject
    private CompraDao dao;
    
    @Inject
    private LivroDao livroDao;
     
      
    private List<Compra> compras = new ArrayList<>();

	public List<Compra> getCompras() {
     this.compras = dao.listar();

     return compras;
    }
	
    private List<Livro> livros = new ArrayList<>();

    public List<Livro> getLivros() {
     this.livros = livroDao.listar();

     return livros;
    }
    
    //teste início
    // o método acima resolve
    //teste fim
	
}
