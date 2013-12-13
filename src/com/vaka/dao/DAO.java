package com.vaka.dao;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.vaka.model.User;
import com.vaka.rest.ServiceFacade;

public class DAO<T> {
	@PersistenceContext(unitName = "test")
	public EntityManager em = Persistence.createEntityManagerFactory("test").createEntityManager();
	static Logger l = Logger.getLogger(DAO.class.getName());
	private Class classe;

	public DAO(Class classe) {
		this.classe = classe;
		//l.info(DAO.class + " <|-- " + classe.getName() + " : OK "); 
	}

	public T insert(T entidade) {
		em.getTransaction().begin();
		em.persist(entidade);		
		em.getTransaction().commit();
		//l.info( classe.getName() + " Insert (" + classe.getName() + " entity)"); 
		return entidade;
	}

	public T update(T entidade) {
		em.getTransaction().begin();
		em.merge(entidade);
		em.getTransaction().commit();
		//l.info(classe.getName() + " update( " + classe.getName() + " entity) ");
		return entidade;
	}

	public T get(int id) {
		//l.info( classe.getName() + " get(int id = " + id + ") "); 
		return (T) em.find(classe, id);
	}

	public void remove(int id) {
		T entidade = get(id);
		if (entidade != null) {
			em.getTransaction().begin();
			em.remove(entidade);
			em.getTransaction().commit();
			//l.info("remove(int id = " + id + ") => " + classe.getName()); 
		}
	}

	public List<T> list() {
		//l.info("List<"+ classe.getName()+"> list()"); 
		return em.createQuery("SELECT e FROM " + classe.getSimpleName() + " e").getResultList();
	}
		
	public List<T> search(HashMap map)
	{
		Query q = em.createQuery("SELECT e FROM " + classe.getSimpleName() + " e WHERE e. = : AND e. = :");
		return null;		
	}
	
	
}
