/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.services;

import com.nov.hotel.dao.TCompteClientDao;
import com.nov.hotel.entities.TCompteClient;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author marfu
 */

@Stateless
public class TCompteClientService {
    @EJB
    private TCompteClientDao tCompteClientDao;
    
    
     public TCompteClient creerTCompteClient(TCompteClient c) {
        
      return tCompteClientDao.createOrUpdateTCompteClient(c);
       
    }
     
     public TCompteClient findTCompteClientByClient(long id) {
        
      return tCompteClientDao.findTCompteClientByClient(id);
       
    }
}