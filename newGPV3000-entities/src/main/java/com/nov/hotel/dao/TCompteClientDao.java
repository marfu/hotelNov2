/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dao;

import com.nov.hotel.entities.TCompteClient;








/**
 *
 * @author manukey
 */
public interface TCompteClientDao extends GenericDao<TCompteClient>{
    public TCompteClient createOrUpdateTCompteClient(TCompteClient u);
    
    public TCompteClient getTCompteClient(long id);
    
     public TCompteClient findTCompteClientByClient(long id);
}
