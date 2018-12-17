package fr.bicomat.dao;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.bicomat.entities.Virement;

@Repository
public interface VirementRepository extends JpaRepository<Virement, Long> {
	
	@Query("select v from Virement v where v.dateEchance = ? and actif = 1")
	 Set<Virement> findByDateEcheance(Date dateEchance);
}
