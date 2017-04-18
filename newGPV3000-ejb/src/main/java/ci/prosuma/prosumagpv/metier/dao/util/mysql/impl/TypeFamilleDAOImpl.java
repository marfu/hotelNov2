package ci.prosuma.prosumagpv.metier.dao.util.mysql.impl;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ci.prosuma.prosumagpv.entity.util.Famille;
import ci.prosuma.prosumagpv.metier.dao.util.mysql.ITypeFamilleDAO;

@Stateless
@Local(ITypeFamilleDAO.class)
public class TypeFamilleDAOImpl  implements ITypeFamilleDAO , Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	protected EntityManager em;

	
	@Override
	public Famille createOrUpdateFamille(Famille ei) {
		Famille temp = getFamille(ei.getCode());
		if (temp != null) {
			em.merge(ei);
			em.flush();
			return ei;
		} else {
			em.persist(ei);
			em.flush();
			return ei;
		}
	}

	@Override
	public Famille getFamille(String code) {
		try{
		return em.find(Famille.class, code);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public boolean removeFamille(Famille ei) {
		Famille a = getFamille(ei.getCode());
		if (a != null) {
			em.remove(a);
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Famille> getAllFamille() {
		Query query = em
				.createQuery("SELECT u  FROM Famille u ");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Famille> getAllFamilleBySecteurAndRayon(String codeRayon) {
		Query query = em.createQuery("SELECT u  FROM Famille u  WHERE u.code LIKE :codeRayon");
		query.setParameter("codeRayon", codeRayon+"%");
		return query.getResultList();
	}

}
