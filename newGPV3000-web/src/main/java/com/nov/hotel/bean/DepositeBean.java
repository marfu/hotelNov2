/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.bean;

import ci.prosumagpv.web.bean.security.SecurityBean;
import com.nov.hotel.entities.TClient;
import com.nov.hotel.entities.TDeposite;
import com.nov.hotel.services.TClientService;
import com.nov.hotel.services.TDepositeService;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author marfu
 */
@Named(value = "depositeBean")
@SessionScoped
public class DepositeBean implements Serializable {

    @EJB
    private TDepositeService tDepositeService;
    @EJB
    private TClientService tClientService;

    @ManagedProperty(value = "#{securityBean}")
    private SecurityBean securityBean;

    private TDeposite deposite = new TDeposite();
    private List<TDeposite> listTdeposite;
    private long clientId;
    private String numPiece;

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public List<TDeposite> getListTdeposite() {
        return listTdeposite = tDepositeService.listTdeposite();
    }

    public void setListTdeposite(List<TDeposite> listTdeposite) {
        this.listTdeposite = listTdeposite;
    }

    public TDeposite getDeposite() {
        return deposite;
    }

    public void setDeposite(TDeposite deposite) {
        this.deposite = deposite;
    }

    public SecurityBean getSecurityBean() {
        if (securityBean == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            securityBean = (SecurityBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(context.getELContext(), null, "securityBean");
        }
        return securityBean;
    }

    public void setSecurityBean(SecurityBean securityBean) {
        this.securityBean = securityBean;
    }

    public String getNumPiece() {
        return numPiece;
    }

    public void setNumPiece(String numPiece) {
        this.numPiece = numPiece;
    }

    public String creer() {
        //Date today = new Date();
        String userName = getSecurityBean().getUserName();
        //  TClient clientRech = tClientService.findByNumPiece(numPiece);
        TClient clientRech = tClientService.findById(clientId);
        System.out.println(clientRech.getCliId());

        if (clientRech == null) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur!", "Le client specifié n'existe pas !"));

             return "";
        } else {

            List<TDeposite> listTDepos = tDepositeService.listTdepositeByUser(clientId);
            for (TDeposite str : listTDepos) {
                str.setStatut(false);
                System.out.print("xxxxxxxxxxx");
                tDepositeService.CreerTDeposite(str);
            }

            deposite.setClient(clientRech);

            if (deposite.getDepId() == 0) {
                deposite.setUserCreate(userName);
                deposite.setDateCreate(new Date());
                deposite.setStatut(true);
            } else {
                deposite.setUserModif(userName);
                deposite.setDateModif(new Date());

            }

            tDepositeService.CreerTDeposite(deposite);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès!", "Votre traitement a été pris en compte !"));

            clearEntity();
             return "success";
        }
    }

    public void clearEntity() {
        clientId = 0;
        numPiece = "";
        deposite = new TDeposite();

    }

}
