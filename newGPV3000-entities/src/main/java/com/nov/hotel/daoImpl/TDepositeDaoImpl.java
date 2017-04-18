/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;

import com.nov.hotel.dao.TDepositeDao;
import com.nov.hotel.entities.TDeposite;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;

/**
 *
 * @author manukey
 */
@Stateless
public class TDepositeDaoImpl extends GenericDaoImpl<TDeposite> implements TDepositeDao {

    public TDepositeDaoImpl() {
    }

    public TDepositeDaoImpl(Class<TDeposite> entityClass) {
        super(entityClass);
    }

    @Override
    public Double findDepositeByUser(long id) {
        Double results = 0.0;
        try {
            results = (Double) em.createQuery("SELECT d.montant FROM TDeposite d where d.statut= true and d.client.cliId= " + id).getSingleResult();
        } catch (NoResultException nre) {
        }

        return results;
    }

    @Override
    public TDeposite createOrUpdateDeposite(TDeposite u) {

        TDeposite temp = getDeposite(u.getDepId());
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
    public TDeposite getDeposite(long id) {
        try {
            TDeposite u = em.find(TDeposite.class, id);
//			if (u.getPvt() != null)
//				u.getPvt().size();
            return u;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<TDeposite> listTdeposite() {
        List<TDeposite> results = new ArrayList<>();
        try {
            results = em.createQuery("SELECT d FROM TDeposite d order by d.dateCreate desc ").getResultList();
        } catch (NoResultException nre) {
        }

        return results;

    }

    @Override
    public List<TDeposite> findAllDepositeByUser(long id) {
        List<TDeposite> results = null;
        try {
            results = em.createQuery("SELECT d FROM TDeposite d where d.statut=true and d.client.cliId= " + id).getResultList();
        } catch (NoResultException nre) {
        }
        return results;
    }

}
