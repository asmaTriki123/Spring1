package tpasma.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tpasma.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
