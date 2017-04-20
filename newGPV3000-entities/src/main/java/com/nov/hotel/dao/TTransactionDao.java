/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dao;

import com.nov.hotel.entities.TCompteClient;
import com.nov.hotel.entities.TTransaction;
import java.util.List;








/**
 *
 * @author manukey
 */
public interface TTransactionDao extends GenericDao<TTransaction>{
 public TTransaction createOrUpdateTTransaction(TTransaction u);
    
    public TTransaction getTTransaction(long id);
    
     public List <TTransaction> findTTransationByCClient(TCompteClient u);
}
