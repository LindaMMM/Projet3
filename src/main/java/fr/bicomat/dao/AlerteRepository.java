package fr.bicomat.dao;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.bicomat.entities.Alerte;
import fr.bicomat.entities.Prelevement;

@Repository
public interface AlerteRepository extends JpaRepository<Alerte, Integer> {
	@Query("select p from Alerte p where p.dateAlerte = ?")
	 Set<Alerte> findByDateAlerte(Date dateAlerte);
}
