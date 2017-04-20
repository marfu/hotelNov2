/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;


import com.nov.hotel.dao.TCompteClientDao;
import com.nov.hotel.entities.TCompteClient;
import javax.ejb.Stateless;

/**
 *
 * @author manukey
 */
@Stateless
public class TCompteClientDaoImpl extends GenericDaoImpl<TCompteClient> implements TCompteClientDao {

    public TCompteClientDaoImpl() {
    }

    public TCompteClientDaoImpl(Class<TCompteClient> entityClass) {
        super(entityClass);
    }
     @Override
    public TCompteClient createOrUpdateTCompteClient(TCompteClient u) {
        TCompteClient temp = getTCompteClient(u.getId());
        if (temp != null) {
            em.merge(u);
            em.flush();
            return u;
        } else {
            em.persist(u);
            return u;
        }
    }

    @Override
    public TCompteClient getTCompteClient(long id) {
        try {
            TCompteClient u = em.find(TCompteClient.class, id);
//			if (u.getPvt() != null)
//				u.getPvt().size();
            return u;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public TCompteClient findTCompteClientByClient(long id) {
        TCompteClient results = new TCompteClient();
        try {
            results = (TCompteClient) em.createQuery("SELECT u FROM TCompteClient u where u.client.cliId=:code").setParameter("code", id).getSingleResult();

            return results;
        } catch (Exception e) {
            return null;
        }
    }

 
}
