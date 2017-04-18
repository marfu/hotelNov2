/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dao;



import com.nov.hotel.entities.TDeposite;
import com.nov.hotel.entities.TFacture;
import java.util.List;





/**
 *
 * @author manukey
 */
public interface TDepositeDao extends GenericDao<TDeposite>{
  
    public Double findDepositeByUser(long id);
   
    public List <TDeposite> findAllDepositeByUser(long id);
    
    public TDeposite createOrUpdateDeposite(TDeposite u);
    
    public  TDeposite getDeposite(long id);
    
    public List<TDeposite> listTdeposite();
   
}
