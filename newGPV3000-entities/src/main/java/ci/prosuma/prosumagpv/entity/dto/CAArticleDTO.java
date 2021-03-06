package ci.prosuma.prosumagpv.entity.dto;

import java.math.BigDecimal;

public class CAArticleDTO {

	private String codeSecteur;

	private String codeRayon;

	private String codeFamille;

	private String codeSousFamille;

	private String codeArticle;

	private String libArticle;

	private BigDecimal prixFacturation;

	private BigDecimal prixVente;

	private BigDecimal marge;

	private Float pourcentage;

	public String getCodeSecteur() {
		return codeSecteur;
	}

	public void setCodeSecteur(String codeSecteur) {
		this.codeSecteur = codeSecteur;
	}

	public String getCodeRayon() {
		return codeRayon;
	}

	public void setCodeRayon(String codeRayon) {
		this.codeRayon = codeRayon;
	}

	public String getCodeFamille() {
		return codeFamille;
	}

	public void setCodeFamille(String codeFamille) {
		this.codeFamille = codeFamille;
	}

	public String getCodeSousFamille() {
		return codeSousFamille;
	}

	public void setCodeSousFamille(String codeSousFamille) {
		this.codeSousFamille = codeSousFamille;
	}

	public String getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(String codeArticle) {
		this.codeArticle = codeArticle;
	}

	public String getLibArticle() {
		return libArticle;
	}

	public void setLibArticle(String libArticle) {
		this.libArticle = libArticle;
	}

	public BigDecimal getPrixFacturation() {
		return prixFacturation;
	}

	public void setPrixFacturation(BigDecimal prixFacturation) {
		this.prixFacturation = prixFacturation;
	}

	public BigDecimal getPrixVente() {
		return prixVente;
	}

	public void setPrixVente(BigDecimal prixVente) {
		this.prixVente = prixVente;
	}

	public BigDecimal getMarge() {
		return marge;
	}

	public void setMarge(BigDecimal marge) {
		this.marge = marge;
	}

	public Float getPourcentage() {
		return pourcentage;
	}

	public void setPourcentage(Float pourcentage) {
		this.pourcentage = pourcentage;
	}
	
	
}
