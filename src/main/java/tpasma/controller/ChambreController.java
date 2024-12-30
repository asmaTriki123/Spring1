package tpasma.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tpasma.model.Chambre;
import tpasma.service.ChambreService;

@RestController
@RequestMapping("/api/chambres")

public class ChambreController {

   @Autowired
    private ChambreService chambreService;

    // Ajouter une chambre
    @PostMapping("/asma")
    public Chambre ajouterChambre(@RequestBody Chambre chambre) {
        return chambreService.ajouterChambre(chambre);
    }

    // Lire toutes les chambres
    @GetMapping
    public List<Chambre> getAllChambres() {
        return chambreService.getAllChambres();
    }

    // Lire une chambre par son id
    @GetMapping("/{id}")
    public ResponseEntity<Chambre> getChambreById(@PathVariable Long id) {
        Optional<Chambre> chambre = chambreService.getChambreById(id);
        return chambre.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Mettre à jour une chambre
    @PutMapping("/{id}")
    public ResponseEntity<Chambre> updateChambre(@PathVariable Long id, @RequestBody Chambre chambreDetails) {
        Chambre updatedChambre = chambreService.updateChambre(id, chambreDetails);
        return ResponseEntity.ok(updatedChambre);
    }

    // Supprimer une chambre
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChambre(@PathVariable Long id) {
        chambreService.deleteChambre(id);
        return ResponseEntity.noContent().build();
    }
    
    
    @PostMapping("/{chambreId}/attribuer/{etudiantId}")
    public Chambre attribuerChambre(@PathVariable Long chambreId, @PathVariable Long etudiantId) {
        return chambreService.attribuerChambre(chambreId, etudiantId);
    }

    @PostMapping("/{chambreId}/liberer/{etudiantId}")
    public ResponseEntity<Chambre> libererChambre(@PathVariable Long chambreId, @PathVariable Long etudiantId) {
        Chambre chambreLiberee = chambreService.libererChambre(chambreId, etudiantId);
        return ResponseEntity.ok(chambreLiberee);
    }
}
