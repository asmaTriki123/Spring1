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

import tpasma.model.Cart;
import tpasma.service.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartController {
	 @Autowired
	    private CartService cartService;

	 
	    // Créer ou mettre à jour un panier
	    @PostMapping
	    public ResponseEntity<Cart> createOrUpdateCart(@RequestBody Cart cart) {
	        Cart savedCart = cartService.saveCart(cart);
	        return ResponseEntity.ok(savedCart);
	    }

	    // Obtenir tous les paniers
	    @GetMapping
	    public ResponseEntity<List<Cart>> getAllCarts() {
	        List<Cart> carts = cartService.getAllCarts();
	        return ResponseEntity.ok(carts);
	    }

	    // Obtenir un panier par son ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Cart> getCartById(@PathVariable Long id) {
	        Optional<Cart> cart = cartService.getCartById(id);
	        return cart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	    }

	    // Mettre à jour un panier
	    @PutMapping("/{id}")
	    public ResponseEntity<Cart> updateCart(@PathVariable Long id, @RequestBody Cart updatedCart) {
	        Cart cart = cartService.updateCart(id, updatedCart);
	        return (cart != null) ? ResponseEntity.ok(cart) : ResponseEntity.notFound().build();
	    }

	    // Supprimer un panier
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
	        cartService.deleteCart(id);
	        return ResponseEntity.noContent().build();
	    }
	    
	    
	    
	
	    // Ajouter un panier pour un étudiant spécifique
	    @PostMapping("/{etudiantId}")
	    public ResponseEntity<Cart> addCartToEtudiant(@PathVariable Long etudiantId, @RequestBody Cart cart) {
	        Cart savedCart = cartService.addCartToEtudiant(etudiantId, cart);
	        return ResponseEntity.ok(savedCart);
	    }
}
