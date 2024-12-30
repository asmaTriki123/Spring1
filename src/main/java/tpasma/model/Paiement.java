package tpasma.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



@Entity
public class Paiement {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private double montant;             // Montant du paiement
	    private String status;              // Statut du paiement ("En attente", "Payé", "Échoué")
	    private LocalDate datePaiement;     // Date du paiement

	    @ManyToOne
	    @JoinColumn(name = "etudiant_id", nullable = false)
	    private Etudiant etudiant;          // L'étudiant qui effectue le paiement

	    @ManyToOne
	    @JoinColumn(name = "cart_id", nullable = true)
	    private Cart cart;
	    
	    @ManyToOne
	    @JoinColumn(name = "chambre_id")
	    private Chambre chambre;

	    private LocalDate datePaiementPrevu; 
	    private double montantAPayer;
	    // La carte utilisée pour le paiement

	    // Getter et Setter pour id
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	     //Getter et Setter pour montant
	    public double getMontant() {
	        return montant;
	    }

	    public void setMontant(double montant) {
	        this.montant = montant;
	    }

	    // Getter et Setter pour status
	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }

	    // Getter et Setter pour datePaiement
	    public LocalDate getDatePaiement() {
	        return datePaiement;
	    }

	    public void setDatePaiement(LocalDate datePaiement) {
	        this.datePaiement = datePaiement;
	    }

	    // Getter et Setter pour etudiant
	    public Etudiant getEtudiant() {
	        return etudiant;
	    }

	    public void setEtudiant(Etudiant etudiant) {
	        this.etudiant = etudiant;
	    }

	    // Getter et Setter pour cart
	    public Cart getCart() {
	        return cart;
	    }

	    public void setCart(Cart cart) {
	        this.cart = cart;
	    }
	    
	    public double getMontantAPayer() {
	        return montantAPayer;
	    }

	    public void setMontantAPayer(double montantAPayer) {
	        this.montantAPayer = montantAPayer;
	    }
	    
	    public LocalDate getDatePaiementPrevu() {
	        return datePaiementPrevu;
	    }

	    public void setDatePaiementPrevu(LocalDate datePaiementPrevu) {
	        this.datePaiementPrevu = datePaiementPrevu;
	    }
	    public Chambre getChambre() {
	        return chambre;
	    }

	    public void setChambre(Chambre chambre) {
	        this.chambre = chambre;
	    }
}
