package fr.bicomat.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bicomat.entities.Banque;

@Repository
public interface BanqueRepository extends JpaRepository<Banque, Integer> {

	/*@Query("select u from Banque u where u.id_banque = ?1")
	  Banque findByidbanque(Long idbanque);*/
	/* Optional<Banque> findById(Long id);*/
	
}
