/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.nov.hotel.bean;

import com.nov.hotel.dto.FactureDto;
import com.nov.hotel.entities.EtatFactureEnum;
import com.nov.hotel.entities.TChambre;
import com.nov.hotel.entities.TCompteClient;
import com.nov.hotel.entities.TDetailFacture;
import com.nov.hotel.entities.TFacture;
import com.nov.hotel.entities.TModePaiment;
import com.nov.hotel.entities.TTarif;
import com.nov.hotel.services.TCategorieChambreService;
import com.nov.hotel.services.TChambreService;
import com.nov.hotel.services.TCompteClientService;
import com.nov.hotel.services.TDetailFactureService;
import com.nov.hotel.services.TFactureService;
import com.nov.hotel.services.TModePaiementService;
import com.nov.hotel.services.TOffreTarifaireService;
import com.nov.hotel.services.TTarifService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.inject.Named;

/**
 *
 * @author manukey
 */
@Named(value = "facturedtoBean")
@SessionScoped
public class TfactureDtoBean implements Serializable {

    @EJB
    private TFactureService tfactureService;

    @EJB
    private TDetailFactureService tDetailFactureService;
    @EJB
    private TCompteClientService tcompteClientService;
      @EJB
    private TModePaiementService tModePaiementService;

    private TFacture tfacture = new TFacture();
    private TCompteClient compteclient= new TCompteClient();
    private TDetailFacture tdetailFacture = new TDetailFacture();
    private FactureDto facturedo = new FactureDto();
    private FactureDto facturedoparam = new FactureDto();
    private List<TFacture> listTfacture = new ArrayList<>();
    private List<TDetailFacture> listTdetailFacture = new ArrayList<>();
    private List<FactureDto> listfatureDto = new ArrayList<>();
   private EtatFactureEnum etatfacture=EtatFactureEnum.CLOTURER;

    private long idcategorie;
    private long idoffretarifaire;
     private long modePaiementId;

    public TFacture getTfacture() {
        return tfacture;
    }

    public void setTfacture(TFacture tfacture) {
        this.tfacture = tfacture;
    }

    public TDetailFacture getTdetailFacture() {
        return tdetailFacture;
    }

    public void setTdetailFacture(TDetailFacture tdetailFacture) {
        this.tdetailFacture = tdetailFacture;
    }

    public FactureDto getFacturedo() {
        return facturedo;
    }

    public void setFacturedo(FactureDto facturedo) {
        this.facturedo = facturedo;
    }

    public List<TFacture> getListTfacture() {
        listTfacture = tfactureService.listTFacture();
        return listTfacture;
    }

    public void setListTfacture(List<TFacture> listTfacture) {
        this.listTfacture = listTfacture;
    }

    public List<TDetailFacture> getListTdetailFacture() {
        return listTdetailFacture;
    }

    public void setListTdetailFacture(List<TDetailFacture> listTdetailFacture) {
        this.listTdetailFacture = listTdetailFacture;
    }

    public List<FactureDto> getListfatureDto() {
        remplirlist();
        return listfatureDto;
    }

    public void setListfatureDto(List<FactureDto> listfatureDto) {
        this.listfatureDto = listfatureDto;
    }

    public long getIdcategorie() {
        return idcategorie;
    }

    public void setIdcategorie(long idcategorie) {
        this.idcategorie = idcategorie;
    }

    public long getIdoffretarifaire() {
        return idoffretarifaire;
    }

    public void setIdoffretarifaire(long idoffretarifaire) {
        this.idoffretarifaire = idoffretarifaire;
    }

    public TCompteClient getCompteclient() {
        return compteclient;
    }

    public void setCompteclient(TCompteClient compteclient) {
        this.compteclient = compteclient;
    }

    public FactureDto getFacturedoparam() {
        return facturedoparam;
    }

    public void setFacturedoparam(FactureDto facturedoparam) {
        this.facturedoparam = facturedoparam;
    }
    
    public long getModePaiementId() {
        return modePaiementId;
    }

    public void setModePaiementId(long modePaiementId) {
        this.modePaiementId = modePaiementId;
    }

    public EtatFactureEnum getEtatfacture() {
        return etatfacture;
    }

