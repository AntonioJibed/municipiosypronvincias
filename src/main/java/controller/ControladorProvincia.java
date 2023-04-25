package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Provincia;

public class ControladorProvincia {
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("provinciasymunicipios");
	
	
	/**
	 * 
	 * 
	 */
	public static List<Provincia> findAllProvincias(){
		EntityManager em = entityManagerFactory.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM provincia", Provincia.class);

		
		List<Provincia> l = (List<Provincia>) q.getResultList();
		
		em.close();
		return l;
		
	}
	
	/**
	 * 
	 * 
	 */
	public static Provincia findById(int id){
		EntityManager em = entityManagerFactory.createEntityManager();
		
		Provincia p = (Provincia) em.find(Provincia.class, id);
		
		em.close();
		return p;
		
	}
	


}
