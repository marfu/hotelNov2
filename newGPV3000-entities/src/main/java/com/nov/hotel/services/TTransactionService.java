/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.services;

import com.nov.hotel.dao.TCompteClientDao;
import com.nov.hotel.dao.TTransactionDao;
import com.nov.hotel.entities.TCompteClient;
import com.nov.hotel.entities.TTransaction;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author marfu
 */

@Stateless
public class TTransactionService {
    @EJB
    private TTransactionDao ttransactionDao;
    
    
     public TTransaction creerTTransaction(TTransaction c) {
        
      return ttransactionDao.createOrUpdateTTransaction(c);
       
    }
     
     public List<TTransaction> findTTransationByCClient(TCompteClient cc) {
        
      return ttransactionDao.findTTransationByCClient(cc);
       
    }
}