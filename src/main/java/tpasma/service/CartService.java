package tpasma.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpasma.model.Cart;
import tpasma.model.Etudiant;
import tpasma.repository.CartRepository;
import tpasma.repository.EtudiantRepository;

@Service
public class CartService {
	 @Autowired
	    private CartRepository cartRepository;
	 @Autowired
	    private EtudiantRepository etudiantRepository;
	 
	    // Créer ou mettre à jour un panier
	    public Cart saveCart(Cart cart) {
	        return cartRepository.save(cart);
	    }

	    // Obtenir tous les paniers
	    public List<Cart> getAllCarts() {
	        return cartRepository.findAll();
	    }

	    // Obtenir un panier par son ID
	    public Optional<Cart> getCartById(Long id) {
	        return cartRepository.findById(id);
	    }

	    // Supprimer un panier par son ID
	    public void deleteCart(Long id) {
	        cartRepository.deleteById(id);
	    }

	    // Mettre à jour un panier
	    public Cart updateCart(Long id, Cart updatedCart) {
	        if (cartRepository.existsById(id)) {
	            updatedCart.setId(id);
	            return cartRepository.save(updatedCart);
	        } else {
	            return null;
	        }
	    }
	    
	    public Cart addCartToEtudiant(Long etudiantId, Cart cart) {
	        // Trouver l'étudiant par ID
	        Etudiant etudiant = etudiantRepository.findById(etudiantId)
	                .orElseThrow(() -> new RuntimeException("Étudiant introuvable avec l'ID " + etudiantId));

	        // Associer le panier à l'étudiant
	        cart.setEtudiant(etudiant);

	        // Sauvegarder le panier
	        return cartRepository.save(cart);
	    }
	    
	    
	  
}
