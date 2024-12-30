package tpasma.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpasma.model.Chambre;
import tpasma.model.Etudiant;
import tpasma.model.Paiement;
import tpasma.repository.ChambreRepository;
import tpasma.repository.EtudiantRepository;
import tpasma.repository.PaiementRepository;
@Service
public class ChambreService {

    @Autowired
    private ChambreRepository chambreRepository;
    
    @Autowired
    private EtudiantRepository etudiantRepository;
    
    @Autowired
    private PaiementRepository paiementRepository;

    // Ajouter une chambre
    public Chambre ajouterChambre(Chambre chambre) {
        return chambreRepository.save(chambre);
    }

    // Lire toutes les chambres
    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

    // Lire une chambre par son id
    public Optional<Chambre> getChambreById(Long id) {
        return chambreRepository.findById(id);
    }

    // Mettre à jour une chambre
    public Chambre updateChambre(Long id, Chambre chambreDetails) {
        Chambre chambre = chambreRepository.findById(id).orElseThrow(() -> new RuntimeException("Chambre non trouvée"));
        chambre.setEtat(chambreDetails.getEtat());
        chambre.setLibelle(chambreDetails.getLibelle());
        chambre.setCapacite(chambreDetails.getCapacite());
        return chambreRepository.save(chambre);
        
    }

    // Supprimer une chambre
    public void deleteChambre(Long id) {
        chambreRepository.deleteById(id);
    }
    
    public Chambre getChambreById2(Long id) {
        return chambreRepository.findById(id).orElseThrow(() -> new RuntimeException("Chambre non trouvée"));
    }
    
    public Chambre updateEtatChambre(Long id, String etat) {
        Chambre chambre = getChambreById2(id);
        chambre.setEtat(etat);
        return chambreRepository.save(chambre);
    }

    // Attribuer une chambre et créer un paiement associé
// Attribuer une chambre et créer un paiement associé
/*    
public Chambre attribuerChambre(Long chambreId, Long etudiantId) {
    // Trouver la chambre
    Chambre chambre = chambreRepository.findById(chambreId)
        .orElseThrow(() -> new RuntimeException("Chambre non trouvée"));

    // Vérifier si la chambre est complète
    if (chambre.getCapacite() <= 0) {
        throw new RuntimeException("La chambre est déjà complète");
    }

    // Trouver l'étudiant
    Etudiant etudiant = etudiantRepository.findById(etudiantId)
        .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));

    // Vérifier si l'étudiant a déjà une chambre attribuée
    if (etudiant.getChambre() != null) {
        throw new RuntimeException("L'étudiant a déjà une chambre attribuée");
    }

    // Assigner la chambre à l'étudiant
    etudiant.setChambre(chambre);
    etudiant.setDateAttribution(LocalDate.now());  // Date d'attribution actuelle
    chambre.setCapacite(chambre.getCapacite() - 1);  // Réduire la capacité de la chambre
    chambreRepository.save(chambre);  // Sauvegarder la mise à jour de la chambre
    etudiantRepository.save(etudiant);  // Sauvegarder les modifications de l'étudiant

    // Créer un paiement pour l'étudiant sans la carte (cart = null)
    Paiement paiement = new Paiement();
    paiement.setEtudiant(etudiant);
    paiement.setChambre(chambre);  // Associer la chambre au paiement
    paiement.setMontant(chambre.getPrix());  // Montant de la chambre récupéré directement depuis la chambre
    paiement.setStatus("Non payé");  // Statut initial
    paiement.setDatePaiementPrevu(LocalDate.now().plusMonths(1));  // Date prévue pour le paiement dans 1 mois
    paiement.setDatePaiement(null);  // Pas encore payé
    paiement.setMontantAPayer(0);  // Ne pas remplir immédiatement ce champ
    paiement.setCart(null);  // Set cart as null

    // Enregistrer le paiement
    paiementRepository.save(paiement);

    // Retourner la chambre
    return chambre;
}

*/
   
    
    public Chambre attribuerChambre(Long chambreId, Long etudiantId) {
        // Trouver la chambre
        Chambre chambre = chambreRepository.findById(chambreId)
            .orElseThrow(() -> new RuntimeException("Chambre non trouvée"));

        // Vérifier si la chambre est déjà complète
        if (chambre.getCapacite() <= 0) {
            chambre.setEtat("Complète");  // Mettre à jour le statut de la chambre
            chambreRepository.save(chambre); // Sauvegarder la mise à jour de la chambre
            throw new RuntimeException("La chambre est déjà complète");
        }

        // Trouver l'étudiant
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
            .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));

        // Vérifier si l'étudiant a déjà une chambre attribuée
        if (etudiant.getChambre() != null) {
            throw new RuntimeException("L'étudiant a déjà une chambre attribuée");
        }

        // Assigner la chambre à l'étudiant
        etudiant.setChambre(chambre);
        etudiant.setDateAttribution(LocalDate.now());  // Date d'attribution actuelle

        // Réduire la capacité de la chambre
        chambre.setCapacite(chambre.getCapacite() - 1);

        // Si la capacité atteint 0, marquer la chambre comme complète
        if (chambre.getCapacite() == 0) {
            chambre.setEtat("Complète");
        }

        // Sauvegarder les mises à jour
        chambreRepository.save(chambre);  // Sauvegarder la mise à jour de la chambre
        etudiantRepository.save(etudiant);  // Sauvegarder les modifications de l'étudiant

        // Créer un paiement pour l'étudiant sans la carte (cart = null)
        Paiement paiement = new Paiement();
        paiement.setEtudiant(etudiant);
        paiement.setChambre(chambre);  // Associer la chambre au paiement
        paiement.setMontant(chambre.getPrix());  // Montant de la chambre récupéré directement depuis la chambre
        paiement.setStatus("Non payé");  // Statut initial
        paiement.setDatePaiementPrevu(LocalDate.now().plusMonths(1));  // Date prévue pour le paiement dans 1 mois
        paiement.setDatePaiement(null);  // Pas encore payé
        paiement.setMontantAPayer(0);  // Ne pas remplir immédiatement ce champ
        paiement.setCart(null);  // Set cart as null

        // Enregistrer le paiement
        paiementRepository.save(paiement);

        // Retourner la chambre
        return chambre;
    }

    // Libérer la chambre
    public Chambre libererChambre(Long chambreId, Long etudiantId) {
        // Récupérer la chambre par son ID
        Chambre chambre = chambreRepository.findById(chambreId)
            .orElseThrow(() -> new RuntimeException("Chambre non trouvée"));
        
        // Récupérer l'étudiant par son ID
        Etudiant etudiant = etudiantRepository.findById(etudiantId)
            .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));

        // Vérifier si l'étudiant est bien assigné à cette chambre
        if (etudiant.getChambre() == null || !etudiant.getChambre().equals(chambre)) {
            throw new RuntimeException("L'étudiant n'est pas assigné à cette chambre");
        }

        // Augmenter la capacité de la chambre
        chambre.setCapacite(chambre.getCapacite() + 1);

        // Si la chambre a maintenant une capacité > 0, on la marque comme "libre"
        if (chambre.getCapacite() > 0) {
            chambre.setEtat("libre");
        }

        // Dissocier l'étudiant de la chambre
        etudiant.setChambre(null);

        // Sauvegarder les modifications
        chambreRepository.save(chambre);
        etudiantRepository.save(etudiant);

        return chambre;
    }
}
