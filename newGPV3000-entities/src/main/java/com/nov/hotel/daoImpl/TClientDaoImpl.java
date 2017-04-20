/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;

import com.nov.hotel.entities.TClient;
import com.nov.hotel.dao.TClientDao;
import com.nov.hotel.entities.ClientEnum;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author manukey
 */
@Stateless
public class TClientDaoImpl extends GenericDaoImpl<TClient> implements TClientDao {

    public TClientDaoImpl() {
    }

    public TClientDaoImpl(Class<TClient> entityClass) {
        super(entityClass);
    }

    @Override
    public TClient createOrUpdateTClient(TClient u) {

        TClient temp = getUser(u.getCliId());
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
    public TClient getUser(long id) {
        try {
            TClient u = em.find(TClient.class, id);
//			if (u.getPvt() != null)
//				u.getPvt().size();
            return u;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public TClient listTclient(long id) {
        TClient results = (TClient) em.createQuery("SELECT cr FROM TClient cr where cr.cliId=" + id).getSingleResult();
        return results;
    }

    @Override
    public TClient getUserfindByNumPiece(String num) {
        TClient results = new TClient();
        try {
            results = (TClient) em.createQuery("SELECT u FROM TClient u where u.numeroPieceIdentite=:code").setParameter("code", num).getSingleResult();

            return results;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<TClient> searchListTclient(String type, String nom) {
        List<TClient> results = null;
            
        try {
            Query q;
            if (nom == null) {
                q = em.createQuery("SELECT c FROM TClient c where c.typeClient= :type ");
            } else {
                q = em.createQuery("SELECT c FROM TClient c where c.typeClient= :type AND :nomChampTable=:nom ");
                q.setParameter("nom", nom);
            }
            
            

            if (type == null) {
                q.setParameter("type", ClientEnum.INDIVIDU);
                if (nom != null) {
                    q.setParameter("nomChampTable", "c.cliNom");
                }
                
                
                

            } else {
                
                
                if (type.equals("INDIVIDU")) {
                    if (nom != null) {
                    q.setParameter("nomChampTable", "c.cliNom");
                }
                    q.setParameter("type", ClientEnum.INDIVIDU);
                  
                } else {
                    if(nom != null) {
                    q.setParameter("nomChampTable", "c.raisonSociale");
                }
                    q.setParameter("type", ClientEnum.GROUPE);
                   
                }
            }
            
           results =  q.getResultList();
        } catch (NoResultException nre) {
        }
        return results;
    }

}
