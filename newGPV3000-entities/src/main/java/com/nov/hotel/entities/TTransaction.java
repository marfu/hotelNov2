/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author marfu
 */
@Entity
public class TTransaction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTransaction;

    private double montantTransaction;
    private String motif;

    @Enumerated(EnumType.STRING)
    private EtatTransactionEnum etat;

    @OneToOne
    private TCompteClient compteClient;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(Date dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public double getMontantTransaction() {
        return montantTransaction;
    }

    public void setMontantTransaction(double montantTransaction) {
        this.montantTransaction = montantTransaction;
    }

    public EtatTransactionEnum getEtat() {
        return etat;
    }

    public void setEtat(EtatTransactionEnum etat) {
        this.etat = etat;
    }

    public TCompteClient getCompteClient() {
        return compteClient;
    }

    public void setCompteClient(TCompteClient compteClient) {
        this.compteClient = compteClient;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

   
    
    @Override
    public String toString() {
        return "TTransaction{" + "id=" + id + ", dateTransaction=" + dateTransaction + ", montantTransaction=" + montantTransaction + ", motif=" + motif + ", etat=" + etat + ", compteClient=" + compteClient + '}';
    }

    

}
