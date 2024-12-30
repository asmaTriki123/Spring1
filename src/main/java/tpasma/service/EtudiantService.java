package tpasma.service;

import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpasma.model.Etudiant;
import tpasma.repository.EtudiantRepository;

@Service
public class EtudiantService {
	  @Autowired
	    private EtudiantRepository etudiantRepository;

	    public List<Etudiant> getAllEtudiants() {
	        return etudiantRepository.findAll();
	    }

	    public Etudiant getEtudiantById(Long id) {
	        return etudiantRepository.findById(id).orElseThrow(() -> new RuntimeException("Étudiant non trouvé !"));
	    }
	    
	    
	    
	    
	    

	    public Etudiant addEtudiant(Etudiant etudiant) {
	        validateMotpasse(etudiant.getMotpasse());
	        return etudiantRepository.save(etudiant);
	    }
	    private void validateMotpasse(String motpasse) {
	        // Expression régulière pour le mot de passe
	        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8}$";
	        if (!Pattern.matches(regex, motpasse)) {
	            throw new IllegalArgumentException("Le mot de passe doit contenir exactement 8 "
	            		+ "caractères, incluant au moins une majuscule,"
	            		+ " un chiffre, et un caractère spécial.");
	      
	        }}
	    

	    public Etudiant updateEtudiant(Long id, Etudiant etudiantDetails) {
	        Etudiant etudiant = getEtudiantById(id);
	        etudiant.setNom(etudiantDetails.getNom());
	        etudiant.setPrenom(etudiantDetails.getPrenom());
	        etudiant.setDateNaissance(etudiantDetails.getDateNaissance());
	        etudiant.setMatricule(etudiantDetails.getMatricule());
	        etudiant.setFiliere(etudiantDetails.getFiliere());
	        etudiant.setSexe(etudiantDetails.getSexe());
	        return etudiantRepository.save(etudiant);
	    }

	    public void deleteEtudiant(Long id) {
	        etudiantRepository.deleteById(id);
	    }

	    public List<Etudiant> searchEtudiants(String nom, String matricule, String filiere) {
	        if (nom != null && !nom.isEmpty()) {
	            return etudiantRepository.findByNomContaining(nom);
	        } else if (matricule != null && !matricule.isEmpty()) {
	            return etudiantRepository.findByMatriculeContaining(matricule);
	        } else if (filiere != null && !filiere.isEmpty()) {
	            return etudiantRepository.findByFiliereContaining(filiere);
	        } else {
	            return getAllEtudiants();
	        }
	    }
}
