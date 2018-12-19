package fr.bicomat.dao;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import fr.bicomat.entities.Prelevement;


@Repository
public interface PrelevementRepository extends JpaRepository<Prelevement, Long> {
	
	Set<Prelevement> findByDateEcheance(Date dateEchance);

	@Query(value = "select p from Prelevement p where p.etatPrelevement = :etatPrelevement")
	Set<Prelevement> findByetat(@Param("etatPrelevement") String etatPrelevement);
	
	Set<Prelevement> findByDateEcheanceAndEtatPrelevement(Date dateEcheance, String etatPrelevement );
}
