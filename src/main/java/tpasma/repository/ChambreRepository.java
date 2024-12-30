package tpasma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tpasma.model.Chambre;
@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
}
