/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.dto;

import com.nov.hotel.entities.TChambre;
import com.nov.hotel.entities.TClient;
import java.util.Date;

/**
 *
 * @author marfu
 */
public class OccupantDto {
    
    private TClient client;
    private DetailsChambreOccupantsDto chambre;
    private Date dateDeb;
    private Date dateFin;

    public TClient getClient() {
        return client;
    }

    public void setClient(TClient client) {
        this.client = client;
    }

    public DetailsChambreOccupantsDto getChambre() {
        return chambre;
    }

    public void setChambre(DetailsChambreOccupantsDto chambre) {
        this.chambre = chambre;
    }

    public Date getDateDeb() {
        return dateDeb;
    }

    public void setDateDeb(Date dateDeb) {
        this.dateDeb = dateDeb;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

  
   
    
    
    
}
