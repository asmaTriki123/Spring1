package tpasma.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import tpasma.model.Cart;
import tpasma.model.Chambre;
import tpasma.model.Etudiant;
import tpasma.model.Paiement;
import tpasma.repository.CartRepository;
import tpasma.repository.ChambreRepository;
import tpasma.repository.EtudiantRepository;
import tpasma.repository.PaiementRepository;
import java.util.Map;
import java.util.HashMap;

@Service
public class PaiementService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ChambreRepository chambreRepository;

    @Autowired
    private PaiementRepository paiementRepository;
/*
    @PutMapping("/paiement/{etudiantId}")
    public String effectuerPaiement(@PathVariable Long etudiantId) {
        // Récupérer l'étudiant
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable avec l'ID : " + etudiantId));

        // Récupérer la chambre associée à l'étudiant
        Chambre chambre = etudiant.getChambre();
        if (chambre == null) {
            throw new RuntimeException("Aucune chambre associée à l'étudiant avec l'ID : " + etudiantId);
        }

        // Récupérer le prix de la chambre
        double prixChambre = chambre.getPrix();

        // Récupérer la carte de l'étudiant
        Cart cart = cartRepository.findById(etudiant.getCart().getId())
                .orElseThrow(() -> new RuntimeException("Aucune carte associée à l'étudiant avec l'ID : " + etudiantId));

        double ancienSolde = cart.getMontant(); // Sauvegarder l'ancien solde de la carte

        if (ancienSolde < prixChambre) {
            return "Solde insuffisant sur la carte de l'étudiant.";
        }

        // Rechercher un paiement existant pour cet étudiant
        Optional<Paiement> paiementExistant = paiementRepository.findByEtudiantId(etudiantId);
        Paiement paiement;

        if (paiementExistant.isPresent()) {
            paiement = paiementExistant.get();
            LocalDate datePrevu = paiement.getDatePaiementPrevu();
            LocalDate datePaiement = LocalDate.now(); // La date du paiement est la date actuelle

            // Vérifier si la date de paiement est après la date prévue
            if (datePaiement.isAfter(datePrevu)) {
                paiement.setStatus("En retard");  // Le statut devient "En retard" si la date est dépassée
            } else {
                paiement.setStatus("Payé");  // Sinon, le statut est "Payé"
            }

            // Calculer le montant à payer
            double montantAPayer = prixChambre;  // Paiement standard sans retard pour ce cas précis

            paiement.setMontant(prixChambre);  // Le montant payé
            paiement.setMontantAPayer(montantAPayer); // Montant total à payer
            paiement.setDatePaiement(datePaiement); // La date du paiement
            paiement.setCart(cart);

            // Mise à jour du solde sur la carte
            double nouveauMontant = ancienSolde - montantAPayer;
            cart.setMontant(nouveauMontant);
            cartRepository.save(cart);
        } else {
            // Si aucun paiement n'existe, en créer un nouveau
            paiement = new Paiement();
            paiement.setEtudiant(etudiant);
            paiement.setMontant(prixChambre);
            paiement.setMontantAPayer(prixChambre);
            paiement.setDatePaiement(LocalDate.now());
            paiement.setCart(cart);

            paiement.setStatus("Non payé");  // Statut initial

            // Mise à jour du solde sur la carte
            double nouveauMontant = ancienSolde - prixChambre;
            cart.setMontant(nouveauMontant);
            cartRepository.save(cart);
        }

        // Calculer la date de paiement prévue à partir de la date d'attribution de la chambre (ajouter un mois)
        LocalDate dateAttributionChambre = etudiant.getDateAttribution();// Date d'attribution de la chambre
        LocalDate nouvelleDatePrevu = dateAttributionChambre.plusMonths(1);  // Ajouter un mois à la date d'attribution

        // Mettre à jour la date de paiement prévue
        paiement.setDatePaiementPrevu(nouvelleDatePrevu);

        // Sauvegarder le paiement mis à jour ou nouvellement créé
        paiementRepository.save(paiement);

        return "Paiement effectué ou mis à jour avec succès. Montant à payer : " + paiement.getMontantAPayer() + ". Nouveau solde : " + cart.getMontant();
    }
*/
    // hathi t5dem
 /*   
    @PutMapping("/paiement/{etudiantId}")
    public String effectuerPaiement(@PathVariable Long etudiantId) {
        // Récupérer l'étudiant
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable avec l'ID : " + etudiantId));

        // Récupérer la chambre associée à l'étudiant
        Chambre chambre = etudiant.getChambre();
        if (chambre == null) {
            throw new RuntimeException("Aucune chambre associée à l'étudiant avec l'ID : " + etudiantId);
        }

        // Récupérer le prix de la chambre
        double prixChambre = chambre.getPrix();

        // Récupérer la carte de l'étudiant
        Cart cart = cartRepository.findById(etudiant.getCart().getId())
                .orElseThrow(() -> new RuntimeException("Aucune carte associée à l'étudiant avec l'ID : " + etudiantId));

        double ancienSolde = cart.getMontant(); // Sauvegarder l'ancien solde de la carte

        if (ancienSolde < prixChambre) {
            return "Solde insuffisant sur la carte de l'étudiant.";
        }

        // Rechercher un paiement existant pour cet étudiant
        Optional<Paiement> paiementExistant = paiementRepository.findByEtudiantId(etudiantId);
        Paiement paiement;

        if (paiementExistant.isPresent()) {
            paiement = paiementExistant.get();

            // Vérifier si le statut est déjà "Payé"
            if (paiement.getStatus().equals("Payé")) {
                return "Le paiement a déjà été effectué pour cet étudiant.";
            }

            LocalDate datePrevu = paiement.getDatePaiementPrevu();
            LocalDate datePaiement = LocalDate.now(); // La date du paiement est la date actuelle

            // Vérifier si la date de paiement est après la date prévue
            if (datePaiement.isAfter(datePrevu)) {
                paiement.setStatus("En retard");  // Le statut devient "En retard" si la date est dépassée
            } else {
                paiement.setStatus("Payé");  // Sinon, le statut devient "Payé"
            }

            // Calculer le montant à payer
            double montantAPayer = prixChambre;  // Paiement standard sans retard pour ce cas précis

            paiement.setMontant(prixChambre);  // Le montant payé
            paiement.setMontantAPayer(montantAPayer); // Montant total à payer
            paiement.setDatePaiement(datePaiement); // La date du paiement
            paiement.setCart(cart);

            // Mise à jour du solde sur la carte
            double nouveauMontant = ancienSolde - montantAPayer;
            cart.setMontant(nouveauMontant);
            cartRepository.save(cart);
        } else {
            // Si aucun paiement n'existe, en créer un nouveau
            paiement = new Paiement();
            paiement.setEtudiant(etudiant);
            paiement.setMontant(prixChambre);
            paiement.setMontantAPayer(prixChambre);
            paiement.setDatePaiement(LocalDate.now());
            paiement.setCart(cart);

            paiement.setStatus("Non payé");  // Statut initial

            // Mise à jour du solde sur la carte
            double nouveauMontant = ancienSolde - prixChambre;
            cart.setMontant(nouveauMontant);
            cartRepository.save(cart);
        }

        // Calculer la date de paiement prévue à partir de la date d'attribution de la chambre (ajouter un mois)
        LocalDate dateAttributionChambre = etudiant.getDateAttribution();  // Date d'attribution de la chambre
        LocalDate nouvelleDatePrevu = dateAttributionChambre.plusMonths(1);  // Ajouter un mois à la date d'attribution

        // Mettre à jour la date de paiement prévue
        paiement.setDatePaiementPrevu(nouvelleDatePrevu);

        // Sauvegarder le paiement mis à jour ou nouvellement créé
        paiementRepository.save(paiement);

        return "Paiement effectué ou mis à jour avec succès. Montant à payer : " + paiement.getMontantAPayer() + ". Nouveau solde : " + cart.getMontant();
    }
*/
    

    
    @PutMapping("/paiement/{etudiantId}")
    public String effectuerPaiement(@PathVariable Long etudiantId) {
        // Récupérer l'étudiant
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable avec l'ID : " + etudiantId));

        // Récupérer la chambre associée à l'étudiant
        Chambre chambre = etudiant.getChambre();
        if (chambre == null) {
            throw new RuntimeException("Aucune chambre associée à l'étudiant avec l'ID : " + etudiantId);
        }

        // Récupérer le prix de la chambre
        double prixChambre = chambre.getPrix();

        // Récupérer la carte de l'étudiant
        Cart cart = cartRepository.findById(etudiant.getCart().getId())
                .orElseThrow(() -> new RuntimeException("Aucune carte associée à l'étudiant avec l'ID : " + etudiantId));

        double ancienSolde = cart.getMontant(); // Sauvegarder l'ancien solde de la carte

        // Vérifier si l'étudiant a suffisamment de fonds pour payer
        if (ancienSolde < prixChambre) {
            return "Solde insuffisant sur la carte de l'étudiant.";
        }

        // Rechercher un paiement existant pour cet étudiant
        Optional<Paiement> paiementExistant = paiementRepository.findByEtudiantId(etudiantId);
        Paiement paiement;

        if (paiementExistant.isPresent()) {
            paiement = paiementExistant.get();

            // Si le paiement est déjà effectué, ne rien faire
            if (paiement.getStatus().equals("Payé")) {
                return "Le paiement a déjà été effectué pour cet étudiant.";
            }

            // Calculer la date de paiement prévue
            LocalDate datePrevu = paiement.getDatePaiementPrevu();
            LocalDate datePaiement = LocalDate.now(); // La date du paiement est la date actuelle

            // Vérifier si la date de paiement est après la date prévue
            if (datePaiement.isAfter(datePrevu)) {
                paiement.setStatus("En retard");  // Le statut devient "En retard" si la date est dépassée
            } else {
                paiement.setStatus("Payé");  // Sinon, le statut devient "Payé"
            }

            // Calculer le montant à payer
            paiement.setMontant(prixChambre);  // Le montant payé
            paiement.setMontantAPayer(prixChambre); // Montant total à payer
            paiement.setDatePaiement(datePaiement); // La date du paiement
            paiement.setCart(cart);

            // Mise à jour du solde sur la carte
            double nouveauMontant = ancienSolde - prixChambre;
            cart.setMontant(nouveauMontant);
            cartRepository.save(cart);
        } else {
            // Si aucun paiement n'existe, en créer un nouveau
            paiement = new Paiement();
            paiement.setEtudiant(etudiant);
            paiement.setMontant(prixChambre);
            paiement.setMontantAPayer(prixChambre);
            paiement.setDatePaiement(LocalDate.now());
            paiement.setCart(cart);
            paiement.setStatus("Non payé");  // Statut initial
        }

        // Calculer la date de paiement prévue basée sur la date d'attribution de la chambre
        LocalDate dateAttributionChambre = etudiant.getDateAttribution();  // Date d'attribution de la chambre
        LocalDate nouvelleDatePrevu = dateAttributionChambre.plusMonths(1);  // Ajouter un mois à la date d'attribution

        // Mettre à jour la date de paiement prévue pour le mois suivant
        paiement.setDatePaiementPrevu(nouvelleDatePrevu);

        // Sauvegarder le paiement mis à jour ou nouvellement créé
        paiementRepository.save(paiement);

        return "Paiement effectué ou mis à jour avec succès. Montant à payer : " + paiement.getMontantAPayer() + ". Nouveau solde : " + cart.getMontant();
    }

    /**
     * Réinitialiser le statut des paiements non payés ou en retard
     * Cette tâche sera planifiée pour être exécutée tous les mois
     */
    @Scheduled(cron = "0 0 1 * * ?")  // Cette tâche sera exécutée le 1er de chaque mois à minuit
    public void resetPaiements() {
        // Trouver tous les paiements dont la date de paiement prévue est dans le mois précédent
        List<Paiement> paiements = paiementRepository.findByStatus("Non payé");

        LocalDate dateActuelle = LocalDate.now();

        for (Paiement paiement : paiements) {
            LocalDate datePrevu = paiement.getDatePaiementPrevu();

            // Si la date de paiement est dans le mois précédent et n'a pas été payée
            if (dateActuelle.isAfter(datePrevu) && paiement.getDatePaiement() == null) {
                paiement.setStatus("Non payé");  // Réinitialiser le statut
                paiement.setDatePaiementPrevu(datePrevu.plusMonths(1));  // Mettre à jour la date de paiement pour le mois suivant
                paiementRepository.save(paiement);
            }
        }
    }
    
    
    public Map<String, Object> getPaiementDetails(Long etudiantId) {
        // Trouver l'étudiant par son ID
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
                .orElseThrow(() -> new RuntimeException("Étudiant introuvable avec l'ID : " + etudiantId));

        // Trouver le paiement associé à cet étudiant
        Optional<Paiement> paiementOptional = paiementRepository.findByEtudiant(etudiant);

        // Si le paiement est absent, retourner un message d'erreur
        if (!paiementOptional.isPresent()) {
            throw new RuntimeException("Aucun paiement trouvé pour cet étudiant.");
        }

        // Récupérer le paiement (puisque nous avons vérifié qu'il existe)
        Paiement paiement = paiementOptional.get();

        // Récupérer la chambre associée à ce paiement
        Chambre chambre = paiement.getChambre();
        if (chambre == null) {
            throw new RuntimeException("Aucune chambre associée à cet étudiant.");
        }

        // Construire la réponse sous forme de Map pour générer un JSON
        Map<String, Object> response = new HashMap<>();
        response.put("nom", etudiant.getNom());
        response.put("prenom", etudiant.getPrenom());
        response.put("libelleChambre", chambre.getLibelle());
        response.put("prixChambre", chambre.getPrix());
        response.put("datePaiement", paiement.getDatePaiement());
        response.put("statusPaiement", paiement.getStatus());

        // Retourner la réponse sous forme de Map (serialisée en JSON par Spring)
        return response;
    }

}