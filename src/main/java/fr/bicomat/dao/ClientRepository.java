package fr.bicomat.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.bicomat.entities.CarteBancaire;
import fr.bicomat.entities.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	@Query("select c from Client c where c.nomClient like  ?1%")
	List<Client> chercherByNom(String nom);
	List<Client> findByCarteBancaires(CarteBancaire carte);
	
	

}
