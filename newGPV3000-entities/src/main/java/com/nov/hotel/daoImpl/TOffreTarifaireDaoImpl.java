/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;

import com.nov.hotel.entities.TOffreTarifaire;
import com.nov.hotel.dao.TOffreTarifaireDao;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author manukey
 */
@Stateless
public class TOffreTarifaireDaoImpl extends GenericDaoImpl<TOffreTarifaire> implements TOffreTarifaireDao {

    public TOffreTarifaireDaoImpl() {
    }

    public TOffreTarifaireDaoImpl(Class<TOffreTarifaire> entityClass) {
        super(entityClass);
    }

    @Override
    public List<TOffreTarifaire> listOffreTarifaireByCategorie(long id) {

        

        List<TOffreTarifaire> results = em.createQuery("SELECT o FROM TTarif ta join ta.offre o where ta.chCategorie.catChambreId="+id).getResultList();

        return results;
    }

    @Override
    public TOffreTarifaire createOrUpdateTOffreTarifaire(TOffreTarifaire u) {
      TOffreTarifaire temp = getTOffreTarifaire(u.getOffreId());
        if (temp != null) {
            em.merge(u);
            em.flush();
            return u;
        } else {
            em.persist(u);
            return u;
        } }

    @Override
    public TOffreTarifaire getTOffreTarifaire(long id) {
      try {
			TOffreTarifaire u = em.find(TOffreTarifaire.class, id);

			return u;
		} catch (Exception e) {
                	return null;
		}
    }
    
    @Override
    public TOffreTarifaire findOffreTarifaireByLib(String libelle) {
     TOffreTarifaire result = null;
        try {
            Query q = em.createQuery("SELECT o FROM TOffreTarifaire o where o.offTitre= ?1");
            q.setParameter(1, libelle);

            result = (TOffreTarifaire) q.getSingleResult();

        } catch (NoResultException e) {
            // log.debug("No result forund for... ");
        }

        return result;}
}
