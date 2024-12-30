package tpasma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tpasma.model.Etudiant;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    List<Etudiant> findByNomContaining(String nom);
    List<Etudiant> findByMatriculeContaining(String matricule);
    List<Etudiant> findByFiliereContaining(String filiere);

}