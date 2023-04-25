package controller;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Municipio;
import model.Provincia;

public class ControladorMunicipio {
private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("provinciasymunicipios");
	
	
	
	
	public static List<Municipio> findLikeNombre(String descripcion){
		EntityManager em = entityManagerFactory.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM municipio where nombre like ?", Municipio.class);
		q.setParameter(1, "%" + descripcion + "%");
		
		List<Municipio> l = (List<Municipio>) q.getResultList();
		
		em.close();
		return l;
		
	}
	
	
	/**
	 * 
	 * 
	 */
	public static Municipio findById(int id){
		EntityManager em = entityManagerFactory.createEntityManager();
		
		Municipio l = (Municipio) em.find(Municipio.class, id);
		
		em.close();
		return l;
		
	}
	/**
	 * 
	 * 
	 */
	public static List<Municipio> findProvincia(String provincia){
		EntityManager em = entityManagerFactory.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM municipio where idProvincia like ?", Municipio.class);
		q.setParameter(1, provincia);
		
		List<Municipio> l = (List<Municipio>) q.getResultList();
		
		em.close();
		return l;
		
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public static void guardar(Municipio m) {
		EntityManager em = entityManagerFactory.createEntityManager();
		
		em.getTransaction().begin();
		em.merge(m);
		em.getTransaction().commit();
		em.close();
	}
}
