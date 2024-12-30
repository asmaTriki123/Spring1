
package tpasma.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tpasma.model.Chambre;
import tpasma.model.Etudiant;
import tpasma.model.Paiement;

@Repository
/*public interface PaiementRepository extends JpaRepository<Paiement, Long> {
   // boolean existsByEtudiantAndDatePaiementAfter(Etudiant etudiant, LocalDate datePaiement);
    List<Paiement> findByEtudiantAndChambre(Etudiant etudiant, Chambre chambre);
}*/

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
  //  List<Paiement> findByEtudiantAndChambre(Etudiant etudiant, Chambre chambre);
	//Optional<Paiement> findByEtudiantAndChambre(Etudiant etudiant, Chambre chambre);
	  Optional<Paiement> findByEtudiantId(Long etudiantId);
	  List<Paiement> findByStatus(String status);
	  Optional<Paiement> findByEtudiant(Etudiant etudiant);
}
