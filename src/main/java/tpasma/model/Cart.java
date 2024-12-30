package tpasma.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nomCarte;           // Nom de la carte
    private String numeroCarte;        // Numéro de la carte
    private double montant;            // Montant du paiement
    private LocalDate dateExpiration;  // Date d'expiration de la carte (optionnel)
 
    @OneToOne
    @JoinColumn(name = "etudiant_id", nullable = true, unique = true) // La colonne etudiant_id est unique
    @JsonBackReference
    // Cela évite la récursion infinie et fait en sorte que l'objet `Etudiant` ne soit pas inclus dans `Cart`
    private Etudiant etudiant;
    
    // Getter et Setter pour id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter et Setter pour nomCarte
    public String getNomCarte() {
        return nomCarte;
    }

    public void setNomCarte(String nomCarte) {
        this.nomCarte = nomCarte;
    }

    // Getter et Setter pour numeroCarte
    public String getNumeroCarte() {
        return numeroCarte;
    }

    public void setNumeroCarte(String numeroCarte) {
        this.numeroCarte = numeroCarte;
    }

    // Getter et Setter pour montant
    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    // Getter et Setter pour dateExpiration
    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

}