
package tpasma.controller;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tpasma.service.PaiementService;
@RestController
@RequestMapping("/paiement")

public class PaiementController {

	@Autowired
    private PaiementService paiementService;
	@PutMapping("/effectuer/{etudiantId}")
	public ResponseEntity<String> mettreAJourPaiement(@PathVariable Long etudiantId) {
	    String message = paiementService.effectuerPaiement(etudiantId);
	    return ResponseEntity.ok(message); // Renvoie le message avec un code de succès 200
	}


	 @GetMapping("/details/{etudiantId}")
	    public Map<String, Object> getPaiementDetails(@PathVariable Long etudiantId) {
	        return paiementService.getPaiementDetails(etudiantId);
	    }
}