/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dao;


import com.nov.hotel.entities.TChambre;
import com.nov.hotel.entities.TFacture;
import java.util.List;





/**
 *
 * @author manukey
 */
public interface TFactureDao extends GenericDao<TFacture>{
    public TFacture findTFactureByChambre(TChambre tchambre);
    
     public long findLastTFacture();
     
      public List<TFacture> listTFacture();
      public TFacture createOrUpdateTFacture(TFacture u);

    public TFacture getTFacture(long id);
   
}
