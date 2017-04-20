/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.bean;

import com.nov.hotel.entities.ClientEnum;
import com.nov.hotel.entities.EtatTransactionEnum;
import com.nov.hotel.entities.TClient;
import com.nov.hotel.entities.TCompteClient;
import com.nov.hotel.entities.TTransaction;
import com.nov.hotel.services.TClientService;
import com.nov.hotel.services.TCompteClientService;
import com.nov.hotel.services.TTransactionService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author marfu
 */
@Named(value = "tClientBean")
@SessionScoped
public class TClientBean implements Serializable {
    
    @EJB
    private TClientService tClientService;
    @EJB
    private TCompteClientService tcompteClientService;
    @EJB
    private TTransactionService ttransactionService;
    
    private String nom;
    private String type;
    private String typeTransac;
    private TClient client;
    private boolean testCat = true;
    
    private List<TClient> listClient;
    private List<TClient> listClientsearch;
    private List<TTransaction> listTransaction;
    private TCompteClient compteClient;
    private TTransaction transaction=new TTransaction();
    
    public List<TClient> getListClient() {
        return listClient = tClientService.listClient();
    }
    
    public void setListClient(List<TClient> listClient) {
        this.listClient = listClient;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public TClient getClient() {
        return client;
    }
    
    public void setClient(TClient client) {
        this.client = client;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public List<TClient> getListClientsearch() {
        listClientsearch = tClientService.searchListTclient(type, nom);
        return listClientsearch;
    }
    
    public void setListClientsearch(List<TClient> listClientsearch) {
        this.listClientsearch = listClientsearch;
    }
    
    public boolean isTestCat() {
        return testCat;
    }
    
    public void setTestCat(boolean testCat) {
        this.testCat = testCat;
    }
    
    public TCompteClient getCompteClient() {
        return compteClient;
    }
    
    public void setCompteClient(TCompteClient compteClient) {
        this.compteClient = compteClient;
    }
    
    public TTransaction getTransaction() {
        return transaction;
    }
    
    public void setTransaction(TTransaction transaction) {
        this.transaction = transaction;
    }
    
    public String getTypeTransac() {
        return typeTransac;
    }
    
    
    public void setTypeTransac(String typeTransac) {
        this.typeTransac = typeTransac;
    }

    public List<TTransaction> getListTransaction() {
        listTransaction=ttransactionService.findTTransationByCClient(compteClient);
        return listTransaction;
    }

    public void setListTransaction(List<TTransaction> listTransaction) {
        this.listTransaction = listTransaction;
    }
    
    
    
    
    
    
    
    
    public void changeCategorie() {
        
        if ((type).equals("INDIVIDU")) {
            testCat = true;
            
        } else {
            testCat = false;
            
        }
        listClientsearch = tClientService.searchListTclient(type, nom);
        
    }
    
    public void detailCompteClient() {
        
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("clientID");
        
        long idch = Long.valueOf(id);
        if (id != null && !id.trim().equals("")) {
            
            compteClient = tcompteClientService.findTCompteClientByClient(idch);
            
            if ((compteClient.getClient().getTypeClient()).equals(ClientEnum.INDIVIDU)) {
                testCat = true;
                
            } else {
                testCat = false;
                
            }
            
        } else {
            
            System.out.println("xxxxx");
        }
    }
    
    public void creerTransaction() {
        Date today = new Date();
        double montantsolde = 0;
        double montantoperation = 0;
        
        transaction.setCompteClient(compteClient);
        transaction.setDateTransaction(today);
        System.out.println("manukey             "+ transaction.toString());
        montantsolde = compteClient.getSolde();
        montantoperation = transaction.getMontantTransaction();
        System.out.println("montantoperation             "+ montantoperation);
        System.out.println("montantsolde             "+ montantsolde);
        if (typeTransac.equals(EtatTransactionEnum.DEPOT.toString())) {
            transaction.setEtat(EtatTransactionEnum.DEPOT);
            montantsolde = montantsolde + montantoperation;
            
        } else {
            
            transaction.setEtat(EtatTransactionEnum.RETRAIT);
            montantsolde = montantsolde - montantoperation;
        }
        System.out.println("don            "+ transaction.toString());
        System.out.println("operation 2             ");
        System.out.println("montantoperation             "+ montantoperation);
        System.out.println("montantsolde             "+ montantsolde);
        //insertion de la transaction
        ttransactionService.creerTTransaction(transaction);

        //mis a jour du compte client
        compteClient.setSolde(montantsolde);
        tcompteClientService.creerTCompteClient(compteClient);
         FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage("Success", "Votre transaction a été prise en compte"));
            clearEntity();
        
    }
    
      public void clearEntity() {
          transaction=new TTransaction();
      }
}
