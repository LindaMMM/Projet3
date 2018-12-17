package fr.bicomat.dao;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.bicomat.entities.Prelevement;


@Repository
public interface PrelevementRepository extends JpaRepository<Prelevement, Long> {
	@Query("select p from Prelevement p where p.dateEchance = ?")
	 Set<Prelevement> findByDateEcheance(Date dateEchance);
}