    public void setEtatfacture(EtatFactureEnum etatfacture) {
        this.etatfacture = etatfacture;
    }
    
    
    public void changeStatut() {
        
//        if ((type).equals("INDIVIDU")) {
//            testCat = true;
//            
//        } else {
//            testCat = false;
//            
//        }
//        listClientsearch = tClientService.searchListTclient(type, nom);
        
    }
    

    public void remplirlist() {
        listfatureDto.clear();
        listTfacture = tfactureService.listTFacture();
       // System.out.println(listTfacture.size());
        for (TFacture fact : listTfacture) {
            FactureDto vfact = new FactureDto();
            compteclient= new TCompteClient();
            vfact.setFactDateCreate(fact.getFactDateCreate());
            vfact.setFactDateModif(fact.getFactDateModif());
            vfact.setNumFacture(fact.getNumFacture());
            vfact.setRemise(fact.getRemise());
            vfact.setStatuId(fact.getStatuId());
            vfact.setUserCreate(fact.getUserCreate());
            vfact.setUserModif(fact.getUserModif());
            vfact.setFactId(fact.getFactId());
            vfact.setClient(fact.getClient());
            vfact.setMontantregle(fact.getMontantRegle());
            vfact.setMontantrendu(fact.getMontantrendu());
            compteclient=tcompteClientService.findTCompteClientByClient(fact.getClient().getCliId());
            double vprix = 0;
            long vqte = 0;
            double tauxf=0;
            double montanttaux=0;
            double montantaregler=0;
            double soldecomptclient=0;
            soldecomptclient=compteclient.getSolde();
            tauxf=fact.getRemise().getRemiseTaux();
            tauxf=tauxf/100;
            
            listTdetailFacture = tDetailFactureService.listTDetailFactureByFacture(vfact.getFactId());
            listTdetailFacture.size();
            for (TDetailFacture detfac : listTdetailFacture) {
                //calcul du montant total de la facture avant reduction
                detfac.toString();
                vprix = +detfac.getDfactPrix();
                vqte = +detfac.getDfactQte();
            }
            montanttaux=tauxf*vprix;
            //calcul du montant total de la facture apres les reduction
            vprix=vprix-montanttaux-soldecomptclient;
            vfact.setMontantTaux(montanttaux);
            vfact.setSoldeCompteClient(soldecomptclient);
            vfact.setPrix(vprix);
            vfact.setQte(vqte);

           // System.out.println(vfact.toString());
            listfatureDto.add(vfact);

        }

    }

    // fonction et methode
    public String creerUpdateTTarif() {
        //   Date today = new Date();
        return "";
    }

    public void detailFacturef() {
        System.out.println("jdjdejfffrfrf");
        String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("factureID");

        long idch = Long.valueOf(id);
        if (id != null && !id.trim().equals("")) {

            listTdetailFacture = tDetailFactureService.listTDetailFactureByFacture(idch);

            for (TDetailFacture tdet : listTdetailFacture) {
                System.out.println("aaaaaaa" + tdet.toString());
            }

        } else {

            System.out.println("xxxxx");
        }
    }

    
      public void EncaisserFacture(){
           
           TFacture tfacture=new TFacture();
           TModePaiment modPaie = tModePaiementService.findTModePaimentById(modePaiementId);
           tfacture=tfactureService.findByIDTFacture(facturedoparam.getFactId());
           tfacture.setMontantRegle(facturedoparam.getMontantregle());
           double mntregle=0;
           double mnttotal=0;
           double mntrendu=0;
           mntregle=facturedoparam.getMontantregle();
           mnttotal=facturedoparam.getPrix();
           mntrendu=mntregle-mnttotal;
           tfacture.setMontantrendu(mntrendu);
           tfacture.setStatuId(EtatFactureEnum.CLOTURER);
           tfacture.setModePaiement(modPaie);
           tfacture.setNumCheque(facturedoparam.getNumchecque());
           System.out.println("tfacture.toString()        "+ tfacture.toString());
          tfactureService.CreerorupdateTFacture(tfacture);
          FacesContext context = FacesContext.getCurrentInstance();

            context.addMessage(null, new FacesMessage("Success", "Votre transaction a été prise en compte"));
          facturedoparam=new FactureDto();
      }
    
    
}
