/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.daoImpl;

import com.nov.hotel.dao.TTransactionDao;
import com.nov.hotel.entities.TCompteClient;
import com.nov.hotel.entities.TTransaction;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author manukey
 */
@Stateless
public class TTransactionDaoImpl extends GenericDaoImpl<TTransaction> implements TTransactionDao {

    public TTransactionDaoImpl() {
    }

    public TTransactionDaoImpl(Class<TTransaction> entityClass) {
        super(entityClass);
    }

    @Override
    public TTransaction createOrUpdateTTransaction(TTransaction u) {
        TTransaction temp = getTTransaction(u.getId());
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
    public TTransaction getTTransaction(long id) {
        try {
            TTransaction u = em.find(TTransaction.class, id);
//			if (u.getPvt() != null)
//				u.getPvt().size();
            return u;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<TTransaction> findTTransationByCClient(TCompteClient u) {
        List<TTransaction> result = null;

        try {

            Query q = em.createQuery("SELECT t FROM TTransaction t WHERE t.compteClient = :compte ORDER BY t.dateTransaction DESC");
            q.setParameter("compte", u);
            result = q.getResultList();
        } catch (NoResultException e) {
            System.out.println(e.getMessage());
        }

        return result;
    }

}
